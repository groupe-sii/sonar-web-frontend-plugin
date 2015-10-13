class IssuesVariationsController < Api::ResourcesController
  def index
    begin
      raise ApiException.new(400, "languages parameter required") unless params[:languages]
      raise ApiException.new(400, "resource parameter required") unless params[:resource]
      resource_id=params[:resource]
      period_index=params[:period].to_i if params[:period]
      start_date=params[:start]
      resource=Project.by_key(resource_id)
      raise ApiException.new(404, "Resource [#{resource_id}] not found") if resource.nil?
      if start_date == nil
        start_date = get_start_date(resource, period_index)
      else
        start_date = Time.parse(start_date)
      end

      languages=params[:languages].split(',')
      
      issues_by_lang = {}
      languages.each do |language|
        # get new and removed issues for the language
        new_issues = get_new_issues(start_date, resource.id, language)
        closed_issues = get_closed_issues(start_date, resource.id, language)
  
        # aggregate
        issues_by_severity = aggregate(new_issues, closed_issues)
        issues_by_lang[language] = issues_by_severity
      end
      
      respond_to do |format|
        format.json { render :json => jsonp(issues_by_lang) }
        format.xml { render :xml => to_xml(objects) }
        format.text { render :text => text_not_supported }
      end
    rescue ApiException => e
      render_error(e.msg, e.code)
    end
  end
  
  def get_start_date(resource, period_index)
    last_snapshot=(resource && resource.last_snapshot)
    raise ApiException.new(401, "Unauthorized") unless has_role?(:user, last_snapshot)
    if period_index<=1
      # use the last snapshot if nothing specified
      start_date = last_snapshot.created_at
    else
      dates = [last_snapshot.created_at.beginning_of_day - 30.day]
      first = resource.snapshots.first
      puts resource.processed_snapshots.map{ |s| s.created_at }
      date = dates[period_index - 2]
      snapshots = resource.processed_snapshots.
                    reject{ |s| s.id==first.id }.
                    select{ |s| s.created_at>=date }.
                    sort_by{ |s| s.created_at }
      puts snapshots.map{ |s| s.created_at }
      start_date = snapshots.first.created_at if snapshots.first
      start_date = first.created_at unless snapshots.first
    end
    start_date
  end

  def get_new_issues(start_date, project_id, language)
    # TODO: should use ActiveRecord but Issues are not ActiveRecord...
    new_issues_query = "select i.severity from issues i left outer join projects p on p.id=i.component_id where i.created_at>'#{start_date.to_s(:db)}' and i.status='OPEN' and i.root_component_id=#{project_id} and p.language='#{language}'"
    ActiveRecord::Base.connection.execute(new_issues_query)
  end
  
  def get_closed_issues(start_date, project_id, language)
    # TODO: should use ActiveRecord but Issues are not ActiveRecord...
    closed_issues_query = "select i.severity from issues i left outer join projects p on p.id=i.component_id left outer join issue_changes ic on i.kee=ic.issue_key where ic.issue_change_creation_date>'#{start_date.to_s(:db)}' and i.status='CLOSED' and i.root_component_id=#{project_id} and p.language='#{language}'"
    ActiveRecord::Base.connection.execute(closed_issues_query)
  end

  def aggregate(new_issues, closed_issues)
    issues_by_severity = {}
    total = create_map
    new_issues.each do |issue|
      inc_values(total, issues_by_severity, issue['severity'], true)
    end
    closed_issues.each do |issue|
      inc_values(total, issues_by_severity, issue['severity'], false)
    end
    issues_by_severity[:total] = total
    issues_by_severity
  end
  
  def create_map
    map = {:added => 0, :removed  => 0, :delta => 0}
  end
  
  def inc_values(total, issues_by_severity, severity, added)
    issues_by_severity[severity] = create_map unless issues_by_severity[severity]
    if(added)
      issues_by_severity[severity][:added] += 1
      total[:added] += 1
      delta = 1
    else
      issues_by_severity[severity][:removed] += 1
      total[:removed] += 1
      delta = -1
    end
    issues_by_severity[severity][:delta] += delta
    total[:delta] += delta
  end
end
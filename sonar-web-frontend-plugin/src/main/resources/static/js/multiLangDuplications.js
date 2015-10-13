MultiLanguageDuplications = (function() {
	
	var search = function(/*String*/projectKey, /*String*/url) {
		// FIXME: Sonar has hard coded limit of 500 results => if more than 500, information is wrong...
		return jQuery.ajax({
			"url": url,
			"data": {
				'resource': projectKey,
				'metrics': "duplicated_files,duplicated_blocks,duplicated_lines,duplicated_lines_density,lines",
				'depth': -1,
				'scopes': "FIL"
			},
			"async": true
		});
	};
	
	var initByLang = function(/*Array*/languages) {
		var dupByLang = {};
		for(var i=0, l=languages.length ; i<l ; i++) {
			var lang = languages[i];
			dupByLang[lang] = {
				"duplicated_lines": 0,
				"duplicated_blocks": 0,
				"duplicated_files": 0,
				"duplicated_lines_density": 0,
				"lines": 0
			};
		}
		return dupByLang;
	};
	
	var groupByLang = function(/*Array*/languages, /*Array*/results) {
		var dupByLang = initByLang(languages);
		for(var i=0, l=results.length ; i<l ; i++) {
			var result = results[i];
			// TODO: get language file suffix
			var lang = result.lang;
			if(result.msr) {
				for(var j=0, k=result.msr.length ; j<k ; j++) {
					var item = result.msr[j];
					dupByLang[lang][item.key] += item.val;
				}
			}
		}
		return dupByLang;
	}
	
	var setTotalCount = function(/*String*/lang, /*Object*/dups) {
		jQuery("a[href='#"+lang+"-duplications'] .total-count").html(dups["duplicated_files"]);
		jQuery("#"+lang+"-duplications .duplications-percent").html((Math.round(dups["duplicated_lines"]/dups["lines"]*1000)/10)+"%");
		jQuery("#"+lang+"-duplications .duplications-blocks").html(dups["duplicated_blocks"]);
		jQuery("#"+lang+"-duplications .duplications-lines").html(dups["duplicated_lines"]);
		jQuery("#"+lang+"-duplications .duplications-files").html(dups["duplicated_files"]);
	};
	
	
	return {
		display: function(/*Array*/languages, /*boolean*/hideEmpty, /*String*/projectKey, /*String*/url) {
			var results = search(projectKey, url).then(function(results) {
				var dupByLang = groupByLang(languages, results);
				for(var i=0, l=languages.length ; i<l ; i++) {
					var lang = languages[i];
					setTotalCount(lang, dupByLang[lang]);
				}
				jQuery("#duplications-by-language").removeClass("loading");
			});
		}
	}
})();
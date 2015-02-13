MultiLanguageIssues = (function() {
	var totalByLang;
	var countBySeverity;
	var progress = {
		currentStep: 0,
		numSteps: 0
	};
	
	var setTotalCount = function(/*String*/lang) {
		var total = totalByLang[lang];
		jQuery("a[href='#"+lang+"-issues'] .total-count").html(total);
		jQuery("#"+lang+"-issues .total-count").html(total);
	};
	
	var setSeverityCount = function(/*String*/lang, /*String*/severity, /*int*/max) {
		var count = countBySeverity[lang][severity];
		jQuery("#"+lang+"-issues ."+severity.toLowerCase()+"-count").html(count);
		jQuery("#"+lang+"-issues .barchart-"+severity.toLowerCase()+" .barchart > div").width((100 * count / max)+"%");
	};
	
	var search = function(/*String*/lang, /*String*/severity, /*String*/projectKey) {
		return jQuery.ajax({
			"url": "/api/issues/search",
			"data": {
				'languages': lang,
				'componentRoots': projectKey,
				'resolved': false,
				'severities': severity.toUpperCase(),
				'pageSize': 1
			},
			"async": true
		});
	};
	
	var countTotalByLang = function(lang, severity, data) {
		totalByLang[lang] += countBySeverity[lang][severity] = data.paging.total;
	};
	
	var next = function(languages, hideEmpty, severities, projectKey, max, langIdx, severityIdx) {
		// all severities are done => next language
		if(severityIdx>=severities.length) {
			severityIdx = 0;
			langIdx++;
		}
		var lang = languages[langIdx];
		var severity = severities[severityIdx];
		if(lang) {
			if(severityIdx==0) {
				totalByLang[lang] = 0;
				countBySeverity[lang] = {};
			}
			search(lang, severity, projectKey)
				.then(countTotalByLang.bind(this, lang, severity))
				.then(setSeverityCount.bind(this, lang, severity, max))
				.always(next.bind(this, languages, hideEmpty, severities, projectKey, max, langIdx, severityIdx+1));
		} else {
			// all languages are done => finish
			languages.forEach(setTotalCount);
			jQuery("#issues-by-language").removeClass("loading");
		}
		progress.currentStep++;
		jQuery("#issues-by-language .progressbar .progress").width((100 * progress.currentStep / progress.numSteps)+"%");
	};
	
	return {
		display: function(/*Array*/languages, /*boolean*/hideEmpty, /*Array*/severities, /*String*/projectKey, /*int*/max) {
			totalByLang = {};
			countBySeverity = {};
			progress.currentStep = 0;
			progress.numSteps = languages.length*severities.length;
			next(languages, hideEmpty, severities, projectKey, max, 0, 0);
		}
	}
})();
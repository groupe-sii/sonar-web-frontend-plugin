MultiLanguageIssues = (function() {
	var totalByLang;
	var countBySeverity;
	var progress = {
		currentStep: 0,
		numSteps: 0
	};
	
	var setTotalCount = function(/*boolean*/hideEmpty, /*String*/lang) {
		var total = totalByLang[lang];
		jQuery("a[href='#"+lang+"-issues'] .total-count").html(total);
		jQuery("#"+lang+"-issues .total-count").html(total);
		if(hideEmpty && !total) {
			jQuery("#"+lang+"-issues-tab").hide();
			jQuery("#"+lang+"-issues").hide();
		}
	};
	
	var setSeverityCount = function(/*String*/lang, /*String*/severity, /*int*/max) {
		var count = countBySeverity[lang][severity];
		jQuery("#"+lang+"-issues ."+severity.toLowerCase()+"-count").html(count);
		var charts = jQuery("#"+lang+"-issues .barchart-"+severity.toLowerCase()+" .barchart > div");
		if(charts.length==1) {
			jQuery(charts[0]).width((100 * count / max)+"%");
		}
	};
	
	var search = function(/*String*/lang, /*String*/severity, /*String*/projectKey, /*String*/url) {
		return jQuery.ajax({
			"url": url,
			"data": {
				'languages': lang,
				'componentRoots': projectKey,
				'resolved': false,
				'severities': severity.toUpperCase(),
				'pageSize': 1,
				'hideRules': true
			},
			"async": true
		});
	};
	
	var countTotalByLang = function(lang, severity, data) {
		totalByLang[lang] += countBySeverity[lang][severity] = data.paging.total;
	};
	
	var next = function(languages, hideEmpty, severities, projectKey, max, url, langIdx, severityIdx) {
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
			search(lang, severity, projectKey, url)
				.then(countTotalByLang.bind(this, lang, severity))
				.then(setSeverityCount.bind(this, lang, severity, max))
				.always(next.bind(this, languages, hideEmpty, severities, projectKey, max, url, langIdx, severityIdx+1));
		} else {
			// all languages are done => finish
			languages.forEach(setTotalCount.bind(this, hideEmpty));
			jQuery("#issues-by-language").removeClass("loading");
		}
		progress.currentStep++;
		jQuery("#issues-by-language .progressbar .progress").width((100 * progress.currentStep / progress.numSteps)+"%");
	};
	

	var setVariations = function(/*String*/language, /*Object*/variations) {
		setTotalVariation(language, variations.total);
		for(var severity in variations) {
			if(severity!=="total") {
				setSeverityVariation(language, severity, variations[severity], variations.total);
			}
		}
	};
	
	var setTotalVariation = function(/*String*/lang, /*Object*/totalVariations) {
		jQuery("#"+lang+"-issues .total-variation").html(formatVariation(totalVariations.delta, true)).addClass(variationClass(totalVariations.delta));
		jQuery("#"+lang+"-issues-tab .total-variation").html(formatVariation(totalVariations.delta)).addClass(variationClass(totalVariations.delta));
		jQuery("#"+lang+"-issues .total.textual-variation .added .value").html(totalVariations.added);
		jQuery("#"+lang+"-issues .total.textual-variation .removed .value").html(totalVariations.removed);
	};
	
	var setSeverityVariation = function(/*String*/lang, /*String*/severity, /*Object*/variations, /*Object*/totalVariations) {
		jQuery("#"+lang+"-issues ."+severity.toLowerCase()+"-variation").html(formatVariation(variations.delta, true)).addClass(variationClass(variations.delta));
		var charts = jQuery("#"+lang+"-issues .barchart-"+severity.toLowerCase()+" .barchart > div");
		if(charts.length==2) {
			var max = Math.max(totalVariations.removed, totalVariations.added);
			jQuery(charts[0]).width((100 * variations.removed / max)+"%").css("float", "right");
			jQuery(charts[1]).width((100 * variations.added / max)+"%");
		}
	};
	
	var getVariations = function(/*Array*/languages, /*String*/projectKey, /*int*/periodIdx, /*String*/url) {
		return jQuery.ajax({
			"url": url,
			"data": {
				'languages': languages,
				'resource': projectKey,
				'period': periodIdx
			},
			"async": true
		});
	};
	
	var formatVariation = function(/*int*/value, /*boolean*/parens) {
		var str = value<0 ? ""+value : "+"+value;
		return parens ? "("+str+")" : str;
	};
	
	var variationClass = function(/*int*/value) {
		return value==0 ? "var" : (value<0 ? "varb" : "varw");
	};
	

	var setTrends = function(/*String*/language, /*Object*/variations) {
		setTotalTrend(language, variations.total);
		for(var severity in variations) {
			if(severity!=="total") {
				setSeverityTrend(language, severity, variations[severity], variations.total);
			}
		}
	};
	
	var setTotalTrend = function(/*String*/lang, /*Object*/totalVariations) {
		jQuery("#"+lang+"-issues .total-trend").html(trendIcon(totalVariations.delta, totalVariations.delta));
		jQuery("#"+lang+"-issues-tab .total-trend").html(trendIcon(totalVariations.delta, totalVariations.delta));
	};
	
	var setSeverityTrend = function(/*String*/lang, /*String*/severity, /*Object*/variations, /*Object*/totalVariations) {
		var max = Math.max(totalVariations.removed, totalVariations.added);
		jQuery("#"+lang+"-issues ."+severity.toLowerCase()+"-trend").html(trendIcon(variations.delta, max));
	};
	
	var trendIcon = function(/*int*/value, /*int*/max) {
		var trend = max==0 ? 0 : 2*value/max;
		trend = trend>0 ? Math.ceil(trend) : -Math.ceil(-trend);
		return value==0 ? '' : '<i class="icon-trend-'+trend+' '+trendIconClass(value)+'"></i>';
	};
	
	var trendIconClass = function(/*int*/value) {
		return value==0 ? 'icon-black' : (value>0 ? 'icon-red' : 'icon-green');
	};
	
	return {
		display: function(/*Array*/languages, /*boolean*/hideEmpty, /*Array*/severities, /*String*/projectKey, /*int*/max, /*String*/url) {
			totalByLang = {};
			countBySeverity = {};
			progress.currentStep = 0;
			progress.numSteps = languages.length*severities.length;
			next(languages, hideEmpty, severities, projectKey, max, url, 0, 0);
		},
		
		displayVariations: function(/*Array*/languages, /*String*/projectKey, /*int*/periodIdx, /*String*/url) {
			getVariations(languages.join(","), projectKey, periodIdx, url)
				.then(function(variations) {
					for(var lang in variations) {
						setVariations(lang, variations[lang]);
					}
				});
		},
		
		displayTrends: function(/*Array*/languages, /*String*/projectKey, /*String*/url) {
			getVariations(languages.join(","), projectKey, null, url)
				.then(function(variations) {
					for(var lang in variations) {
						setTrends(lang, variations[lang]);
					}
				});
		}
	}
})();
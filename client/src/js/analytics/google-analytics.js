var $ = require('../../../bower_components/jquery/dist/jquery.js');

$.getScript('http://www.google-analytics.com/analytics.js', function(data, textStatus, jqxhr) {
	var ga = window.ga;
	
	ga('create', 'UA-51739473-1', 'socialapolitiker.se');
	ga('send', 'pageview');
});

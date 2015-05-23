var $ = require('../../../bower_components/jquery/dist/jquery.js');

$.getScript('http://www.google-analytics.com/analytics.js', function(data, textStatus, jqxhr) {
	var ga = window.ga;
	
	ga('create', 'UA-51739473-1', 'socialapolitiker.se');
	ga('send', 'pageview');
	
	$(document).on('click', '.js-ga-sender', function(e) {
		var $this = $(this);
		
		var category = $this.data('gaCategory');
		var action = $this.data('gaAction');
		var label = $this.data('gaLabel');
		var value = $this.data('gaValue');
		
		if (value !== undefined) {
			value = parseInt(value, 10);
		}
		
		ga('send', 'event', category, action, label, value);
	});
});

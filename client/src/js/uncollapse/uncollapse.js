var $ = require('../../../bower_components/jquery/dist/jquery.js');

var init = function() {
	$('.js-uncollapse').each(function() {
		var $this = $(this);
		var $targetElement = $this.prev();
		var origHeight = parseInt($targetElement.css('max-height'), 10);
		var targetHeight = $targetElement[0].scrollHeight + 1;
		var $icon = $this.find('.js-uncollapse__icon');
		var $text = $this.find('.js-uncollapse__text');
		
		if (origHeight >= targetHeight) {
			$targetElement.removeClass('cut-box');
			$this.addClass('hidden');
			return;
		}
		
		$this.click(function(e) {
			e.preventDefault();
			
			var currentHeight = parseInt($targetElement.css('max-height'), 10);
			
			if (currentHeight === targetHeight) {
				$targetElement.css('max-height', origHeight + "px");
				$targetElement.addClass('cut-box');
				$text.text($text.text().replace('Färre', 'Fler'));
				$icon.removeClass('icon-caret-up').addClass('icon-caret-down');
			} else {
				$targetElement.css('max-height', targetHeight + "px");
				$targetElement.removeClass('cut-box');
				$text.text($text.text().replace('Fler', 'Färre'));
				$icon.removeClass('icon-caret-down').addClass('icon-caret-up');
			}
			
		});
	});
};

module.exports = {
	init: init
};
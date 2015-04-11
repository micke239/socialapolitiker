var $ = require('../../../bower_components/jquery/dist/jquery.js');

var init = function() {
	console.log('initing search.js component');
	
	$('.js-search').each(function() {
		var $this = $(this);
		var $searchField = $this.find('.js-search__search-field');
		var $list = $this.find('.js-search__search-list');
		var $listItems = $this.find('.js-search__search-list li');
		
		$searchField.on('keyup',function() {
			var inputValue = $searchField.val();
			var regex, showAll;
			
			if (inputValue == "") {
				showAll = true;
			} else {
				var words = inputValue.split(" ");
				var regexString = ".*";
				for (var i = 0; i < words.length; i++) {
					regexString += words[i] + ".*";
				}
				
				regex = new RegExp(regexString, "i")
			}
			
			$listItems.each(function() {
				var $this = $(this);
				if (showAll || regex.test($this.text().trim())) {
					$this.removeClass('hidden');
				} else {
					$this.addClass('hidden');
				}
			});
			
			if ($list[0].scrollHeight <= parseInt($list.css('max-height'), 10)) {
				$list.removeClass('cut-box');
				$list.next().addClass('hidden');
				console.log($list.next());
			} else {
				$list.addClass('cut-box');
				$list.next().removeClass('hidden');
			}
			
		});
		
	});
};

module.exports = {
	init : init	
};

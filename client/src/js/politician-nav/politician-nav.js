var $ = require('../../../bower_components/jquery/dist/jquery.js');
var breakpoints = require('../breakpoints/breakpoints.js');

//work around for iOS 300ms delay
var CLICK_OR_TOUCH = (('ontouchend' in window)) ? 'touchend' : 'click';

var $navContainer = $('.js-nav__container');

var isPalm = function() {
	return breakpoints.currentBreakpoint === "palm";
};

var initNav = function() {
	var $nav = $(this);
	var navId = $nav.data('navId');
	var isOpen = false;
	
	var hide = function($nav, breakpointChange) {
		if ((breakpointChange && !isPalm()) || (!breakpointChange && isPalm())) {
			var $palmNav = $('.js-nav__palm-nav[data-nav-id="' + navId + '"]');
			
			$('.js-politician-nav__wrapper[data-nav-id="' + navId + '"]').append($palmNav);
		} else {
			$nav.hide();	
		}
		
		isOpen = false;
	};

	var show = function($button, $nav) {
		if (isPalm()) {
			var $palmNav = $nav.find('.js-nav__palm-nav');
			$button.after($palmNav);
		} else {
			$nav.css('left', $button.offset().left - 300 + $button.width() / 2)
			$nav.show();
		}
		
		isOpen = true;
	};
	
	$('.js-nav__menu-button').on(CLICK_OR_TOUCH, function(e) {
		e.preventDefault();
		
		if ($(this).data("navId") !== navId || isOpen) {
			hide($nav);
		} else {
			show($(this), $nav);
		}
	});
	
	$(window).on('breakpoint-change', function(e, breakpoint) {
		if (isOpen) {
			hide($nav, true);
		}
	});
};

var initPalm = function() {
	$(this).on(CLICK_OR_TOUCH, function(e) {
		e.preventDefault();
		
		if ($navContainer.is(':visible')) {
			$navContainer.slideUp();
		} else {
			$navContainer.slideDown();
		}
	});
	
	$(window).on('breakpoint-change', function(e, breakpoint) {
		$navContainer.removeAttr('style');
	});
};

var init = function() {
	$('.js-nav').each(initNav);
	$('.js-nav__palm').each(initPalm);
};

module.exports = {
	init : init	
};
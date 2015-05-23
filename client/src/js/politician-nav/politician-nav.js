var $ = require('../../../bower_components/jquery/dist/jquery.js');
var breakpoints = require('../breakpoints/breakpoints.js');

var WINDOW_HEIGHT = $(window).height();
var TOP = "-" + WINDOW_HEIGHT + "px";
var MAX_HEIGHT = WINDOW_HEIGHT + "px";

var shouldAnimate = function() {
	return breakpoints.currentBreakpoint === "palm";
};

var initNav = function() {
	var $nav = $(this);
	var navId = $nav.data('navId');
	
	var isVisible = false;
	
	var hidingDone = function() {
		isVisible = false;
	};
	
	var showingDone = function() {
		$nav.css('maxHeight', "none");
		isVisible = true;
	};
	
	var hide = function($nav, animate) {
		animate = animate && shouldAnimate();
		var top = TOP;
		
		if (breakpoints.currentBreakpoint !== "palm") {
			top = (parseInt(TOP, 10) - 200) + "px";
		}
		
		$nav.css('maxHeight', MAX_HEIGHT)
		if (animate) {
			$nav.animate({
				top: top
			}, 250, hidingDone);
		} else {
			$nav.css('top', top)
			hidingDone();
		}
	};

	var show = function($nav, animate) {
		animate = animate && shouldAnimate();
		
		var top = $(window).scrollTop() + "px";
		if (breakpoints.currentBreakpoint !== "palm") {
			top = "auto";
		}
		
		if (animate) {
			$nav.animate({
				top: top
			}, 250, showingDone);
		} else {
			$nav.css('top', top);
			showingDone();
		}
	};
	
	//work around for iOS 300ms delay
	var clickOrTouch = (('ontouchend' in window)) ? 'touchend' : 'click';
	$('.js-nav__menu-button').on(clickOrTouch, function(e) {
		e.preventDefault();
		
		if ($(this).data("navId") !== navId || isVisible) {
			hide($nav, true);
		} else {
			show($nav, true);
		}
	});
	
	$(window).on('breakpoint-change', function(e, breakpoint) {
		if (breakpoint === "palm" 
			|| breakpoints.lastBreakpoint === "palm") {
			var shouldShow = isVisible;
			
			hide($nav, false);
			if (shouldShow) {
				show($nav, false);
			}
		}
	});
};

var init = function() {
	$('.js-nav').each(initNav);
};

module.exports = {
	init : init	
};
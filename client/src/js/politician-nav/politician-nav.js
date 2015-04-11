var $ = require('../../../bower_components/jquery/dist/jquery.js');

var init = function() {
	var $nav = $('.js-politician-nav');
	$('.js-politician-nav__menu-button').click(function() {
		$nav.toggleClass('inactive');
	});
};

module.exports = {
	init : init	
};
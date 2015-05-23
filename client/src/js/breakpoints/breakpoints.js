/**
 * Borrowed from http://zerosixthree.se/detecting-media-queries-with-javascript/
 */

'use strict';

var $ = require('../../../bower_components/jquery/dist/jquery.js');

var MediaQueryListener = function() {
    this.afterElement = window.getComputedStyle ? window.getComputedStyle(document.body, ':after') : false;
    this.currentBreakpoint = '';
    this.lastBreakpoint = '';
    this.init();
};

MediaQueryListener.prototype = {

    init: function () {
        var self = this;

        if(!self.afterElement) {
            // If the browser doesn't support window.getComputedStyle just return
            return;
        }

        self._resizeListener();

    },
    _resizeListener: function () {
        var self = this;
        
        var calculateBreakpoint = function() {
        	self.currentBreakpoint = self.afterElement.getPropertyValue('content');
        	
        	if (self.currentBreakpoint !== self.lastBreakpoint) {
        		$(window).trigger('breakpoint-change', self.currentBreakpoint);
        		self.lastBreakpoint = self.currentBreakpoint;
        	}
        }
        
        calculateBreakpoint();
        $(window).on('resize orientationchange load', function() {
        	calculateBreakpoint();
        });
    }

};

module.exports = new MediaQueryListener();

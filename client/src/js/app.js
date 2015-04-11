var $ = require('../../bower_components/jquery/dist/jquery.js');
//var menuToggler = require('./menu-toggler/menu-toggler.js');

require('./analytics/google-analytics.js');

var ready = require('./ready/ready.js');

var uncollapse = require('./uncollapse/uncollapse.js');
var search = require('./search/search.js');
var politicianNav = require('./politician-nav/politician-nav.js');


//page specific if necessary
ready.ready(function() {
	uncollapse.init();
	search.init();
	politicianNav.init();
});
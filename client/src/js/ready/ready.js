/**
 * As jQuery ready 'sucks' at determining
 *  DOMContentLoaded if it was already fired before 
 *  .ready() is called, I need this implementation.
 *  
 * This implementation needs the following script at the very bottom of the body though:
 *  <script type="text/javascript">window.DOMIsReady();</script>
 */


var readyQueue = [];

var ready = function(f) {
	if (window.DOMIsReady) {
		setTimeout(f, 0);
	} else {
		readyQueue.push(f);
	}
};

window.handleDOMIsReady = function() {
	while(readyQueue.length !== 0) {
		setTimeout(readyQueue.shift(), 0);
	}
};

module.exports = {
	ready : ready	
};
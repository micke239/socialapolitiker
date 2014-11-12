var $ = require('jquery');

var menuToggler = function() {
    $('.js-menu-toggler').each(function() {
        var $self = $(this);
        var $icon = $self.find('i');
        var $target = $($self.data('target'));

        if ($target.length > 0) {
            $self.click(function() {
                if ($target.hasClass('hidden')) {
                    $target.removeClass('hidden');
                    $icon.removeClass('fa-caret-down');
                    $icon.addClass('fa-caret-up');
                } else {
                    $target.addClass('hidden');
                    $icon.addClass('fa-caret-down');
                    $icon.removeClass('fa-caret-up');
                }
            });
        } else {
            $self.addClass('disabled');
        }
    });
};

module.exports = {
    init : menuToggler
};
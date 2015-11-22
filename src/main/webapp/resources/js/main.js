$(function () {
    // Convert the links in the navigation to AJAX links. This way the page can be crawled by bots.
    $('header nav a').on('click', function (e) {
        jsf.ajax.navigate(e.currentTarget.href);
        e.preventDefault();
    });
    $(window).trigger('page:change');
});

// If the main element was rendered, we need to update the location in the browser.
$(document).on('jsf:ajaxUpdate', '#main', function (e) {
    var element = $(e.currentTarget);
    var url = element.data('url');

    var pathname = window.location.pathname;
    if (pathname != url) {
        window.history.pushState(null, null, url);
    }
    $(window).trigger('page:change');
});

$(window).on('page:change', function () {
    var url = window.location.pathname;

    // adjust the menu state
    $('nav a').removeClass('active');
    $('nav a[href="' + url + '"]').addClass('active');

    // hide teasers and footer in the shopping cart.

    var visibility = $('#shoppingCart').data('count') ? 'hidden' : 'visible';
    $('aside, footer').css('visibility', visibility);
});

$(document).on('jsf:ajaxUpdate', '.error', function (e) {
    var element = $(e.currentTarget);
    if (element.hasClass('alert') && element.html()) {
        alert(element.html());
    }
});

// On history back, get the contents via AJAX.
$(window).on('popstate', function () {
    jsf.ajax.navigate(location.pathname);
});
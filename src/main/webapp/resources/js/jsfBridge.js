// fix for https://java.net/jira/browse/JAVASERVERFACES_SPEC_PUBLIC-790
jsf.originalGetViewState = jsf.getViewState;
jsf.getViewState = function (form) {
    if (form) {
        // if we don't have the view state hidden input
        if (!form.elements['javax.faces.ViewState']) {
            // add it to the form
            $(form).append('<input type="hidden" name="javax.faces.ViewState" value="stateless"/>')
        }
    }
    return this.originalGetViewState(form);
}

jsf.ajax.navigate = function (url) {
    // we need this fake element, because jsf.ajax.request usually needs a form.
    var fakeElement = {
        hasAttribute: $.noop,
        form: {
            action: url,
            elements: [],
            length: 0
        }
    };
    // and we need an event.
    var fakeEvent = {
        type: 'navigate'
    };
    this.request(fakeElement, fakeEvent, {execute: '@none', render: 'main'});
};

jsf.ajax.addOnEvent(function (data) {
    // react on successful rendering
    if (data.status == 'success' && data.responseCode == 200) {
        var xml = $(data.responseXML);
        var updates = xml.find('update');

        updates.each(function (idx, e) {
            // fire a custom event
            e = document.getElementById(e.id);
            $(e).trigger('jsf:ajaxUpdate');
        });
    }
});

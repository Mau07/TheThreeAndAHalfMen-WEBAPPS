$( document ).ready(function() {

    // Select dropdown
    $("select").selectpicker({style: 'btn-lg btn-primary', menuStyle: 'dropdown-inverse'});

});

// Tab Wizards
var $tabs = $('#report-tab li');

function showPrev() {
    $tabs.filter('.active').prev('li').removeClass("disabled");
    $tabs.filter('.active').prev('li').find('a[data-toggle]').each(function () {
       $(this).attr("data-toggle", "tab");
    });

    $tabs.filter('.active').prev('li').find('a[data-toggle="tab"]').tab('show');

    $tabs.filter('.active').next('li').find('a[data-toggle="tab"]').each(function () {
        $(this).attr("data-toggle", "").parent('li').addClass("disabled");        
    })
}

function showNext() {
    $tabs.filter('.active').next('li').removeClass("disabled");
    $tabs.filter('.active').next('li').find('a[data-toggle]').each(function () {
        $(this).attr("data-toggle", "tab");
    });

    $tabs.filter('.active').next('li').find('a[data-toggle="tab"]').tab('show');

    $tabs.filter('.active').prev('li').find('a[data-toggle="tab"]').each(function () {
        $(this).attr("data-toggle", "").parent('li').addClass("disabled");;        
    })
}
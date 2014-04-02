//jQuery to stop dropdown in login from closing
$('.dropdown-menu input, .dropdown-menu label').click(function(e) {
	e.stopPropagation();
});

$(function() {
	$('form').superLabels({
		labelLeft:5,
		labelTop:5
	});
});
$(document).ready(function() {

	$('#ContactInfoForm').submit(function(e) {
		e.preventDefault();

		var status = dataValidation();

		if (status) {
			$('#ContactInfoForm').get(0).submit();
		}
	});

	function dataValidation() {

		var status = true;

		if ($("#name").val() == "") {
			status = false;
			$("#errorMsgname").text("Empty field found!!");
			$("#name").focus();
		} else
			$("#errorMsgname").text("");

		if ($("#subject").val() == "") {
			status = false;
			$("#errorMsgsubject").text("Empty field found!!");
			$("#subject").focus();
		} else
			$("#errorMsgsubject").text("");

		if ($("#email").val() == "") {
			status = false;
			$("#errorMsgemail").text("Empty field found!!");
			$("#email").focus();
		} else
			$("#errorMsgemail").text("");

		if ($("#comment").val() == "") {
			status = false;
			$("#errorMsgcomment").text("Empty field found!!");
			$("#comment").focus();
		} else
			$("#errorMsgcomment").text("");

		return status;
	}

});
$(document).ready(function() {

	$('#signupInfoForm').submit(function(e) {
		e.preventDefault();

		var status = dataValidation();

		if (status) {
			$('#signupInfoForm').get(0).submit();
		}
	});

	function dataValidation() {

		var status = true;
		var regexMobile = /^(?:\+?88)?01[1,2,3,5,6,7,8,9]{1}[0-9]{8}$/;

		if ($("#userName").val() == "") {
			status = false;
			$("#errorMsguserName").text("Empty field found!!");
			$("#userName").focus();
		} else
			$("#errorMsguserName").text("");

		if ($("#email").val() == "") {
			status = false;
			$("#errorMsgemail").text("Empty field found!!");
			$("#email").focus();
		} else
			$("#errorMsgemail").text("");

		if ($("#phone").val() == "") {
			status = false;
			$("#errorMsgphone").text("Empty field found!!");
			$("#phone").focus();
		} else if (!regexMobile.test($("#phone").val())) {
			status = false;
			$("#errorMsgphone").text("Invalid contact number!!");
			$("#phone").focus();
		} else
			$("#errorMsgphone").text("");

		if ($("#password").val() == "") {
			status = false;
			$("#errorMsgpassword").text("Empty field found!!");
			$("#password").focus();
		} else
			$("#errorMsgpassword").text("");

		if ($("#firstname").val() == "") {
			status = false;
			$("#errorMsgfirstname").text("Empty field found!!");
			$("#firstname").focus();
		} else
			$("#errorMsgfirstname").text("");

		return status;
	}

});

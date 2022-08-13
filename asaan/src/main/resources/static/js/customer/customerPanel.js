$(document)
		.ready(
				function() {

					$('#tblSellingProduct').DataTable();
					$('#tblTrack').DataTable();
					
				
					var whichRequest = $('#checkRequest').val();

					if (whichRequest == "product") {

						$("#product").addClass('active');
						$("#tabProduct").addClass('active');

					} else {
						$("#product").addClass('active');
						$("#tabProduct").addClass('active');
					}

					var sellTarget = [];

					$("#btnReset").click(function() {
						clrsecedulelist();
					});

					$("#btnschedule")
							.click(
									function() {

										var flag = dataValidation();

										if (flag == true) {

											arrOne = [];
											arrTwo = [];

											for ( var name in sellTarget) {
												arrOne.push(name);
												arrTwo.push(sellTarget[name]);
											}

											var division = $('#division').val();
											var schudleDate = $('#schudleDate')
													.val();
											var scheduleTime = $(
													'#scheduleTime').val();
											var productName = arrOne.toString();

											if (productName == '') {
												alert("Product field must not be empty!!");
											} else {

												var scheduleList = {};
												scheduleList.division = division;
												scheduleList.schudleDate = schudleDate;
												scheduleList.scheduleTime = scheduleTime;
												scheduleList.productName = productName;

												$
														.ajax({
															type : "POST",
															contentType : "application/json",
															url : "/customer/saveSchedule",
															data : JSON
																	.stringify(scheduleList),
															timeout : 100000,
															async : true,
															dataType : 'json',
															success : function(
																	data) {
																if (data == '1') {
																	alert("Successfully added!!");
																	clrsecedulelist();
																}

															},
															error : function(
																	xhr,
																	status,
																	error) {
																var err = eval("("
																		+ xhr.responseText
																		+ ")");
																alert(err.Message);
															}
														});
											}

										}

									});

					function dataValidation() {

						var status = true;

						if ($("#schudleDate").val() == "") {
							status = false;
							$("#errorDatepicker").text("Empty field found!!");
							$("#schudleDate").focus();

						} else if (isValidDate($("#schudleDate").val()) == false) {
							status = false;
							$("#errorDatepicker").text("Invalid date format!!");
							$("#schudleDate").focus();

						} else
							$("#errorDatepicker").text("");

						if ($("#scheduleTime").val() == -1) {
							status = false;
							$("#errorsellerTime").text("Empty field found!!");
							$("#scheduleTime").focus();
						} else
							$("#errorsellerTime").text("");

						if ($("#division").val() == -1) {
							status = false;
							$("#errorsellerdivision").text(
									"Empty field found!!");
							$("#division").focus();
						} else
							$("#errorsellerdivision").text("");

						return status;
					}

					$('#tblSellingProduct tbody')
							.on(
									'click',
									'#edit',
									function() {

										var curRow = $(this).closest('tr');
										sellTarget[$.trim(curRow.find(
												'td:eq(0)').text())] == undefined ? sellTarget[$
												.trim(curRow.find('td:eq(0)')
														.text())] = curRow
												: delete sellTarget[$
														.trim(curRow.find(
																'td:eq(0)')
																.text())];

									});

					$(document).on(
							"change",
							"#scheduleTime",
							function(e) {

								var sellerTime = $(
										"#scheduleTime option:selected").val();
								var sellerDate = $("#schudleDate").val();

								if (sellerTime != "" && sellerDate != "") {

									loadGetAjax(sellerTime, sellerDate);

								}

							});

					$(document).on(
							"change",
							"#schudleDate",
							function(e) {

								var sellerTime = $(
										"#scheduleTime option:selected").val();
								var sellerDate = $("#schudleDate").val();

								if (sellerTime != "" && sellerDate != "") {

									loadGetAjax(sellerTime, sellerDate);

								}

							});

					function loadGetAjax(sellerTime, sellerDate) {

						$.ajax({
							type : "GET",
							url : "/customer/checkScheduledetails",
							data : {
								sellerTime : sellerTime,
								sellerDate : sellerDate
							},

							success : function(result) {
								if (result == true) {
									$("#btnschedule").prop('disabled', true);
									$("#btnReset").prop('disabled', true);
									alert("Sorry,This time already booked!!");

								} else {
									$("#btnschedule").prop('disabled', false);
									$("#btnReset").prop('disabled', false);
								}

							},
							error : function(xhr, status, error) {
								alert("Sorry,Something wrong!!");
							}
						});

					}

					// for send message

					$("#msgSend")
							.click(
									function() {

										var sendMsgStatus = sendMsgValidation();

										if (sendMsgStatus == true) {

											var subject = $('#subject').val();
											var comment = $('#comment').val();

											var msgList = {};
											msgList.subject = subject;
											msgList.comment = comment;

											$
													.ajax({
														type : "POST",
														contentType : "application/json",
														url : "/customer/sendMessage",
														data : JSON
																.stringify(msgList),
														timeout : 100000,
														async : true,
														dataType : 'json',
														success : function(data) {
															if (data == '1') {
																alert("Message send successfully!!");
																clrsendMsg();
															}

														},
														error : function(xhr,
																status, error) {
															var err = eval("("
																	+ xhr.responseText
																	+ ")");
															alert(err.Message);
														}
													});

										}

									});

					function sendMsgValidation() {

						var status = true;

						if ($("#subject").val() == "") {
							status = false;
							$("#errormsgSubject").text("Empty field found!!");
							$("#subject").focus();

						} else
							$("#errormsgSubject").text("");

						if ($("#comment").val() == "") {
							status = false;
							$("#errormsgComment").text("Empty field found!!");
							$("#comment").focus();

						} else
							$("#errormsgComment").text("");

						return status;
					}

					// profile setting
		

					function clrsendMsg() {
						$("#subject").val("");
						$("#comment").val("");
						$("#errormsgSubject").text("");
						$("#errormsgComment").text("");
					}

					function clrsecedulelist() {
						$("#schudleDate").val("");
						$("#scheduleTime").val("-1");
						$("#division").val("-1")
						$("#errorDatepicker").text("");
						$("#errorsellerTime").text("");
						$("#errorsellerdivision").text("");
					}

					function isValidDate(s) {
						var bits = s.split('/');
						var d = new Date(bits[2] + '/' + bits[1] + '/'
								+ bits[0]);
						return !!(d && (d.getMonth() + 1) == bits[1] && d
								.getDate() == Number(bits[0]));
					}

					function getFormateDate(date) {
						var d = new Date(date);
						return d;
					}

				});
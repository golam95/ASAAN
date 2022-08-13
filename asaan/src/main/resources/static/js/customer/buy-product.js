$(document)
		.ready(
				function() {

					if ($("#productName").val() == "") {
						$("#cartSummaryID").hide();
						$("#hiddenTextFiled").hide();
					} else {
						$("#cartSummaryID").show();
						$("#hiddenTextFiled").show();
					}

					$("#showCartItem").text("0");
					getCartQuantity();

					$("#cart").addClass('active');
					$("#tabCart").addClass('active');

					$('#tblbuyintProduct').DataTable();

					var whichRequest = $('#checkRequest').val();

					if (whichRequest == "product") {

						$("#product").addClass('active');
						$("#tabProduct").addClass('active');

					} else if (whichRequest == "profile") {

						$("#account-info").addClass('active');
						$("#tabAcc").addClass('active');

					} else {

						$("#product").addClass('active');
						$("#tabProduct").addClass('active');
					}

					$('#tblbuyintProduct tbody')
							.on(
									'click',
									'#edit',
									function() {

										var curRow = $(this).closest('tr');
										var productId = curRow.find('td:eq(0)')
												.text();
										var productName = curRow.find(
												'td:eq(1)').text();
										var productPrice = curRow.find(
												'td:eq(2)').text();
										var quantityStatus = curRow.find(
												'td:eq(3)').text();
										var quantity = $("#qnty").val();
										var price = 0;
										if ($("#activeUser").val() == '1') {

											if (quantityStatus == 'Available') {
												price = parseFloat(productPrice
														* quantity);
												addToCart(productId,
														productName, price,
														quantity);
											} else {
												alert('Sorry,this product Out Of Stock.Please buy another One!!');
											}

										} else {
											
											
											alert('Login please...!!');
											
										}

									});

					function addToCart(productId, productName, price, quantity) {

						var cartList = {};
						cartList.productId = productId;
						cartList.productName = productName;
						cartList.price = price;
						cartList.quantity = quantity;

						$.ajax({
							type : "POST",
							contentType : "application/json",
							url : "/customer/addCart",
							data : JSON.stringify(cartList),
							timeout : 100000,
							async : true,
							dataType : 'json',
							success : function(data) {
								if (data == '1') {
									alert("Product Add to Cart!!");
									getCartQuantity();
								}
							},
							error : function(xhr, status, error) {
								alert("Sorry,something wrong");
							}
						});
					}

					function getCartQuantity() {
						$.ajax({
							type : "GET",
							url : "/customer/totalCartItem",
							success : function(data) {
								$("#showCartItem").text(data);
							},
							error : function(xhr, status, error) {
								alert("Sorry,Something wrong!!");
							}
						});
					}

					// order

					$("#btnCheckOut")
							.click(
									function() {

										var flag = dataValidation();

										if (flag == true) {
											var productPrice = $(
													'#productPrice').val();
											var quantity = $('#quantity').val();
											var productName = $('#productName')
													.val();
											var country = $('#country').val();
											var town = $('#town').val();
											var zipcode = $('#zipcode').val();
											var shipingAddress = $(
													'#shipingAddress').val();

											var orderList = {};
											orderList.productPrice = productPrice;
											orderList.quantity = quantity;
											orderList.productName = productName;
											orderList.country = country;
											orderList.town = town;
											orderList.zipcode = zipcode;
											orderList.shipingAddress = shipingAddress;

											$
													.ajax({
														type : "POST",
														contentType : "application/json",
														url : "/customer/addOrder",
														data : JSON
																.stringify(orderList),
														timeout : 100000,
														async : true,
														dataType : 'json',
														success : function(data) {
															if (data == '1') {
																alert("Successfully added!!");
																clrField();
																window.location = 'http://localhost:8086/customer/buyProuct';
															}

														},
														error : function(xhr,
																status, error) {
															alert("Sorry,Someting Wrong!!");
														}
													});

										}

									});

					function dataValidation() {

						var status = true;

						if ($("#town").val() == "") {
							status = false;
							$("#errortown").text("*Required");
							$("#town").focus();
						} else
							$("#errortown").text("");

						if ($("#zipcode").val() == "") {
							status = false;
							$("#errorzipcode").text("*Required");
							$("#zipcode").focus();
						} else
							$("#errorzipcode").text("");

						if ($("#shipingAddress").val() == "") {
							status = false;
							$("#errorshipingAddress").text("*Required");
							$("#shipingAddress").focus();
						} else
							$("#errorshipingAddress").text("");

						return status;
					}

					$("#msgSendBuyer")
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

					function clrsendMsg() {
						$("#subject").val("");
						$("#comment").val("");
						$("#errormsgSubject").text("");
						$("#errormsgComment").text("");
					}

					function clrField() {
						$("#errortown").text("");
						$("#errorzipcode").text("");
						$("#errorshipingAddress").text("");
						//
						$("#town").val("");
						$("#zipcode").val("");
						$("#shipingAddress").val("");
						$("#productPrice").val("");
						$("#quantity").val("");
						$("#productName").val("");
					}

				});
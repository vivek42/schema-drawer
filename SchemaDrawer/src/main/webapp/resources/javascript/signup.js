$(document)
		.ready(
				function() {

					//$("#date").datepicker();

					// modify default settings for validation
					jQuery.validator
							.setDefaults({
								// where to display the error relative to the
								// element
								errorPlacement : function(error, element) {
									error.appendTo(element.parent().find(
											'div.error'));
								}
							});

					// custom validation method to make sure the date is in
					// mm/dd/yyyy format
					jQuery.validator.addMethod("usaDate", function(value,
							element) {
						var date = getDateFromFormat(value, 'MM/dd/yyyy');
						if (date == 0) {
							return false;
						}
						return true;
					}, "Please enter a date in the format mm/dd/yyyy");

					$("#profileCreation")
							.validate({
										rules : {

											username : "required",
											firstName : "required",
											lastName : "required",
											password : {
												required : true,
												minlength : 8,
												maxlength : 15
											},
											rpassword : {
												required : true,
												equalTo : "#password",
												minlength : 8,
												maxlength : 15
											},

											emailAddress : {
												required : true,
												email : true
											},

											dob : {
												required : true,
												usaDate : true
											},

											gender : "required",

										},

										// custom error messages
										messages : {
											email: "Please enter a valid email address",
//											rpassword : {
//												equalTo : "password and re-entered password do not match "
//											},
										},

										// on page submit 
										submitHandler : function(form) {
											alert('Form validation was a success, please proceed!');
											form.submit();
										}

									});

				});
$(document)
		.ready(
				function() {

					//$("#date").datepicker();

					jQuery.validator
							.setDefaults({
								errorPlacement : function(error, element) {
									error.appendTo(element.parent().find(
											'div.error'));
								}
							});

					// custom validation methods 
					jQuery.validator.addMethod("usaDate", function(value,
							element) {
						var date = getDateFromFormat(value, 'MM/dd/yyyy');
						if (date == 0) {
							return false;
						}
						return true;
					}, "Please enter a date in the format mm/dd/yyyy");
					
					jQuery.validator.addMethod("confirmPassword",function(value,
							element){
						if($("#rpassword").val() != value) {
							return false;
						}
						return true;
					}, "Password and Re-entered value do not match");

					$("#profileCreation")
							.validate({
										rules : {
											username : "required",
											firstName : "required",
											lastName : "required",
											password : {
												required : true,
												minlength : 8,
												maxlength : 15,
												confirmPassword :true
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
insert into user(user_id, username, password, status) values(1475941220985, 'test', '05a671c66aefea124cc08b76ea6d30bb', 'ACTIVE');
insert into role(role_id, roleName) values(1, 'admin');
insert into usersandroles(user_id, role_id) values(1475941220985,1);
insert into user_profile(user_id,firstname, lastname,email, dob, gender) values(1475941220985,'testfirst','testlast','test@test.com', 941342400000, 'male');


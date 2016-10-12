CREATE TABLE uploads (
  upload_id INT NOT NULL,
  content longblob NULL,
  upload_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP, file_name VARCHAR(45) NULL,
  PRIMARY KEY (upload_id));
CREATE TABLE events (
  event_id INT NOT NULL,
  event_code VARCHAR(50) NOT NULL,
  event_time DATETIME NOT NULL,
  message VARCHAR(45) NULL,
  stack_trace VARCHAR(45) NULL,
  application_name VARCHAR(45) NULL,
  PRIMARY KEY (event_id));
CREATE TABLE user (
user_id int not null,
username varchar(50) not null,
password varchar(75) not null,
status varchar(15) not null,
primary key (user_id)
);
CREATE TABLE role (
role_id int not null,
roleName varchar(50) not null,
primary key (role_id)
);
CREATE TABLE usersandroles (
user_id int not null,
role_id int not null,
primary key (user_id, role_id),
foreign key (user_id) references user(user_id) on delete cascade,
foreign key (role_id) references role(role_id) on delete cascade
);
CREATE TABLE user_profile (
user_id int not null,
firstname varchar(50) not null,
lastname varchar(50) not null,
email varchar(50) not null,
dob datetime not null, gender varchar(15) default 'male' not null,
primary key (user_id),
foreign key (user_id) references user(user_id) on delete cascade
);
CREATE TABLE user_upload_xref (
id int not null,
upload_id int not null,
user_id int not null,
primary key(id),
foreign key(upload_id) references uploads(upload_id) on delete cascade,
foreign key(user_id) references user(user_id) on delete cascade);

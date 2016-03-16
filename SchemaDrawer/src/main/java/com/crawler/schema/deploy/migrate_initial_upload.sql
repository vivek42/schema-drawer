CREATE TABLE uploads (
  upload_id INT NOT NULL,
  content longblob NULL,
  upload_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (upload_id));
  
ALTER TABLE uploads 
ADD COLUMN file_name VARCHAR(45) NULL;
  
CREATE TABLE Events (
  EVENT_ID INT NOT NULL,
  EVENT_CODE VARCHAR(50) NOT NULL,
  EVENT_TIME DATETIME NOT NULL,
  MESSAGE VARCHAR(45) NULL,
  STACK_TRACE VARCHAR(45) NULL,
  APPLICATION_NAME VARCHAR(45) NULL,
  PRIMARY KEY (EVENT_ID));
  
create table users (
  user_id int not null,
  username varchar(30) not null,
  password varchar(50) not null,
  create_date datetime not null default current_timestamp,
  email varchar(50) not null,
  primary key (user_id)
  );
  
create table permission_ref (
  permission_id int not null,
  name varchar(20) not null,
  description varchar(50) not null,
  primary key (permission_id)
  );
  
create table user_permission_xref (
  id int not null,
  permission_id int not null,
  user_id int not null,
  primary key (id),
  foreign key (permission_id) references permission_ref (permission_id) on delete cascade,
  foreign key (user_id) references users (user_id) on delete cascade
  );

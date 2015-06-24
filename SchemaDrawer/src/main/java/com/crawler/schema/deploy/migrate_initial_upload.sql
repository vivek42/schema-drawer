CREATE TABLE uploads (
  upload_id INT NOT NULL AUTO_INCREMENT,
  content VARCHAR(500) NULL,
  upload_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (upload_id));
  
ALTER TABLE uploads 
ADD COLUMN file_name VARCHAR(45) NULL AFTER upload_time;
  
CREATE TABLE Events (
  EVENT_ID INT NOT NULL,
  EVENT_CODE VARCHAR(50) NOT NULL,
  EVENT_TIME DATETIME NOT NULL,
  MESSAGE VARCHAR(45) NULL,
  STACK_TRACE VARCHAR(45) NULL,
  APPLICATION_NAME VARCHAR(45) NULL,
  PRIMARY KEY (EVENT_ID));

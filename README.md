# Schema Drawer
Draw schema diagrams from a SQLite database

## Abstract
The goal of this project is to create a web application which takes an uploaded a SQLite database file, and creates schema diagrams based on it. The application should offer these diagrams in different formats and should allow profile creation and maintainance to the users.

## Development
This project will be developed using agile methodology. The project will be divided into tasks/ goals, each task will have description, estimate, measurement of completion. The estimate will be given by number of weeks.

## Tasks
As mentioned above this project will be developed in stages or tasks. This project is divided into 5 tasks.
These tasks are:

### 1. Create basic web application using Spring MVC.
Under this task a simple web application will be implemented which should be able to consume a SQLite database file from     the user. This task is further divided into subtasks(each subtask is followed by their estimate)

  a. Setup Eclipse, install Spring tool kit in the eclipse, setup git as version control - 1 week
  
  b. Install MySql/ SQLite - 0.5 week
  
  c. Write a basic hello world web application using Spring MVC. - 1 week
  
  d. Add functionality of reading the data from text box from the home page and storing it in database. - 1 week
  
  e. Generate Exception events and store them in database for debugging. - 0.5 week
  
  f. Add file browser widget in the home page and store file content in the database. - 1 week 
  
  g. Display stored files in form of a clickable list in a separate page. - 1 week
  
  h. Display/ Preview content of the file when the link on the file name is clicked. - 1 week
  
  i. Download file. - 0.5 week 
  
  j. Design the layout of UI to make it look good. - 0.5 week 
  
#### Measurement 
  This task will be measured in terms of demo of the project given. The demo of the this project should have all functionalities covered by the subtasks. Estimate of task totals out to be 8 weeks.

### 2. Create build scripts to build, deploy, and to perform testing.
Under this task the web application created above will be deployed and tested with build scripts. This task is further divided into subtasks -

  a. Install Clover or similar software to check for code coverage. - 1 week
  
  b. Perform unit testing for every source file written so far. - 0.5 week
  
  c. Learn how to use Selenium, by writing a very basic test. - 2 weeks
  
  d. Write script to build and deploy this project from source. - 0.5 week
  
  e. Write Selenium script to check the content upload from text box. - 0.5 week
  
  f. Write Selenium script to check the content upload from file. - 0.5 week
  
  g. Write command line script to perform unit testing over the project which reports success/errors and also the time elapsed and code coverage. - 2 weeks
  
  h. Write command line script to perform Selenium testing which reports unexpected behavior and exception events. - 1 week
  
#### Measurement 
  This task will be measured in terms of code and documentation submitted. The documentation should contain step by step process of deployment and testing of the project. Deployed at remote location from scratch this project should be able to perform/display all sorts of testing covered in the subtasks. Estimate of the tasks totals out to be 8 weeks

### 3. Research SchemaCrawler to draw schema.
  a. Read about SchemaCrawler - 1 week
  
  b. Make a list of features needed from SchemaCrawler for the project - 1 week
  
  c. Proof of concept and validate if each feature is available - 2 weeks
  
### Measurement
  This task will be measured in terms of documentation. This documentation should contain a report which includes the proof of concept for applicability of SchemaCrawler in the project. It must contain every feature we require/achieve from SchemaCrawler as well as feature we need to develope separately. Estimate of the task is coming out to be 4 weeks.

### 4. Implement the functionality to upload files, and draw diagrams in the web application.
  a. Design Data model which will be used to draw the images from the database. - 0.5 week
  
  b. Integrate SchemaCrawler into the project. Perform unit testing as part of it. - 1 week
  
  c. Use SchemaCrawler to read the contents of the uploaded file. - 1 week
  
  d. Store the results in the database. - 0.5 week
  
  e. Design how the image of the schema should look like (color of the nodes, fonts of the text present etc.) - 1 week
  
  f. Research on best way to draw the images, as part of back end or through javascripts in front end itself. - 1 week
  
  g. Using database draw images with help of research done. - 2 weeks
  
#### Measurement 
  This task will be measured in terms of demo of the project given. The demo of the this project should have all functionalities covered by the subtasks. The demo for this subtask can be a selenium test. Estimate of task totals out to be 7 weeks.

### 5. Implement profile creation for users. 
  a. Extend the Data design to accomodate user profiles - 1 week
  
  b. Draw layout for the Registration page - 1 week
  
  c. Add/Implement registration page to the web application - 1 week
  
  d. Design layout for user profiles - 1 week
  
  e. Add/Implement user profile page - 1 week
  
  f. Add support for different extensions for images drawn. - 2 weeks
  
#### Measurement 
  This task will be measured in terms of demo of the project given. The demo of the this project should have all functionalities covered by the subtasks. The demo for this subtask can be a selenium test. Estimate of task totals out to be 7 weeks.
  
  

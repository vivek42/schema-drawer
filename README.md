# Schema Drawer
Draw schema diagrams from a SQLite database

## Abstract
The goal of this project is to create a web application which takes an uploaded a SQLite database file, and creates schema diagrams based on it. The application should offer these diagrams in different formats and should allow profile creation and maintainance to the users.

## Development
This project will be developed using agile methodology. The project will be divided into tasks/ goals, each task will have description, estimate, measurement of completion. The estimate will be given by number of weeks.

## Tasks
As mentioned above this project will be developed in stages or tasks. This project is divided into 5 tasks.
These tasks are:

### 1. Create basic web application using Spring MVC

Under this task a simple web application will be implemented which should be able to consume a SQLite database file from the user. 

  a. Setup Eclipse, install Spring Tool Kit in the Eclipse, setup git as version control - 1 week
     Completed.
  
  b. Install MySql/ SQLite - 1 week
     Completed.
  
  c. Write a basic hello world web application using Spring MVC - a single page, with a controller - 1 week
     Completed. Due Date - May 25 
  
  d. Add functionality of reading the data from text box from the home page and storing it in database - 1 week In progress . Due Date - June 1
  
  e. Create an event service, and store exception events in database for debugging - 1 week . Due Date June 8
  
  f. Add file browser widget to the home page and store file content in the database - 1 week . Due Date June 15
  
  g. Binary file to blob data. Done : July 26
  
  
#### Measurement 
This task will be measured in terms of demo of the project given. The demo of the this project should have all functionalities covered by the subtasks. Successful completion will involve having another engineer review and approve the demo. Estimate of task totals out to be 8 weeks. Due Date June 29 - pushed : July 26

### 2. Create build scripts to build, deploy, and to perform testing

Under this task the web application created above will be deployed and tested with build scripts. 

  a. Add Spring Security - 2 weeks . Due Date July 13 - Pushed August 10
    1. Add login page - Done
    2. Add dependencies and enable filter to make spring security active - August 6
    3. Use database to maintain credentials - August 10
  
  b. Install Clover or similar software to check for code coverage - 1 week . Due Date July 20 - Pushed Aug 10
  
  c. Perform unit testing for every source file written so far - 1 week . Due Date July 27 - Pushed Aug 17
  
  d. Learn how to use Selenium, by writing a very basic test - 2 weeks. Due Date Aug 10 - Pushed Sept 7
  
  e. Write Selenium script to check the content upload from text box - 1 week . Due Date Aug 17 - Pushed Sept 14
  
  g. Make sure the Gradle build performs unit testing (including Selenium testing) over the project which reports success/ errors and also the time elapsed and code coverage - 3 weeks. Due Date Sept 7 - Pushed Sept 21
  
#### Measurement 
This task will be measured in terms of code and documentation submitted. The documentation should contain step by step process of deployment and testing of the project. Deployed at remote location from scratch this project should be able to perform/ display all sorts of testing covered in the subtasks. Successful completion will involve having another engineer read the documentation, and build and deploy the project. Estimate of the tasks totals out to be 10 weeks. Due Date Sept 7 - Pushed Sept 21

### 3. Research SchemaCrawler to draw schema

  a. Read about SchemaCrawler - 1 week. Due Date Sept 14 - Pushed Sept 28
  
  b. Make a list of features needed from SchemaCrawler for the project - 1 week. Due Date Sept 21 - Pushed Oct 5
  
  c. Proof of concept and validate if each feature is available - 2 weeks. Due Date Oct 5 - Pushed Oct 19
  
### Measurement
This task will be measured in terms of documentation. This documentation should contain a report which includes the proof of concept for applicability of SchemaCrawler in the project. It must contain every feature we require/achieve from SchemaCrawler as well as feature we need to develop separately. Estimate of the task is coming out to be 4 weeks. Due Date Oct 5 - Pushed Oct 19

### 4. Implement the functionality to upload files, and draw diagrams in the web application

  a. Design data model which will be used to draw the images from the database - 1 week Due Date Oct 12 - Pushed Oct 26
  
  b. Integrate SchemaCrawler into the project - 1 week Due Date Oct 19 - Pushed Nov 2
  
  c. Store the diagram in the database - 1 week Due Date Oct 26 - Pushed Nov 9
  
  d. Design how the image of the schema should look like (color of the nodes, fonts of the text present etc.) - 1 week Due Date Nov 2
  
  e. Research on best way to draw the images, as part of back end or through javascripts in front end itself - 1 week Due Date Nov 9
  
  f. Create a new web page to allow users to upload a SQLite file, and view the diagram - 2 weeks Due Date Nov 23
  
  g. Deploy to "production", that is, a free hosting site that allows you to run a Java web application - 3 weeks Due Date Dec 14
  
#### Measurement 
This task will be measured in terms of demo of the project given. The demo of the this project should have all functionalities covered by the subtasks. The demo for this subtask can be a Selenium test. Successful completion will involve having another engineer use the live product. Estimate of task totals out to be 10 weeks.

### 5. Implement profile creation for users. 
  a. Extend the Data design to accomodate user profiles - 1 week Due Date Dec 21
  
  b. Draw layout for the Registration page - 1 week Due Date Dec 28
  
  c. Add/Implement registration page to the web application - 1 week Due Date Jan 4
  
  d. Design layout for user profiles - 1 week Due Date Jan 11
  
  e. Add/Implement user profile page - 1 week Due Date Jan 18
  
  f. Add support for different extensions for images drawn. - 2 weeks Due Date Feb 1 
  
#### Measurement 
  This task will be measured in terms of demo of the project given. The demo of the this project should have all functionalities covered by the subtasks. The demo for this subtask can be a selenium test. Estimate of task totals out to be 7 weeks.
  
  ####BackLog
  
  a. Move to gradle build from Maven.
  
  
  
  

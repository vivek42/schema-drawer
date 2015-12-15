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
  
####Status 
Completed
  
  
#### Measurement 
This task will be measured in terms of demo of the project given. The demo of the this project should have all functionalities covered by the subtasks. Successful completion will involve having another engineer review and approve the demo. Estimate of task totals out to be 8 weeks. Due Date June 29 - pushed : July 26


### 2. Add Spring Security and User Profiles

Under this task the web application created will be enhanced with Spring security, which means the web application will need authentication to proceed. User profiles will be a by product of this enhancement as the authentication will only be complete with user profiles.

  a. Add Spring Security without database authentication. 
  Due Date : August 10
  Status : Complete
  
  b. Add UI pages which includes login and logout, and update existing ui pages to show presence of session (logout button, and Username).
  Due Date : August 25
  Status : Complete
  
  c. Add Junit Testing for classes created so far.
  Due Date : August 31
  Status : Complete
  
  d. Refactor the code as testing proceeds.
  Due Date : Sep 7
  Status : Complete
  
  e. Add User Profile Page
  Due Date : Sept 28
  Status : Complete
  
  f. Junit Testing and Refactor
  Due Date : Oct 5
  Status : Complete
  
  g. Integration or Black Box Testing
  Due Date : Oct 12
  Status : Not Started
  
  h. Refactor the code as Testing Proceeds
  Due Date : Oct 19
  Status : Not Started
  
####Status 
Working
  
  
#### Measurement 
This task will be measured in terms of demo of the project given. The demo of the this project should have all functionalities covered by the subtasks. Successful completion will involve having another engineer review and approve the demo. Due Date Oct 19

  
### 3. Research SchemaCrawler to draw schema

  a. Read about SchemaCrawler - 1 week. 
  Due Date : Oct 25
  Status : Completed
  
  Pushing the dates back due to vacation and adding new tasks according to the needs.
  
  b. Re-read about SchemaCrawler - 1 week.
  Due Date : 21 Dec
  Status : Started
  
  b. Make a list of features needed from SchemaCrawler for the project - 1 week. 
  Due Date : 28 Dec
  Status : Not Started
  
  c. Proof of concept and validate if each feature is available - 1 week. 
  Due Date : 4 Jan
  Status : Not Started
  
### Measurement
This task will be measured in terms of documentation. This documentation should contain a report which includes the proof of concept for applicability of SchemaCrawler in the project. It must contain every feature we require/achieve from SchemaCrawler as well as feature we need to develop separately. Due Date Oct 5 - Pushed Nov 7 - Pushed again 4 Jan

### 4. Implement the functionality to upload files, and draw diagrams in the web application

  a. Design data model which will be used to draw the images from the database - 1 week 
  Due Date : Jan 11
  Status : Not Started
  
  b. Integrate SchemaCrawler into the project - 1 week 
  Due Date : Jan 18
  Status : Not Started
  
  c. Store the diagram in the database - 2 weeks 
  Due Date : 1 Feb
  Status : Not Started
  
  d. Design how the image of the schema should look like (color of the nodes, fonts of the text present etc.) - 1 week 
  Due Date : 8 Feb
  Status : Not Started
  
  e. Research on best way to draw the images, as part of back end or through javascripts in front end itself - 1 week 
  Due Date : 15 Feb
  Status : Not Started
  
  f. Create a new web page to allow users to upload a SQLite file, and view the diagram - 1 week 
  Due Date : 21 Feb
  Status : Not Started
  
#### Measurement 
This task will be measured in terms of demo of the project given. The demo of the this project should have all functionalities covered by the subtasks. The demo for this subtask can be a Selenium test. Successful completion will involve having another engineer use the live product. Due Date is Dec 25 - Pushed 15 Feb

### 4. Create build scripts to build, deploy, and to perform testing

Under this task the web application created above will be deployed and tested with build scripts. 

  a. Install Clover or similar software to check for code coverage - 1 week .
  Due Date : 21 Feb
  Status : Not Started
  
  b. Complete code coverage for the project - 1 week.
  Due Date : 28 Feb
  Status : Not Started
  
  c. Add Selenium Testing to the project - 1 week. 
  Due Date : Mar 6
  Status : Not Started
  
  e. Make sure the build performs unit testing (including Selenium testing) over the project which reports success/ errors and also the time elapsed and code coverage - 1 weeks. 
  Due Date : Mar 13
  Status : Not Started
  
    g. Deploy to "production", that is, a free hosting site that allows you to run a Java web application - 1 week   Due Date : 20 Mar
  Status : Not Started
  
#### Measurement 
This task will be measured in terms of code and documentation submitted. The documentation should contain step by step process of deployment and testing of the project. Deployed at remote location from scratch this project should be able to perform/ display all sorts of testing covered in the subtasks. Successful completion will involve having another engineer read the documentation, and build and deploy the project. Feb 19 - Pushed Feb 26 - Mar 20
  
####BackLog
  
a. Move to gradle build from Maven.
  
  
  
  

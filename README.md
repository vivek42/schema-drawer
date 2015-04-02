# Schema Drawer
Draw schema diagrams from a SQLite database

## Abstract
The goal of this project is to create a web application which takes an uploaded a SQLite database file, and creates schema diagrams based on it. The application should offer these diagrams in different formats and should allow profile creation and maintainance to the users.

## Development
This project will be developed using agile methodology. The project will be divided into tasks/ goals, each task will have description, estimate, measurement of completion. The estimate will be given by number of iterations, and for this project 1 iteration is 2 weeks.

## Tasks
As mentioned above this project will be developed in stages or tasks. This project is divided into 5 tasks.
These tasks are:

### 1. Create basic web application using Spring MVC.
Under this task a simple web application will be implemented which should be able to consume a SQLite database file from     the user. This task is further divided into subtasks

  a. Setup Eclipse, install Spring tool kit in the eclipse, setup git as version control
  b. Install MySql/ SQLite
  c. Write a basic hello world web application using Spring MVC.
  d. Add functionality of reading the data from text box from the home page and storing it in database.
  e. Generate Exception events and store them in database for debugging.
  f. Add file browser widget in the home page and store file content in the database.  
  g. Display stored files in form of a clickable list in a separate page.
  h. Display content of the file when the link on the file name is clicked.
  i. Design the layout of UI to make it look good.

### 2. Create build scripts to build, deploy, and to perform testing.
Under this task the web application created above will be deployed and tested with build scripts. This task is further divided into subtasks -

  a. Install Clover or similar software to check for code coverage.
  b. Perform unit testing for every source file written so far.
  c. Learn how to use Selenium, by writing a very basic test.
  d. Write script to build and deploy this project from source.
  e. Write Selenium script to check the content upload from text box.
  f. Write Selenium script to check the content upload from file.
  g. Write command line script to perform unit testing over the project which reports success/errors and also the time elapsed and code coverage.
  g. Write command line script to perform Selenium testing which reports unexpected behavior and exception events.

### 3. Research SchemaCrawler to draw schema.
  a. Read about SchemaCrawler
  b. Make a list of features needed from SchemaCrawler for the project
  c. Proof of concept and validate if each feature is available

### 4. Implement the functionality to upload files, and draw diagrams in the web application.

### 5. Implement profile creation for users. 

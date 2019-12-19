Getting Started
===============
Pre requisites:

* JDK 1.8 or above
* Maven 3 
 
 Assumptions:
 1. results created/updated when search article in google, shows results form other sources
 2. source and articles within 2 days are valid

How To Run Tests
=============
Tests can be run with Maven or with Intellij

1.Run with maven
---------------
 Open command prompt and navigate to pom.xml location ;
 Run "mvn clean integration-test"
 
 Run with Firefox / IE relavant drivers needs to be downloaded
 
 1.  mvn clean integration-test -Dbrowser.name=firefox
 2.  mvn clean integration-test -Dbrowser.name=ie
  
2.Run with intellij
  ---------------

1. Open "Edit Run Configurations" dialog.

Add the below to the defaults for Cucumber Java tests:

Main Class: cucumber.api.cli.Main
Glue: com.test

Run feature files from the context menu.

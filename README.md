# DesignProjectSpringStarter
This is an alternate Design Project starter kit that uses Maven (with wrapper) and SpringBoot.

## Basic Setup
Read this section first.  Take these steps as soon as you download the git repo.

### Prepare the Project
**BEFORE** you import the project into your IDE, I would recommend you do the following:
* Replace the text `TEAM-XX` in the `pom.xml` file with your own team identifier, such as
  `MUD-1` or `NutriApp-2` and so on.
* Replace the text `REPLACE-ME-WITH-PROJECT-NAME` in the `pom.xml` file with your own project name.
* Replace the text `REPLACE-ME-WITH-PROJECT-NAME` in the `application.properties` file with your own project name.
* Replace the text `YOUR PROJECT DESCRIPTION HERE` in the `pom.xml` file with your own project description.

### Using the Maven Wrapper
This project starter includes a Maven wrapper.  This means that Maven runs in a virtual
environment; not against your global Maven installation.  Use the `./mvnw` command in a *NIX
environment such as MacOS or Linux and use the `mvnw.cmd` in a Windows environment.

Learn more [here](https://maven.apache.org/wrapper/).

## Basic Build Commands
This section provides basic build commands for this project.

### Running the App
To run the Spring Boot application, use this command:

```shell
./mvnw spring-boot:run
```

This will also execute any `CommandLineRunner` implementation classes, such as
the sample `SampleCommandLineRunner` in this starter kit.

### Running Unit Tests
To run the complete suite of unit tests, use this command:

```shell
./mvnw clean test surefire-report:report
```

This generates the Surefire report (on unit tests) in the file
`target/site/surefire-report.html`.  It also generates the JaCoCo
(code coverage) report at: `target/site/jacoco/index.html`.

# mappe-2025-kevinholswilder

[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/NzRaDbQp)
# Portfolio project IDATT1003
This file uses Mark Down syntax. For more information see [here](https://www.markdownguide.org/basic-syntax/).

[//]: # (TODO: Fill inn your name and student ID)

STUDENT NAME = "Kevin Holswilder"  
STUDENT ID = "157339"

## Project description

Keeping track of recipes can become challenging as the collection grows. This project aims to address this problem by creating a register where chefs, hobbyists, and home cooks can organize and manage their recipes through entries in a trusted environment. Users can create, edit, delete, search and export entries, ensuring that their collection is always accessible for themselves and others.

Additionally, this program allows users to label recipes with tags (e.g., 'Keto', 'Halal'). This makes it easier for users to filter and find recipes that fit their diet.

*For visual representation of the project, see the [diagrams](https://github.com/NTNU-IDI/mappe-2025-kevinholswilder/tree/main/assets/diagrams).*

### Key features include:
* Create, edit, and delete recipe entries
* Search and filter recipes by the following:
    - Title
    - Label
    - Author
    - Date
    - Period
    - Prompt
* Display recipe entries

## Project structure

To maintain a clean and organized codebase, the project is structured into multiple packages, where each package has its own purpose.

### Package Descriptions
`enumerations`
</br>
Holds the global enums throughout the project, such as `RecipeLabels` which is used for labelling `DiaryEntry` objects.

`flow`
</br>
Manages the main flow of the program by providing methods to start each flow in the program.

`models`
</br>
Contains all models used in the program, such as `Author` and `DiaryEntry` who are responsible for representing the main objects stored in the register.

`register`
</br>
Holds all the registers that store the models into two separate registers. Additionally, it holds a `RegisterHandler` which provides access to both `DiaryRegister` and `AuthorRegister`.

`service`
</br>
Provides a service layer between the user interface and register.

`ui`
</br>
Contains the main user interaction for handling user input.

`utils`
</br>
Contains `UtilityManager` which provides general purpose methods that are being used all around the project.

`test`
</br>
Contains unit tests for the project, organized to mirror the structure of the main code. Each test class verifies both the functionality and behaviour of the methods that are used in the codebase. The tests are being ran through `MainTest` which is the program's entry point for running the unit tests.

It tests the functionality of the following classes:
* `models`
    - Author
    - DiaryEntry
* `register`
    - DiaryRegister
    - AuthorRegister
    - RegisterHandler
* `ui`
    - ConsoleUI
* `utils`
    - UtilityManager

### Directory Tree
```
src/
    ├── main/
    |    └── java/
    |        └── edu/
    |            └── ntnu/
    |                └── iir/
    |                    └── bidata/
    |                        ├── enumerations/
    |                        |    └── RecipeLabel.java
    |                        ├── flow/
    |                        |    ├── FlowHandler.java
    |                        |    └── MenuDisplay.java
    |                        ├── models/
    |                        |    ├── Author.java
    |                        |    └── DiaryEntry.java
    |                        ├── register/
    |                        |    ├── AuthorRegister.java
    |                        |    ├── DiaryRegister.java
    |                        |    └── RegisterHandler.java
    |                        ├── service/
    |                        |    ├── DiaryService.java
    |                        |    └── UserService.java
    |                        ├── ui/
    |                        |    ├── diary/
    |                        |    |    ├── DiaryCreateUI.java
    |                        |    |    ├── DiaryDeleteUI.java
    |                        |    |    ├── DiaryEditUI.java
    |                        |    |    ├── DiaryExportUI.java
    |                        |    |    ├── DiaryHelper.java
    |                        |    |    └── DiarySearchUI.java
    |                        |    ├── user/
    |                        |    |    └── UserUI.java
    |                        |    ├── ConsoleUI.java
    |                        |    └── InteractionKeys.java
    |                        ├── utils/
    |                        |    └── UtilityManager.java
    |                        └── Main.java
    └── test/
        └── java/
            ├── models/
            |    ├── AuthorTest.java
            |    └── DiaryEntryTest.java
            ├── register/
            |    └── RegisterTest.java
            ├── ui/
            |    └── ConsoleUITest.java
            ├── utils/
            |    └── UtilityManagerTest.java
            └── MainTest.java
.gitignore
README.md
```

## Link to repository

[mappe-2025-kevinholswilder](https://github.com/NTNU-IDI/mappe-2025-kevinholswilder)

## Dependencies
This project uses the following dependencies:
| Dependency | Version |
|-----------------|----------------|
| JUnit4   | 4.13.1  |


## How to run the project

*Note: Before running the project, make sure to have Java 21 installed as it is the version the project was built on.*

There are two methods to run the application:

### Method 1.
*Noe: Before running the project, be sure to add JUnit4 to the classpath as the project does not use a build tool.*
Clone the project in a preferred IDE, and run the application from the Main class (`main/java/edu/ntnu/iir/bidata/Main.java`).

### Method 2.
Download the latest version of the application in [releases](https://github.com/NTNU-IDI/mappe-2025-kevinholswilder/releases).

Once downloaded, open up `Command Prompt`, by either pressing `Windows Key + R`, and then proceed to type `cmd`, followed by pressing `Enter`, or open it up through the `Windows Search Bar`.

In the `Command Prompt`, you will first have to navigate to the directory where the .jar file is located. This can be done by using the `cd` command, followed by the location of the .jar file.

Once located, you can run the application by using the following command:
</br>
`java -jar cooking-diary-v<version>.jar`

*Note: you have to replace `<version>` with the version that was downloaded.*
</br>
**Example:** `java -jar cooking-diary-v1.0.0.jar`

## How to run the tests
As mentioned, the following classes are being tested:
* `models`
  - Author
  - DiaryEntry
* `register`
  - DiaryRegister
  - AuthorRegister
  - RegisterHandler
* `ui`
  - ConsoleUI
* `utils`
  - UtilityManager

To run the tests, open up `Command Prompt` and navigate to the directory where the .jar file is located. 

Then run the following command:
</br>
`java -cp cooking-diary-v<version>.jar test.java.edu.ntnu.iir.bidata.MainTest`

Again, replace `<version>` with the version you downloaded.

Alternatively, you can also run the tests from within the IDE by locating the MainTest class (`test/java/MainTest.java`).

## References
The following references/sources were used in the project:
* [Conventional Commits](https://www.conventionalcommits.org/en/v1.0.0/)
* [GitHub Tree](https://githubtree.mgks.dev/)
* [Java Access Control](https://docs.oracle.com/javase/tutorial/java/javaOO/accesscontrol.html)
* [Singleton Method Design Pattern in Java](https://www.geeksforgeeks.org/java/singleton-class-java/)
* [JUnit User Guide](https://docs.junit.org/current/user-guide/)
* [ISO 8601 Date and time format](https://www.iso.org/iso-8601-date-and-time-format.html)
* [How to Write Doc Comments for the Javadoc Tool](https://www.oracle.com/technical-resources/articles/java/javadoc-tool.html)
* [Google Java Style](https://google.github.io/styleguide/javaguide.html)

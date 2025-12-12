# College Expense Tracker

## Application Description
College Expense Tracker is an application that helps college students manage their finances and keep track of all their budgets. 
With packed schedules filled with exams, assignments, extracurriculars, socializing, and more, college students often face difficulties overseeing their day-to-day expenses. 
This tracker organizes different expenses in a comprehensive interface that students can easily use. 
The system allows users to add new expenses, delete existing ones, and categorize each entry into groups such as food, tuition, transportation, housing, etc. 
Students can also add notes to individual expenses for additional context. Students can then view all expenses and optionally filter them by category to help users analyze their spending habits. 
The system will store all expense data in a file for future retrieval. 
This application will include multiple Graphical User Interfaces (GUIs) and will be implemented using Java.

## Group Members
- [Jessica Chen](https://github.com/jessicahc) (jhc10024)
- [Terry Cao](https://github.com/cao-exe) (tc3661)
- [Jennifer Yu](https://github.com/jenniferyuuu) (jy4172)

## Java Version Requirement & Dependency
- This GitHub repository name for this project is: *college-expense-tracker*  (use dash)    
- The project directory containing Java source code is: *college_expense_tracker*  (use underscore)
- This application requires Java SE version 9 or later
- This application has a dependency on the library jar file *LGoodDatePicker-11.2.1.jar*, which is located in *college_expense_tracker/lib*

## How to Build and Run

There are 3 options to build and run the application as described below:

**Option 1**: On Linux or Mac, use the Makefile located inside the project directory (*college_expense_tracker/Makefile*)  
After you clone the project repository using the GitHub URL, go to the project repository on your local computer (e.g. */student1/git/college-expense-tracker*). Then, go to the project directory *college_expense_tracker*, execute the command ***make all*** to compile the application, followed by the command ***make run*** to run the application. For example,
```
	cd /student1/git/college-expense-tracker
	cd college_expense_tracker
	make all
	make run
```
Note: After compilation, the new directory *bin* will be created under the project directory (*college_expense_tracker/bin*) to store all output .class files.  

**Option 2**: Manually compile and run the application using Java commands:
After you clone the project repository using the GitHub URL, go to the project repository on your local computer (e.g. */student1/git/college-expense-tracker*). Then, go to the project directory, use the ***javac*** command to compile the application, followed by the ***java*** command to run the application. For example:
```
	cd /student1/git/college-expense-tracker
	cd college_expense_tracker
	javac -cp lib/LGoodDatePicker-11.2.1.jar -d bin src/main/*.java
	java -cp bin:lib/LGoodDatePicker-11.2.1.jar main.Main
```
Note: After compilation, the new directory *bin* will be created under the project directory (*college_expense_tracker/bin*) to store all output .class files.

**Option 3**: Use *Eclipse IDE for Java Developer* (see Project Report)
This project contains the following configuration files under the project directory, which allow you to import the entire project into Eclipse IDE via the GitHub URL. 
```
	college_expense_tracker/.project
	college_expense_tracker/.classpath
	college_expense_tracker/.settings
```
The configuration file *.classpath* is defined to use Java SE 21 to compile and run the application by default. If your Eclipse IDE does not support Java SE 21, then you can either update your Eclipse to its latest version, or after you have imported this project into your Eclipse, you need to modify the "Java Compiler" and "Java Build Path" settings for this project to the appropriate Java version available in your Eclipse environment (must use Java SE 9 or later). See details in Project Report.

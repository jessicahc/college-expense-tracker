# ===========================================
# Makefile for college_expense_tracker project
# Place this file inside: college_expense_tracker/
# ===========================================

SRC_DIR = src
BIN_DIR = bin
LIB_DIR = lib

# Main class (package must match Main.java)
MAIN_CLASS = main.Main

# External dependency JAR
JAR = $(LIB_DIR)/LGoodDatePicker-11.2.1.jar

# All Java sources
SOURCES := $(shell find $(SRC_DIR) -name "*.java")

# Classpath includes compiled classes + external JAR
CLASSPATH = $(BIN_DIR):$(JAR)

# Default target
all: compile

# Compile all .java files into bin/
compile:
	@mkdir -p $(BIN_DIR)
	javac -cp $(JAR) -d $(BIN_DIR) $(SOURCES)

# Run the application
run:
	java -cp $(CLASSPATH) $(MAIN_CLASS)

# Delete all compiled classes
clean:
	rm -rf $(BIN_DIR)

.PHONY: all compile run clean

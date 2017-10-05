# Makefile for Data Structures Binary Search Tree Assignments

LIB = ../lib
SRCDIR = src
BINDIR = bin
TESTDIR = test
DOCDIR = doc

CLI = $(LIB)/cli/commons-cli-1.3.1.jar
ASM = $(LIB)/asm/asm-5.0.4.jar:$(LIB)/asm/asm-commons-5.0.4.jar:$(LIB)/asm/asm-tree-5.0.4.jar
JUNIT = $(LIB)/junit/junit-4.12.jar:$(LIB)/junit/hamcrest-core-1.3.jar
JACOCO = $(LIB)/jacoco/org.jacoco.core-0.7.5.201505241946.jar:$(LIB)/jacoco/org.jacoco.report-0.7.5.201505241946.jar:
TOOLS = $(LIB)/tools

JAVAC = javac
JFLAGS = -g -d $(BINDIR) -cp $(BINDIR):$(JUNIT)


vpath %.java $(SRCDIR)/models:$(SRCDIR)/xmlparser:$(SRCDIR):$(TESTDIR)
vpath %.class $(BINDIR)/models:$(BINDIR)/xmlparser:$(BINDIR):$(BINDIR)

# define general build rule for java sources
.SUFFIXES:  .java  .class

.java.class:
	$(JAVAC)  $(JFLAGS)  $<

#default rule - will be invoked by make


all: Position.class Node.class LinkedBinaryTree.class BinaryTreeWriter.class SimpleTreeWriter.class \
	BinarySearchTree.class BSTNode.class LinkedBST.class \
	BSTHarness.class

# The following two targets deal with the mutual dependency between Position and BinaryTree
Position.class: BinaryTree.class

BinaryTree.class:
	rm -rf $(BINDIR)/binarytree/Position.class $(BINDIR)/binarytree/BinaryTree.class
	javac $(JFLAGS) $(SRCDIR)/binarytree/Position.java $(SRCDIR)/binarytree/BinaryTree.java
	
		
# Rules for generating documentation
doc:
	javadoc -d $(DOCDIR) $(SRCDIR)/binarytree/*.java $(SRCDIR)/binarysearchtree/*.java $(SRCDIR)/avltree/*.java

# Rules for unit testing
# Add additional Testxx.class file to this line and to TestSuite.java
test_classes: all TestSuite.class

test: test_classes
	java -ea -cp $(BINDIR):$(JUNIT) org.junit.runner.JUnitCore TestSuite
	
# Rules for generating tests
jacoco.exec: test_classes
	java -ea -javaagent:$(LIB)/jacoco/jacocoagent.jar -cp $(BINDIR):$(JUNIT) org.junit.runner.JUnitCore TestSuite

report: jacoco.exec
	java -cp $(BINDIR):$(CLI):$(JACOCO):$(ASM):$(TOOLS) Report --reporttype html .

clean:
	@rm -f  $(BINDIR)/*.class
	@rm -f $(BINDIR)/*/*.class
	@rm -f jacoco.exec *.xml *.csv
	@rm -Rf coveragereport
	@rm -Rf doc

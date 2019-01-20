#!/bin/bash
resize -s 40 100

javac -cp lanterna.jar:. SpaceInvaders.java
java -cp lanterna.jar:. SpaceInvaders

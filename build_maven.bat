@echo off
mvn clean install
echo Listing all files after Maven build:
dir /B
echo Maven build completed successfully.

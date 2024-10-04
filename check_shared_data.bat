@echo off
echo Checking shared data on host:
dir C:\tmp\shared_data
echo Checking shared data in MySQL container:
docker exec myproject-mysql dir /shared_data
echo Checking shared data in Spring Boot container:
docker exec myproject1-container dir /app/shared_data
echo Shared data check completed successfully.

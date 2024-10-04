@echo off
docker logs myproject1-container || echo Failed to retrieve Spring Boot logs
echo Spring Boot logs checked successfully.

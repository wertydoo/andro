docker build -t andro-app .
docker run^
 -e CONFIG_USER_NAME=developer^
 -e CONFIG_PASSWORD=osai@illinois^
 -e CONFIG_SERVER_URL=https://dev.config-server.thomas-driscoll.com/^
 -p 8019:8018 andro-app
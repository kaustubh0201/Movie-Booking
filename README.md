<!-- ABOUT THE PROJECT -->
# Movie Booking Service

REST APIs to get different movies in various cities made using Spring Boot framework, Mongo Database, and Redis Cache.JSON Web Token have been used for authentication and authorization.

<hr>

<!-- GETTING STARTED -->
## Getting Started

Please follow the code snippets below to set up the environment for running the project on the local machine.

### Prerequisites

* <b>Maven</b>

  This <a href="https://www.digitalocean.com/community/tutorials/install-maven-linux-ubuntu">reference</a> can also be used for installing maven on the local system.

  ```sh
  > sudo apt update
  > sudo apt install maven
  > mvn -version
  ```
* <b>MongoDB</b>

  This <a href="https://www.mongodb.com/docs/manual/tutorial/install-mongodb-on-ubuntu/">reference</a> can also be used to install MongoDB.

  ```sh
  > sudo apt-get install gnupg curl
  > curl -fsSL https://pgp.mongodb.com/server-7.0.asc | \
   sudo gpg -o /usr/share/keyrings/mongodb-server-7.0.gpg \
   --dearmor
  > echo "deb [ arch=amd64,arm64 signed-by=/usr/share/keyrings/mongodb-server-7.0.gpg ] https://repo.mongodb.org/apt/ubuntu jammy/mongodb-org/7.0 multiverse" | sudo tee /etc/apt/sources.list.d/mongodb-org-7.0.list
  > sudo apt-get update
  > sudo apt-get install -y mongodb-org
  > mongo --version
  ```
* <b>Redis</b>

    This <a href="https://redis.io/docs/install/install-redis/install-redis-on-linux/">reference</a> can also be used to install Redis.

    ```sh
    > curl -fsSL https://packages.redis.io/gpg | sudo gpg --dearmor -o /usr/share/keyrings/redis-archive-keyring.gpg
    > echo "deb [signed-by=/usr/share/keyrings/redis-archive-keyring.gpg] https://packages.redis.io/deb $(lsb_release -cs) main" | sudo tee /etc/apt/sources.list.d/redis.list
    > sudo apt-get update
    > sudo apt-get install redis
    ```
    
### Installation

<i>Ensure that <b>mongodb</b> is running on <b>mongodb://localhost:27017</b> and <b>redis</b> running on <b>localhost:6379</b>.</i>

1. Clone the repo
   ```sh
   git clone https://github.com/kaustubh0201/Movie-Booking.git
   ```
2. Install `maven dependencies` from `pom.xml` file.
   ```sh
   mvn install
   ```
3. Start the spring boot application
   ```sh
   mvn spring-boot:run
   ```

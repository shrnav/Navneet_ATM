# Navneet_ATM

ATM project for Zinkwork

In order to clone the project :

> git clone https://github.com/shrnav/Navneet_ATM.git

-------------------------------------------------------

# Publicly available Docker images::

---------------------------------------------------------

navneet204/atm-namingserver-banner:latest

navneet204/atm-gateway-banner:latest

navneet204/atm-api-banner:latest

navneet204/atm-client-banner:latest

--------------------------------------------------------

# Ports used:

---------------------------------------------------------

atm-api : 8080

atm-client : 8888

atm-namingserver : 8761

api-gateway : 8766

-----------------------------------------------------------

# Quickest way to execute the ATM project (Make sure your machine is installed with docker):

-----------------------------------------------------------------------------------------
1) Go to the path where docker-compose-only-execute.yml exist.

2) Execute the below command to up all the required services.

>docker-compose -f docker-compose-only-execute.yml up

if you want to build and run the image (both) then go to the path where docker-compose.yml exist and execute. (Make sure you have taken the latest code and build via mvm clean install).

>docker-compose up


3) Hit the URL http://localhost:8761 to confirm if all three services are up.

4) Execute some queries to create the account or upload money in ATM.

To create Account : http://localhost:8080/account/create/{accountNumber}/{pin}/{openingBal}/{overDraft}

To add money in ATM :: http://localhost:8080/atm/create/{initAmt}/{fifties}/{twenties}/{tens}/{fives}/{remainAmt}

URL Via API gateway :

------------------------

Withdraw account : http://localhost:8766/ATM-CLIENT/account-client/withdrawAccount/{accountNumber}/{pin}/{amountTobeWithdraw}

Get the remaining balance : http://localhost:8766/ATM-CLIENT/account-client/getBalance/{accountNumber}/{pin}

Delete the account : http://localhost:8766/ATM-CLIENT/account-client/deleteAccount/{accountNumber}

Account information : http://localhost:8766/ATM-CLIENT/account-client/accountInfo/{accountNumber}

ATM information : http://localhost:8766/ATM-CLIENT/atm-client/atminfo

5) Use below command, in order to down the services

>docker-compose down.

-------------------------------------------------------------------------------------

# Technologies used :

-------------------------------------------------------------------------------------------

Core Java,

Spring boot

Lombok

JPA

h2 ( for demo purpose only).

Tomcat as web server.

Docker for containerization.

Idea Intellej

Test coverage via Intellej

Git for version control.

Maven as build tool.

Docker file, Docker compose and  Jib client to create and execute docker image. 

Eureka discovery client

Feign Client

Dev tools

API Gateway

For security : We can use okta.

------------------------------------------------------------------------------------------

# Other ways to execute the project.

1) Clone the project

2) Build all projects using mvn clean install

3)Open command prompt and start all services via

> mvn spring-boot:run

-------------------------------------------------------------------------------

Check the load balancer (Get the ATM info):

1) Start two instance of atm-api
2)Make sure you have executed below url twice (via 8080 and 8081)to insert row into ATMInfo table
http://localhost:8081/atm/create/1500/30/30/20/10/1500
http://localhost:8081/atm/create/1500/30/30/20/10/1500

3) Hit the URL (via API gateway) http://host.docker.internal:8766/ATM-CLIENT/atm-client/atminfo

--------------------------------------------------------------------------------

# Other useful information::

------------------------------------------------------------

Creation of docker images using Dockerfile.

1) Create the docker image

Ex : docker build --tag=atm-api:latest .

2.1) You can run the image using below command.

Ex: docker run -p8887:8888 atm-api:latest

2.2) You can check running container.

docker container ls

2.3) You can kill the container

docker stop containerid

4)If you want to execute more than one image in one shot then use docker compose.

create docker compose file, and place it on the parent folder where your Docker file exist.

5) Check whether you created correct compose file.

docker-compose config

6) Run the docker compose file.

docker-compose up --build

7) Down the docker compose file.

docker-compose down.

You can find detail information in this https://www.baeldung.com/dockerizing-spring-boot-application article.

------------------------------------------------------------------------------

# SONAR SUPPORT::

Added Sonar support to check code coverage.

After starting Sonar cube server.

Execute below command 

mvn sonar:sonar -Dsonar.host.url=http://localhost:9000 -Dsonar.login=generated_token

browse localhost:9000. You will see navneet-atm project listed.

----------------------------------------------------------

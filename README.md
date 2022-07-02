# Navneet_ATM
ATM project for Zinkwork

Created docker image and uploaded on docker hub, as local image.
Image name : navneet204/navneet-atm:latest

Command to run docker image : docker run -p 8080:8080 navneet204/navneet-atm:latest

Urls:
To create Account : http://localhost:8080/account/create/{accountNumber}/{pin}/{openingBal}/{overDraft}

To delete the account : http://localhost:8080/account/delete/{accountNumber}

To add money in ATM :: http://localhost:8080/atm/create/{initAmt}/{fifties}/{twenties}/{tens}/{fives}/{remainAmt}

To get the info about ATM money : http://localhost:/atm/get

To get the account balance : http://locahost:8080//account/getBal/{accountNumber}/{pin}

To withdraw the amount from account : http://locahost:8080//account/getWithdraw/{accountNumber}/{pin}/{amount} 

Check the loadbalancer (Get the ATM info):

1) Start two instance of atm-api
2)Make sure you have executed below url twice (via 8080 and 8081)to insert row into ATMInfo table
http://localhost:8081/atm/create/1500/30/30/20/10/1500
http://localhost:8081/atm/create/1500/30/30/20/10/1500

3) Hit the url (via API gateway) http://host.docker.internal:8766/ATM-CLIENT/atm-client/atminfo
========================================================================

Creation of docker images using Dockerfile.

1) Create the docker image

Ex : docker build --tag=atm-api:latest .

2.1) You can run the image using below command.

Ex: docker run -p8887:8888 atm-api:latest

2.2) You can check running container.

docker conatiner ls

2.3) You can kill the container

docker stop containerid

4)If you want to execute more than one image in one shot then use docker compose.

create docker compose file.and place it on the parent folder where your Docker file exist.

5) Check whether you created correct compose file.

docker-compose config

6) Run the docke compose file.

docker-compose up --build

7) Down the docker compose file.

docker-compose down.

You can find detail information in this https://www.baeldung.com/dockerizing-spring-boot-application article.
=================================================================================





========================================================================

Ports used:

atm-api : 8080
atm-client : 8888
atm-namingserver : 8761
api-gateway : 8766

=========================================================================

Technologies used :
=========================================
Core Java,

Spring boot

Lombok

JPA

h2 ( for demo purpose only).

Tomcat as web server.

Docker for containerization.

Intellej

Test coverage via Intellej

Git for version control.

Maven as build tool.

Jib client to create docker image.

Eureka discovery client

Feign Client

Dev tools

API Gateway
=================================================

SONAR SUPPORT::

Added Sonar support to check code coverage.

After starting Sonar cube server.
Execute below command 
mvn sonar:sonar -Dsonar.host.url=http://localhost:9000 -Dsonar.login=generated_token
browse localhost:9000. You will see navneet-atm project listed.
===================================================

Note : Please use attached settings.xml for build purpose. As I a using profile "docker" to create the docker image using jib client.If you want to create the docker image then uncomment the active profile section.

Note : If you are seeing Lombok related issue after importing the project on intellej. Make sure you have followed steps.

**Ticking the "Enable annotation processing" checkbox in Settings->Compiler->Annotation Processors.
and
Install the plugin of Lombok for idea and restart for change to take effect.**




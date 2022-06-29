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


Technologies used :

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

Note : Please use attached settings.xml for build purpose. As I a using profile "docker" to create the docker image using jib client.If you want to create the docker image then uncomment the active profile section.

Note : If you are seeing Lombok related issue after importing the project on intellej. Make sure you have followed steps.

**Ticking the "Enable annotation processing" checkbox in Settings->Compiler->Annotation Processors.
and
Install the plugin of Lombok for idea and restart for change to take effect.**


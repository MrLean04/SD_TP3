#!/usr/bin/env bash
url=l040101-ws09.ua.pt/sd405/
username=sd405
password=qwerty
registryHostName=l040101-ws09.ua.pt
RepoHostName=l040101-ws04.ua.pt
PlaneHostName=l040101-ws02.ua.pt
DepartureAirportHostName=l040101-ws01.ua.pt
DestinationAirportHostName=l040101-ws03.ua.pt
HostessHostName=l040101-ws05.ua.pt
PilotHostName=l040101-ws07.ua.pt
PassengerHostName=l040101-ws06.ua.pt
registryPortNum=22447

sshpass -p $password ssh -o StrictHostKeyChecking=no -f $username@$registryHostName "cd Public ; rm -rf src ; rm deploy.tar.gz "
sshpass -p $password ssh -o StrictHostKeyChecking=no -f $username@$RepoHostName "cd Public ; rm -rf src ; rm deploy.tar.gz "
sshpass -p $password ssh -o StrictHostKeyChecking=no -f $username@$PlaneHostName "cd Public ; rm -rf src ; rm deploy.tar.gz  "
sshpass -p $password ssh -o StrictHostKeyChecking=no -f $username@$DepartureAirportHostName "cd Public ; rm -rf src ; rm deploy.tar.gz  "
sshpass -p $password ssh -o StrictHostKeyChecking=no -f $username@$DestinationAirportHostName "cd Public ; rm -rf src ; rm deploy.tar.gz "
sshpass -p $password ssh -o StrictHostKeyChecking=no -f $username@$HostessHostName "cd Public ; rm -rf src ; rm deploy.tar.gz  "
sshpass -p $password ssh -o StrictHostKeyChecking=no -f $username@$PilotHostName "cd Public ; rm -rf src ; rm deploy.tar.gz  "
sshpass -p $password ssh -o StrictHostKeyChecking=no -f $username@$PassengerHostName "cd Public ; rm -rf src ; rm deploy.tar.gz "
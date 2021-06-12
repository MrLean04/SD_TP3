#!/usr/bin/env bash

cd Assignment/src

find /Users/francisco/Desktop/SD/SD_TP3/Assignment/ -name "*.java" > sources.txt
javac -cp genclass.jar:./ @sources.txt
rm sources.txt

#javac -cp "Assignment/src/genclass.jar" Assignment/src/interfaces/*.java  Assignment/src/registry/*.java Assignment/src/entities/Passenger/*.java Assignment/src/entities/Passenger/Interfaces/*.java  Assignment/src/entities/Passenger/States/*.java Assignment/src/entities/Hostess/*.java Assignment/src/entities/Hostess/Interfaces/*.java Assignment/src/entities/Hostess/States/*.java Assignment/src/entities/Pilot/*.java Assignment/src/entities/Pilot/Interfaces/*.java Assignment/src/entities/Pilot/States/*.java Assignment/src/shared/Plane/*.java Assignment/src/shared/DepartureAirport/*.java Assignment/src/shared/DestinationAirport/*.java Assignment/src/shared/Repo/*.java

mkdir deploy
mkdir deploy/interfaces
mkdir deploy/registry
mkdir deploy/shared
mkdir deploy/shared/Repo
mkdir deploy/shared/Plane
mkdir deploy/shared/DepartureAirport
mkdir deploy/shared/DestinationAirport
mkdir deploy/entities/
mkdir deploy/entities/Hostess
mkdir deploy/entities/Pilot
mkdir deploy/entities/Passenger
mkdir deploy/entities/Hostess/Interfaces
mkdir deploy/entities/Hostess/States
mkdir deploy/entities/Pilot/Interfaces
mkdir deploy/entities/Pilot/States
mkdir deploy/entities/Passenger/Interfaces
mkdir deploy/entities/Passenger/States

cp interfaces/*.class deploy/interfaces/

cp registry/*.class deploy/registry/

cp shared/Repo/*.class deploy/shared/Repo/
cp shared/Plane/*.class deploy/shared/Plane/
cp shared/DepartureAirport/*.class deploy/shared/DepartureAirport/
cp shared/DestinationAirport/*.class deploy/shared/DestinationAirport/


cp entities/Hostess/*.class deploy/entities/Hostess/
cp entities/Pilot/*.class deploy/entities/Pilot/
cp entities/Passenger/*.class deploy/entities/Passenger/

cp entities/Hostess/Interfaces/*.class deploy/entities/Hostess/Interfaces/
cp entities/Pilot/Interfaces/*.class deploy/entities/Pilot/Interfaces/
cp entities/Passenger/Interfaces/*.class deploy/entities/Passenger/Interfaces/

cp entities/Hostess/States/*.class deploy/entities/Hostess/States/
cp entities/Pilot/States/*.class deploy/entities/Pilot/States/
cp entities/Passenger/States/*.class deploy/entities/Passenger/States/

cd ../..
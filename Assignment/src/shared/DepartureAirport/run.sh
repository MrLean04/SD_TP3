#!/bin/bash
rm -r classes;
mkdir classes;
javac -d "classes" -cp "../../genclass.jar" ../../entities/Hostess/*.java ../../entities/Hostess/Interfaces/*.java  ../../entities/Hostess/States/*.java ../../entities/Pilot/*.java ../../entities/Pilot/Interfaces/*.java ../../entities/Pilot/States/*.java ../../entities/Passenger/*.java ../../entities/Passenger/Interfaces/*.java ../../entities/Passenger/States/*.java ../../communication/*.java ../../messages/DepartureAirportMessages/*.java ../../messages/DestinationAirportMessages/*.java ../../messages/PlaneMessages/*.java ../../messages/RepoMessages/*.java ../../shared/DepartureAirport/*.java ../../shared/DestinationAirport/*.java ../../shared/Plane/*.java ../../shared/Repo/*.java;
java -classpath "classes" shared.DepartureAirport.DepartureServer;
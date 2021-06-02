#!/usr/bin/env bash
url=http://l040101-ws09.ua.pt/sd405/src/
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

echo "Building..."

bash build.sh

echo " "
echo "Compressing..."

cd Assignment/

tar -czf deploy.tar.gz src/

cd ..

sleep 5
   
echo " "
echo "Sending compressed project to the remote machines..."

sshpass -p $password scp Assignment/deploy.tar.gz $username@$registryHostName:~/Public
sshpass -p $password ssh -o StrictHostKeyChecking=no -f $username@$registryHostName "cd ~/Public ; tar -xmzf deploy.tar.gz" &

sshpass -p $password scp Assignment/deploy.tar.gz $username@$RepoHostName:~/Public
sshpass -p $password ssh -o StrictHostKeyChecking=no -f $username@$RepoHostName "cd ~/Public ; tar -xmzf deploy.tar.gz" &

sshpass -p $password scp Assignment/deploy.tar.gz $username@$PlaneHostName:~/Public
sshpass -p $password ssh -o StrictHostKeyChecking=no -f $username@$PlaneHostName "cd ~/Public ; tar -xmzf deploy.tar.gz" &

sshpass -p $password scp Assignment/deploy.tar.gz $username@$DepartureAirportHostName:~/Public
sshpass -p $password ssh -o StrictHostKeyChecking=no -f $username@$DepartureAirportHostName "cd ~/Public ; tar -xmzf deploy.tar.gz" &

sshpass -p $password scp Assignment/deploy.tar.gz $username@$DestinationAirportHostName:~/Public
sshpass -p $password ssh -o StrictHostKeyChecking=no -f $username@$DestinationAirportHostName "cd ~/Public ; tar -xmzf deploy.tar.gz" &

sshpass -p $password scp Assignment/deploy.tar.gz $username@$HostessHostName:~/Public
sshpass -p $password ssh -o StrictHostKeyChecking=no -f $username@$HostessHostName "cd ~/Public ; tar -xmzf deploy.tar.gz" &

sshpass -p $password scp Assignment/deploy.tar.gz $username@$PilotHostName:~/Public
sshpass -p $password ssh -o StrictHostKeyChecking=no -f $username@$PilotHostName "cd ~/Public ; tar -xmzf deploy.tar.gz" &

sshpass -p $password scp Assignment/deploy.tar.gz $username@$PassengerHostName:~/Public
sshpass -p $password ssh -o StrictHostKeyChecking=no -f $username@$PassengerHostName "cd ~/Public ; tar -xmzf deploy.tar.gz" &

sleep 1

echo " "
echo "Running project on remote machines:"

echo "- Setting RMI..."

sshpass -p $password scp set_registry.sh $username@$registryHostName:~/Public/src
sshpass -p $password ssh -o StrictHostKeyChecking=no -f $username@$registryHostName "cd ~/Public/src ; sh set_registry.sh $registryPortNum" &
sleep 5

echo " "
echo "- Starting Service Register..."

sshpass -p $password scp registry.sh $username@$registryHostName:~/Public/src
sshpass -p $password ssh -o StrictHostKeyChecking=no -f $username@$registryHostName "cd ~/Public/src ; bash registry.sh $registryHostName $registryPortNum $url" &
sleep 5

echo " "
echo "- Starting Repo..."

sshpass -p $password scp repo.sh $username@$RepoHostName:~/Public/src
sshpass -p $password ssh -o StrictHostKeyChecking=no -f $username@$RepoHostName "cd ~/Public/src ; bash repo.sh $registryHostName $registryPortNum $url" &
sleep 5

echo " "
echo "- Starting Plane..."

sshpass -p $password scp plane.sh $username@$PlaneHostName:~/Public/src
sshpass -p $password ssh -o StrictHostKeyChecking=no -f $username@$PlaneHostName "cd ~/Public/src ; bash plane.sh $registryHostName $registryPortNum $url" &
sleep 5

echo " "
echo "- Starting Departure..."

sshpass -p $password scp departureairport.sh $username@$DepartureAirportHostName:~/Public/src
sshpass -p $password ssh -o StrictHostKeyChecking=no -f $username@$DepartureAirportHostName "cd ~/Public/src ; bash departureairport.sh $registryHostName $registryPortNum $url" &
sleep 5

echo " "
echo "- Starting Destination..."

sshpass -p $password scp destinationairport.sh $username@$DestinationAirportHostName:~/Public/src
sshpass -p $password ssh -o StrictHostKeyChecking=no -f $username@$DestinationAirportHostName "cd ~/Public/src ; bash destinationairport.sh $registryHostName $registryPortNum $url" &
sleep 5

echo " "
echo "- Starting Hostess..."

sshpass -p $password scp hostess.sh $username@$HostessHostName:~/Public/src
sshpass -p $password ssh -o StrictHostKeyChecking=no -f $username@$HostessHostName "cd ~/Public/src ; bash hostess.sh $registryHostName $registryPortNum $url" &
sleep 5

echo " "
echo "- Starting Pilot..."

sshpass -p $password scp pilot.sh $username@$PilotHostName:~/Public/src
sshpass -p $password ssh -o StrictHostKeyChecking=no -f $username@$PilotHostName "cd ~/Public/src ; bash pilot.sh $registryHostName $registryPortNum $url" &
sleep 5

echo " "
echo "- Starting Passenger..."

sshpass -p $password scp passenger.sh $username@$PassengerHostName:~/Public/src
sshpass -p $password ssh -o StrictHostKeyChecking=no -f $username@$PassengerHostName "cd ~/Public/src ; bash passenger.sh $registryHostName $registryPortNum $url" &
sleep 5

echo " "
echo "- Retrieving Log..."
sshpass -p $password ssh -o StrictHostKeyChecking=no -f $username@$RepoHostName "cat Public/src/airlift.txt" > airlift.txt
sleep 2

echo " "
echo "- Stopping RMI Processes..."
bash kill_rmi.sh
bash kill_ports.sh

echo " "
echo "Cleaning Local Classes..."

bash cleanclasses.sh

echo " "
echo "Cleaning Remote Machines..."

bash remoteclean.sh

echo " "
echo "Done!"
export SSHPASS='qwerty'

echo -e "\n${bold}* Execução do código em cada nó *${normal}"


echo -e "\n${bold}->${normal} A iniciar e executar Registry e executar Repository na máquina ${bold}1${normal}"
sshpass -e ssh -tt -o StrictHostKeyChecking=no sd405@l040101-ws04.ua.pt << EOF
    cd Public/classes
    nohup rmiregistry -J-Djava.rmi.server.useCodebaseOnly=true 22427 > /dev/null 2>&1 &
    sleep 5
    
    nohup java -Djava.rmi.server.codebase="http://l040101-ws04.ua.pt/sd405/classes/"\
    -Djava.rmi.server.useCodebaseOnly=true\
    -Djava.security.policy=java.policy\
    MainPackage.ServerRegisterRemoteObject > /dev/null 2>&1 &
    cd ../..
    cd Public/classes/shared/Repo
    
    sleep 5
    nohup java -Djava.rmi.server.codebase="http://l040101-ws04.ua.pt/sd405/classes/"\
    -Djava.rmi.server.useCodebaseOnly=true\
    -Djava.security.policy=java.policy\
    MainPackage.MainProgram > /dev/null 2>&1 &
    exit
EOF

sleep 5

echo -e "\n${bold}->${normal} A executar Plane na máquina ${bold}2${normal}"
sshpass -e ssh -tt -o StrictHostKeyChecking=no sd405@l040101-ws02.ua.pt << EOF
    cd Public/classes
    nohup rmiregistry -J-Djava.rmi.server.useCodebaseOnly=true 22427 > /dev/null 2>&1 &
    sleep 5
    
    nohup java -Djava.rmi.server.codebase="http://l040101-ws04.ua.pt/sd405/classes/"\
    -Djava.rmi.server.useCodebaseOnly=true\
    -Djava.security.policy=java.policy\
    MainPackage.ServerRegisterRemoteObject > /dev/null 2>&1 
    exit
EOF




sleep 5 



echo -e "\n${bold}->${normal} A executar DepartureAirport na máquina ${bold}3${normal}"
sshpass -e ssh -tt -o StrictHostKeyChecking=no sd405@l040101-ws01.ua.pt << EOF
    cd Public/classes/
    nohup java -Djava.rmi.server.codebase="http://l040101-ws04.ua.pt/sd405/classes/"\
    -Djava.rmi.server.useCodebaseOnly=true\
    -Djava.security.policy=java.policy\
    MainPackage.MainProgram > /dev/null 2>&1 &
    exit
EOF


sleep 1

echo -e "\n${bold}->${normal} A executar DestinationAirport na máquina ${bold}4${normal}"
sshpass -e ssh -tt -o StrictHostKeyChecking=no sd405@l040101-ws03.ua.pt << EOF
    cd Public/classes/
    nohup java -Djava.rmi.server.codebase="http://l040101-ws04.ua.pt/sd405/classes/"\
    -Djava.rmi.server.useCodebaseOnly=true\
    -Djava.security.policy=java.policy\
    MainPackage.MainProgram > /dev/null 2>&1 &
    exit
EOF


# Wait for the shared regions to be launched before lanching the intervening enities

sleep 5

echo -e "\n${bold}->${normal} A executar Hostess na máquina ${bold}8${normal}"
sshpass -e ssh -tt -o StrictHostKeyChecking=no sd405@l040101-ws05.ua.pt << EOF
    cd Public/classes/
    nohup java -Djava.rmi.server.codebase="http://l040101-ws04.ua.pt/sd405/classes/"\
    -Djava.rmi.server.useCodebaseOnly=true\
    -Djava.security.policy=java.policy\
    MainPackage.MainProgram > /dev/null 2>&1 &
    exit
EOF

sleep 1

echo -e "\n${bold}->${normal} A executar Pilot na máquina ${bold}9${normal}"
sshpass -e ssh -tt -o StrictHostKeyChecking=no sd405@l040101-ws07.ua.pt << EOF
    cd Public/classes/
    nohup java -Djava.rmi.server.codebase="http://l040101-ws04.ua.pt/sd405/classes/"\
    -Djava.rmi.server.useCodebaseOnly=true\
    -Djava.security.policy=java.policy\
    MainPackage.MainProgram > /dev/null 2>&1 &
    exit
EOF

sleep 1

echo -e "\n${bold}->${normal} A executar Passenger na máquina ${bold}10${normal}"
sshpass -e ssh -tt -o StrictHostKeyChecking=no sd405@l040101-ws06.ua.pt << EOF
    cd Public/classes/
    nohup java -Djava.rmi.server.codebase="http://l040101-ws04.ua.pt/sd405/classes/"\
    -Djava.rmi.server.useCodebaseOnly=true\
    -Djava.security.policy=java.policy\
    MainPackage.MainProgram > /dev/null 2>&1 &
    exit
EOF
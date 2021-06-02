  
#!/bin/bash


sshpass -p qwerty ssh -tt -o StrictHostKeyChecking=no sd405@l040101-ws05.ua.pt "/usr/sbin/fuser -n tcp -k 22445"
sshpass -p qwerty ssh -tt -o StrictHostKeyChecking=no sd405@l040101-ws06.ua.pt "/usr/sbin/fuser -n tcp -k 22446"
sshpass -p qwerty ssh -tt -o StrictHostKeyChecking=no sd405@l040101-ws07.ua.pt "/usr/sbin/fuser -n tcp -k 22444"


sshpass -p qwerty ssh -tt -o StrictHostKeyChecking=no sd405@l040101-ws04.ua.pt "/usr/sbin/fuser -n tcp -k 22440"

sshpass -p qwerty ssh -tt -o StrictHostKeyChecking=no sd405@l040101-ws01.ua.pt "/usr/sbin/fuser -n tcp -k 22441"
sshpass -p qwerty ssh -tt -o StrictHostKeyChecking=no sd405@l040101-ws02.ua.pt "/usr/sbin/fuser -n tcp -k 22442"
sshpass -p qwerty ssh -tt -o StrictHostKeyChecking=no sd405@l040101-ws03.ua.pt "/usr/sbin/fuser -n tcp -k 22443"


"exit"
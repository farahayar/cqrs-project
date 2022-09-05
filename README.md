"# cqrs-project"


--les commandes que j'ai utiliser pour la creation des contenaires ainsi que la connexion entre eux

docker network create --attachable -d bridge pj

docker run -it -d --name mongo-container -p 27017:27017 --network pj --restart always -v mongodb_data_container:/data/db mongo:latest

docker run -it -d --name mysql-container -p 3306:3306 --network pj -e MYSQL_ROOT_PASSWORD=hydatis --restart always -v mysql_data_container:/var/lib/mysql mysql:latest

docker run -it -d --name adminer -p 8086:8086 --network pj -e ADMINER_DEFAULT_SERVER=mysql-container --restart always adminer:latest

--pour executer docker-compose :
docker-compose up -d

--assurez vous que les conteneurs sont en état up pour le faire composer cette commande dans votre power-shell
docker ps

--pour tester les différentes fonctionnalitées à l'aide de swagger veuillez entrer se lien dans votre navigateur:
http://localhost:5001//swagger-ui/index.html#/



"# cqrs-project" 

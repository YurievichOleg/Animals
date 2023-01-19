1. Используя команду cat в терминале операционной системы Linux, создать два файла Домашние животные (заполнив файл собаками, кошками, хомяками) и Вьючные животными заполнив файл Лошадьми, верблюдами и ослы), а затем объединить их. Просмотреть содержимое созданного файла. Переименовать файл, дав ему новое имя (Друзья человека). 2. Создать директорию, переместить файл туда.

1168  cat > pets\
1169  history\
1170  nano pets\
1171  cat pack_animals\
1172  cat > pack_animals\
1173  nano pack_animals\
1174  cat pets pack_animals > all_animals\
1175  nano all_animals\
1176  mkdir pets_and_animals\
1178  cd pets_and_animals/\
1181  mv all_animals /home/oleg/pets_and_animals\
1242  mv all_animals human_friends\
1182  history



	3. Подключить дополнительный репозиторий MySQL. Установить любой пакет из этого репозитория. 
sudo apt install mysql-server\
sudo apt -f install\



4. Установить и удалить deb-пакет с помощью dpkg.



1196  wget https://dev.mysql.com/get/mysql-apt-config_0.8.13-1_all.deb\
1198  sudo dpkg -i mysql-apt-config*\
1199  sudo apt -f install\
1201  sudo apt update\
1216  sudo apt install mysql-server -y\
1217  sudo systemctl status mysql


	 

1247  wget https://protonvpn.com/download/protonvpn-stable-release_1.0.1-1_all.deb\
1248  sudo dpkg -i protonvpn-stable-release_1.0.1-1_all.deb\
1249  sudo apt -f install\
1250  sudo apt update\
1252  sudo apt install protonvpn-stable-release -y\
1253  sudo apt list --installed | grep proton\
1255  sudo apt purge protonvpn-stable-release/now -y\
1256  history


	7. В подключенном MySQL репозитории создать базу данных “Друзья человека” 
mysql> create database human_friends;\

mysql> use human_friends;\

mysql> create table Pets (id INT auto_increment primary key, type_of_animal varchar(15) not null, Name varchar(20) not null, birth_day date not null, command varchar(200));\

mysql> create table Pack_animals_1 (id INT auto_increment primary key, type_of_animal varchar(15) not null, Name varchar(20) not null, birth_day date not null, command varchar(200));\

mysql> insert into Pets(type_of_animal, Name, birth_day, command) values ('dog', 'Bobick', '2018-01-15','Сидеть, лежать'), ('cat', 'Murka', '2019-05-09', null), ('hamster', 'Jery', '2021-12-21', null));\

mysql> insert into Pack_animals (type_of_animal, Name, birth_day, command) values ('hourse', 'Merin', '2015-12-27', 'Stop, Go'), ('camel', 'Vasia', '2016-11-03', 'Go,Stop'), ('donkey', 'Ia', '2020-12-29', null);\

mysql> delete from Pack_animals where type_of_animal = ‘camel';\

mysql> create table test as Select * from Pets union select * from Pack_animals;\

mysql> Alter table test drop column id;\

mysql> Alter table test add id_animal INT PRIMARY KEY AUTO_INCREMENT;\

mysql> create table young_animals as select * from test where birth_day between DATE_SUB(CURDATE(), INTERVAL 3 year) and DATE_SUB(CURDATE(), INTERVAL 1 year);
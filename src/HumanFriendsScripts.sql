-- Создаем БД и выводим список БД:
CREATE DATABASE IF NOT EXISTS HumanFriends; 
SHOW DATABASES;
USE  HumanFriends;


-- DELIMITER //
--  
--  DROP FUNCTION IF EXISTS getId//
--  -- Функция переводит секунды в строку: дни-часы-минуты-секунды
--  CREATE FUNCTION getId (i INT)
--  RETURNS INT DETERMINISTIC
--  BEGIN
-- 		SET @i := i + 1;
--     RETURN @i;  #days;
--  END//
--  
--  DELIMITER ;



 
-- Создаем таблицу Dog и заполняем ее: , InventoryValue AS QtyAvailable * UnitPrice
DROP TABLE IF EXISTS Dogs;
CREATE  TABLE Dogs (
  id INT UNSIGNED PRIMARY KEY NOT NULL,
	name VARCHAR(20) NOT NULL,
	birthday Date,
	commands VARCHAR(50)
);	
  
  -- SELECT MAX(id) FROM (SELECT id FROM Dogs UNION SELECT id FROM Cats UNION SELECT id FROM Hamsters) b;
  


-- Выполняем поверхностное клонирование (копирование структуры) из Dogs в Cats, Hamsters, Camel, Horses, Donkeys:
DROP TABLE IF EXISTS Cats, Hamsters, Camels, Horses, Donkeys;
CREATE TABLE Cats LIKE Dogs;
CREATE TABLE Hamsters LIKE Dogs;
CREATE TABLE Camels LIKE Dogs;
CREATE TABLE Horses LIKE Dogs;
CREATE TABLE Donkeys LIKE Dogs;

-- Заполняем таблицы данными:
-- Создаем первый счетчик:
SET @i = (SELECT MAX(id) FROM (SELECT id FROM Dogs UNION SELECT id FROM Cats UNION SELECT id FROM Hamsters) b);
SET @i := IF(@i IS NULL, 0, @i);   
 
INSERT INTO Dogs 
	(id, name, birthday, commands)  
VALUES 
	((@i := @i + 1), 'Fido', '2020-01-01','Sit, Stay, Fetch'),
	((@i := @i + 1), 'Buddy', '2018-12-10',	'Sit, Paw, Bark'),
 	((@i := @i + 1), 'Bella',	'2019-11-11',	'Sit, Stay, Roll')
;
select * FROM Dogs;
-- DELETE FROM Dogs;

INSERT INTO Cats 
	(id, name, birthday, commands)  
VALUES 
	((@i := @i + 1), 'Whiskers', '2019-05-15',	'Sit, Pounce'),
	((@i := @i + 1), 'Smudge',	'2020-02-20',	'Sit, Pounce, Scratch'),
	((@i := @i + 1), 'Oliver',	'2020-06-30',	'Meow, Scratch, Jump')
;
select * FROM Cats;

INSERT INTO Hamsters 
	(id, name, birthday, commands)  
VALUES 
	((@i := @i + 1), 'Hammy',	'2021-03-10', 'Roll, Hide'),
	((@i := @i + 1), 'Peanut',	'2021-08-01',	'Roll, Spin')
;
SELECT * FROM Hamsters;



-- Создаем второй счетчик:
SET @j = (SELECT MAX(id) FROM (SELECT id FROM Camels UNION SELECT id FROM Horses UNION SELECT id FROM Donkeys) b);
SET @j := IF(@j IS NULL, 0, @j);  

INSERT INTO Horses 
	(id, name, birthday, commands) 
VALUES 
	((@j := @j + 1), 'Thunder',	'2015-07-21',	'Trot, Canter, Gallop'),
  ((@j := @j + 1), 'Blaze',	'2016-02-29',	'Trot, Jump, Gallop'),
  ((@j := @j + 1), 'Storm',	'2014-05-05',	'Trot, Canter')
;
SELECT * FROM Horses;

INSERT INTO Camels 
	(id, name, birthday, commands) 
VALUES 
	((@j := @j + 1), 'Sandy',	'2016-11-03',	'Walk, Carry Load'),
	((@j := @j + 1), 'Sahara',	'2015-08-14',	'Walk, Run'),
	((@j := @j + 1), 'Dune',	'2018-12-12',	'Walk, Sit')
;
SELECT * FROM Camels;

INSERT INTO Donkeys 
	(id, name, birthday, commands) 
VALUES 
	((@j := @j + 1), 'Burro',	'2019-01-23',	'Walk, Bray, Kick'),
	((@j := @j + 1), 'Eeyore', '2017-09-18',	'Walk, Carry Load, Bray')
;
SELECT * FROM Donkeys;

-- Создадим также виды Pets и PackAnimals и посмотрим данные в них:
DROP VIEW IF EXISTS Pets, PackAnimals;

CREATE VIEW Pets AS
  SELECT id, name, 'Dog' AS type, birthday, commands FROM Dogs
		UNION
	SELECT id, name, 'Cat' AS type, birthday, commands FROM Cats
		UNION
	SELECT id, name, 'Hamster' AS type, birthday, commands FROM Hamsters
;
SELECT * FROM Pets;


CREATE VIEW PackAnimals AS
	SELECT id, name, 'Camel' AS type, birthday, commands FROM Camels
		UNION
	SELECT id, name, 'Horse' AS type, birthday, commands FROM Horses
		UNION
	SELECT id, name, 'Donkey' AS type, birthday, commands FROM Donkeys
;

SElECT * FROM PackAnimals;

-- Удаляем данные о верблюдах:
DELETE FROM Camels;
SELECT * FROM Camels;

-- Oбъединяем таблицы лошадей и ослов во временную таблицу HorseAndDonkey:
/*CREATE TEMPORARY TABLE HorseAndDonkey 
	SELECT * FROM PackAnimals 
	WHERE type = 'Horse'  OR  type = 'Donkey';*/

-- т.к. мы удалили ослов, сделаем проще:
CREATE TEMPORARY TABLE HorseAndDonkey 
	SELECT * FROM PackAnimals;
  
SELECT * FROM HorseAndDonkey;

-- Создаем новую таблицу для животных в возрасте от 1 до 3 лет и вычисляем их возраст с точностью до месяца.
DROP TABLE IF EXISTS UnderThree;

CREATE TABLE UnderThree (
	SELECT *, 
		concat(DATEDIFF(curdate(), birthday) div 365, ' year '
			, (DATEDIFF(curdate(), birthday) mod 365) div 30, ' month') AS Age
	FROM Pets
		UNION
	SELECT *,
		concat(DATEDIFF(curdate(), birthday) div 365, ' year '
			, (DATEDIFF(curdate(), birthday) mod 365) div 30, ' month') AS Age
	FROM PackAnimals
);

SELECT * FROM UnderThree;



-- Объединяем все таблицы животных в одну, используя виды PackAnimals и Pets в одну, сохраняя информацию о принадлежности к исходным таблицам.

CREATE TABLE Animals
	SELECT *, 'Pets' AS ParentTable
	FROM Pets
	UNION
		SELECT *, 'PackAnimals' AS ParentTable
		FROM PackAnimals;
SELECT * FROM Animals;

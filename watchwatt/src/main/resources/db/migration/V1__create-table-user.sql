CREATE TABLE userr (
  id INT AUTO_INCREMENT PRIMARY KEY,
  cpf VARCHAR(11) not null UNIQUE,
  name varchar(100) not null,
  birthday DATE not null,
  gender VARCHAR(10) not null
  
)
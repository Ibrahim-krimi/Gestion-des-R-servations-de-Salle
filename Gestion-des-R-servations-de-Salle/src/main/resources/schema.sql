-- Création de la table Employee
DROP TABLE  IF EXISTS reservation;
DROP TABLE  IF EXISTS employee ;
DROP TABLE  IF EXISTS room;

CREATE TABLE employee (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          nom VARCHAR(255) NOT NULL,
                          role VARCHAR(50) NOT NULL,
                          email VARCHAR(255) UNIQUE NOT NULL
);

-- Création de la table Room
CREATE TABLE room (
                      id INT AUTO_INCREMENT PRIMARY KEY,
                      name VARCHAR(255) NOT NULL,
                      description TEXT,
                      capacity INT NOT NULL
);

-- Création de la table Reservation
CREATE TABLE reservation (
                             id INT AUTO_INCREMENT PRIMARY KEY,
                             descritption TEXT,
                             date_Debut DATETIME NOT NULL,
                             date_Fin DATETIME NOT NULL,
                             employee_id INT,
                             room_id INT,
                             FOREIGN KEY (employee_id) REFERENCES employee(id) ON DELETE SET NULL,
                             FOREIGN KEY (room_id) REFERENCES room(id) ON DELETE SET NULL
);

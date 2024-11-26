-- Insertion des employés
INSERT INTO employee (nom, role, email) VALUES
                                            ('Alice', 'MANAGER', 'alice@example.com'),
                                            ('Bob', 'DEVELOPER', 'bob@example.com'),
                                            ('Charlie', 'HR', 'charlie@example.com');

-- Insertion des salles
INSERT INTO room (name, description, capacity) VALUES
                                                   ('Salle de conférence', 'Salle avec un grand écran et des sièges confortables', 20),
                                                   ('Salle de réunion', 'Petite salle pour des discussions privées', 6),
                                                   ('Auditorium', 'Salle spacieuse pour des présentations et séminaires', 100);

-- Insertion des réservations
INSERT INTO reservation (descritption, date_Debut, date_Fin, employee_id, room_id) VALUES
                                                                                       ('Réunion stratégique', '2024-11-27 10:00:00', '2024-11-27 12:00:00', 1, 1),
                                                                                       ('Session de formation', '2024-11-28 14:00:00', '2024-11-28 17:00:00', 2, 3),
                                                                                       ('Entretien RH', '2024-11-29 09:00:00', '2024-11-29 10:00:00', 3, 2);

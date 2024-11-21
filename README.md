# Projet à développer : Gestion des Réservations de Salle

## Contexte :

Tu dois développer une application pour gérer les réservations de salles dans une entreprise. Les employés peuvent réserver des salles pour des réunions, avec un horaire précis et une capacité maximale.

## 1. Fonctionnalités de l'application :

Créer un employé : Les informations d’un employé incluent le nom, l’email et un rôle (ex. : "Employé" ou "Manager").

Créer une salle : Chaque salle a un nom, une capacité maximale et une localisation.

Réserver une salle : L'employé peut réserver une salle pour une plage horaire précise.
Une salle ne peut pas être réservée si elle est déjà occupée pendant l'horaire demandé.
La capacité de la salle doit être respectée.

Annuler une réservation : Un employé peut annuler une réservation qu'il a faite.

Lister les réservations : Permet de lister toutes les réservations effectuées, filtrées par employé ou par salle.

## 2. Objectif des tests

   L’objectif est de développer cette application en écrivant des tests unitaires, des tests d'intégration et des tests fonctionnels pour vérifier chaque fonctionnalité.

## 3. Étapes de développement et tests associés
   
### Étape 1 : Modèle et référentiel
   
#### Implémente les entités suivantes :

   Employee (Employé)

   Room (Salle)

   Reservation (Réservation)

#### Tests d'intégration à réaliser :

Tester le repository de chaque entité avec une base de données H2 :

Vérifier que EmployeeRepository peut sauvegarder et récupérer un employé par son email.
   
Vérifier que RoomRepository peut récupérer une salle par sa localisation.
   
Vérifier que ReservationRepository peut sauvegarder une réservation et la récupérer par salle ou employé.
### Étape 2 : Service

#### Implémente la logique métier dans un service :
   
Validation de la disponibilité d’une salle.
   
Validation de la capacité de la salle.

Création et annulation d’une réservation.

#### Tests unitaires à réaliser :
   
ReservationService :
   
Vérifie que la validation d'une salle disponible retourne true ou false correctement.
   
Vérifie que la validation de la capacité fonctionne.
   
Vérifie que la méthode createReservation retourne une exception si la salle est déjà réservée.
  
#### Tests d'intégration point-à-point :
   
Teste que le service interagit correctement avec les référentiels pour :
  
Vérifier la disponibilité des salles.
   
Enregistrer une réservation valide.
   
### Étape 3 : Contrôleur
   
#### Implémente une API REST pour :
   
Créer un employé.
   
Créer une salle.
   
Réserver une salle.
   
Annuler une réservation.
   
Lister les réservations.
   
#### Tests fonctionnels à réaliser :
   
Vérifie que l'API permet de :

Créer un employé via un endpoint POST (/employees).

Créer une salle via un endpoint POST (/rooms).

Réserver une salle via un endpoint POST (/reservations).

Annuler une réservation via un endpoint DELETE (/reservations/{id}).

Lister les réservations via un endpoint GET (/reservations).

#### Tests de bout en bout :

Simule un scénario complet :

Crée un employé et une salle.

Effectue une réservation pour cet employé.

Vérifie que la salle ne peut plus être réservée pour la même plage horaire.

##### 4. Résultat attendu
   Fonctionnalités implémentées :

   Les employés peuvent être créés, les salles peuvent être ajoutées.

   Une réservation peut être créée ou annulée.

   La validation de la disponibilité et de la capacité fonctionne.

##### Tests écrits :
Tests unitaires : Validation de la logique métier (service).
   
Tests d'intégration : Vérification des interactions entre le service et les référentiels.
  
Tests fonctionnels : Vérification de bout en bout via des appels à l'API.

##### 5. Plan détaillé des tests
   Tests d'intégration (Repository)

   EmployeeRepositoryTest :

   Sauvegarder et récupérer un employé.

   Trouver un employé par email.

   RoomRepositoryTest :

   Sauvegarder et récupérer une salle.

   Trouver une salle par localisation.

   ReservationRepositoryTest :

   Sauvegarder une réservation.

   Récupérer les réservations par employé ou salle.

   Tests unitaires (Service)

   ReservationServiceTest :

   Vérifier la validation des horaires.

   Vérifier la validation des capacités.

   Vérifier qu’une réservation est créée correctement.

##### Tests fonctionnels (API REST)
   
Teste les endpoints REST :
   
Créer un employé (POST /employees).
   
Créer une salle (POST /rooms).
   
Réserver une salle (POST /reservations).
   
Annuler une réservation (DELETE /reservations/{id}).
   
Lister les réservations (GET /reservations).
   
Scénario complet (E2E)
   
Crée un employé.
   
Crée une salle.
   
Réserve la salle.
   
Vérifie qu'une deuxième réservation pour la même plage horaire échoue.
   
Annule la réservation.
   
Vérifie que la salle peut à nouveau être réservée.

#### Contributor  : KRIMI Ibrahim
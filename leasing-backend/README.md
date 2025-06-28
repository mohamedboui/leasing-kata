# Car Leasing Backend

Ce projet est un backend réactif pour la gestion de locations de voitures, développé en **Java 21** avec **Spring Boot WebFlux** en architecture hexagonale.


##  API Documentation

L’API est documentée avec OpenAPI.

Accéder à Swagger UI :  
 `http://localhost:8080/swagger-ui.html`

---

## ✅ Exemples d'API

### ➕ Louer une voiture

```http
POST /api/leases
Content-Type: application/json

{
  "customerId": 1,
  "carId": 1,
  "startDate": "2025-07-01",
  "endDate": "2025-07-10",
}
```

###  Retourner une voiture

```http
PUT /api/leases/{leaseId}/return
Content-Type: application/json

{
  "customerId": 1,
  "carId": 1
}
```

###  Obtenir une location

```http
GET /api/leases/1
```

## Tests

Ce projet contient :

    - des tests unitaires (logique métier)

    - des tests d’intégration (WebTestClient && testcontainers) 
### Exécuter les tests :

Pour lancer les tests, utilisez l'une des méthodes suivantes :

mvn test

## Choix Techniques

### Architecture :

Architecture hexagonale

Ce projet applique le modèle hexagonal (aussi appelé Ports and Adapters), structurant le code autour du domaine métier. Cela offre :

une forte isolation entre la logique métier et l'infrastructure

une testabilité renforcée

une évolutivité facilitée (remplacement facile de la base de données, du transport, etc.)

### Technologies utilisées :

- **Backend** : Spring Boot 3.x.x
- **Base de données** : MySQL avec R2DBC pour les opérations non-bloquantes
- **Tests** : JUnit, WebTestClient pour les tests d'intégration WebFlux
- **API REST** : HATEOAS avec Spring WebFlux pour les appels asynchrones
## Docker Compose

Ce projet inclut un fichier docker-compose.yml pour lancer rapidement les services nécessaires.
### Prérequis :

- **Docker** : Assurez-vous que Docker est installé sur votre machine.
- **Docker Compose** : Vérifiez que Docker Compose est également installé.

### Fichier `docker-compose.yml` :

Voici le contenu de notre fichier `docker-compose.yml` :


```
version: "3.7"

services:
  mysqldb:
    image: "mysql:8.0"
    restart: always
    ports:
      - 3306:3306
    networks:
      - kata-net
    environment:
      MYSQL_DATABASE: leasing_db
      MYSQL_USER: kata_user
      MYSQL_PASSWORD: kata_password
      MYSQL_ROOT_PASSWORD: kata_password
    volumes:
      - ./init_db.sql:/docker-entrypoint-initdb.d/init_db.sql
      
networks:
  kata-net:
```

### Démarrer les services :

En pré-requis, il faut générer le package avec : `mvn package`

Pour lancer les services, exécutez la commande suivante dans le répertoire où se trouve le fichier docker-compose.yml :

```
docker compose up --build
```
# Leasing Kata – Fullstack Application

Ce projet est une application complète de gestion de leasing automobile, développée avec :

-  **Backend** : Java 21 + Spring Boot (WebFlux) – dossier `leasing-backend/`
-  **Frontend** : Angular – dossier `leasing-frontend/`

---

## Structure du projet

```
leasing-kata/
├── leasing-backend/      # API REST Reactive (Spring Boot WebFlux)
├── leasing-front/     # Interface utilisateur Angular
├── .gitignore
└── README.md
```

---

##  Lancer le projet

### 1. Prérequis

- **Node.js** `>=18`
- **npm** ou **yarn**
- **Java 21**
- **Maven** (`mvn`) ou Wrapper (`./mvnw`)

---

### Installation

#### Backend (Spring Boot)

```bash
cd leasing-backend
./mvnw clean install
```

#### Frontend (Angular)

```bash
cd leasing-front
npm install
```

---

### Exécution

#### Backend

```bash
cd backend
./mvnw spring-boot:run
```

Accès : `http://localhost:8080`

####  Frontend

```bash
cd frontend
ng serve
```

Accès : `http://localhost:4200`

---

## API et Communication

- Le frontend communique avec l’API exposée par le backend (port `8080` par défaut).
- Pour le développement local, configure un **proxy Angular** dans `frontend/proxy.conf.json` :

```json
{
  "/api": {
    "target": "http://localhost:8080",
    "secure": false,
    "changeOrigin": true
  }
}
```

Et lance Angular avec :

```bash
ng serve --proxy-config proxy.conf.json
```

---

##  Tests

#### Backend :
```bash
cd backend
./mvnw test
```

```

---

##  Technologies principales

### Backend :
- Java 21
- Spring Boot WebFlux
- Architecture hexagonale
- Maven

### Frontend :
- Angular 17+
- TypeScript
- RxJS


---

##  Packaging & Production

Tu peux packager le backend avec :

```bash
cd leasing-backend
./mvnw clean package
```

Cela génère un `.jar` exécutable dans `target/`.

---

## Auteur

**Mohamed Bouillis** 
[https://github.com/mohamedboui](https://github.com/mohamedboui)

---

## Licence

Ce projet est sous licence MIT – voir le fichier [LICENSE](LICENSE) pour plus d’informations.

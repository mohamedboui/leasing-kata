# Leasing Front – Application Angular

Frontend de l'application de gestion de leasing de voitures, développé avec **Angular**, **NgRx**, **Bootstrap** et **TypeScript**.

## Prérequis

- [Node.js](https://nodejs.org/) **v18.13.0 ou plus récent**
- [Angular CLI](https://angular.io/cli) **v17+**
- [npm](https://www.npmjs.com/) (fourni avec Node.js)
- Accès au backend : voir le projet `leasing-backend`

> Assurez-vous que `node -v` affiche une version >= 18.13 et < 21.

---

##  Installation du projet

1. Clone du repo

```bash
git clone  https://gitlab.com/fr_kata_sf/c4-SF-0311-SQ02
cd leasing-front
```

2. Installation des dépendances

```bash
npm install --legacy-peer-deps
```

---

##  Démarrage de l'application

```bash
npm start -- --proxy-config proxy.conf.json

```

Puis ouvrir [http://localhost:4200](http://localhost:4200) dans votre navigateur. le backend devrait se lancer aussi sur le port 8080

---

##  Fonctionnalités principales

-  Liste des leases
-  Création d'un lease
-  Retour d’un véhicule
-  Toasts de succès / erreur
-  State management avec **NgRx**
-  Design Bootstrap

---

## Configuration backend

Par défaut, l'app utilise l'API REST exposée à `http://localhost:8080/api/leases`.

Vous pouvez adapter l'URL dans le service Angular (`lease.service.ts`) si besoin.

---

##  Structure recommandée

```bash
src/
├── app/
│   ├── components/
│   ├── store/
│   ├── models/
│   ├── services/
│   └── app-routing.module.ts
├── assets/
├── environments/
└── index.html
```

---

---

## Auteur

Projet réalisé par [Mohamed Bouillis](https://github.com/mohamedboui)

---

##  Licence

Projet privé dans le cadre d’un test technique.

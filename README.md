# 🐾 ToaIwa - Joc de Cartes Estratègic (Java Swing)

_ToaIwa_ és una **recreació en Java Swing del joc original _Mauwi_ creat per Vincent Joassin**. Aquest projecte **no té cap mena de vinculació amb el joc original**, ni cap intenció de comercialització. Es tracta exclusivament d’un **projecte intern acadèmic** dins del **Cicle Formatiu de Grau Superior de Desenvolupament d'Aplicacions Multiplataforma** (DAM).

## 🧠 Objectiu del joc
Completar columnes de cartes amb estratègia i rapidesa, utilitzant guerrers de diferents colors i valors, juntament amb cartes especials com els esperits i els jokers.

## 🚧 Estat del projecte
🟡 **En desenvolupament**

### ✔️ Implementat fins ara
- [x] Sistema de registre d’usuari amb `JOptionPane`
- [x] Inserció d'usuaris a MySQL
- [x] Connexió JDBC amb MySQL
- [x] Creació i gestió de cartes: Warrior, Spirit i Joker
- [x] Carregat de cartes des de la base de dades
- [x] Interfície gràfica inicial amb Swing
- [x] Temporitzador i control de temps per jugador
- [x] Algunes mecàniques del joc

### 🔧 Funcionalitats pendents
- [ ] Mecànica completa del joc
- [ ] Emmagatzematge de resultats i guanyadors
- [ ] Estètica i accessibilitat de la GUI

## 🛠️ Tecnologies utilitzades
- **Java** (Swing)
- **MySQL**
- **Git + GitHub**

## 📂 Estructura del projecte
src/
├── Model/
│ ├── Card.java
│ ├── Warrior.java
│ ├── Joker.java
│ ├── Spirit.java
│ ├── User.java
│ └── UserControl.java
├── View/
│ └── ToaIwaView.java
└── Main/
└── Main.java

## 📌 Nota legal
Aquest projecte es basa en la mecànica del joc **Mauwi** de Vincent Joassin, però:

- No és una còpia literal del joc.
- No es distribuirà ni es comercialitzarà.
- És només per a ús acadèmic i formatiu.

## ⚠️ Avís de drets d'autor:
Les imatges utilitzades en aquest projecte pertanyen als creadors del joc Mauwi. Aquest projecte és estrictament educatiu i no té cap finalitat comercial. Si ets el propietari dels drets i vols que es retirin, contactam i ho farem immediatament.

## 👩‍💻 Desenvolupadora
Aquest projecte forma part del curs DAM (1r any) i és desenvolupat per **Natalia Pérez Ruiz**, estudiant de desenvolupament d'aplicacions multiplataforma al Centre d'Estudis Politècnics.

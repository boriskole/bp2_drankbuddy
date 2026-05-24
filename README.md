<p align="center">
  <img src="src/main/resources/media/logo.png" width="200" height="200"/>
</p>

# DrankBuddy - Voorraadbeheersysteem

DrankBuddy is een JavaFX-applicatie ontwikkeld om drankvoorraden eenvoudig en efficiënt te beheren. Met deze applicatie kunnen gebruikers producten, categorieën en voorraadmutaties bijhouden in een intuïtieve gebruikersomgeving.

## Aan de slag

Deze instructies helpen je om de applicatie in een lokale omgeving te draaien met behulp van de meegeleverde database.

### Vereisten

De volgende software moet geïnstalleerd zijn voordat je de applicatie kunt gebruiken:

* Java Development Kit versie 25 (JDK 25)
* Maven (voor het bouwen van het project)
* XAMPP, MAMP of een andere manier om een SQL-database lokaal te draaien
* MySQL Server

### Installatie

Een stapsgewijze handleiding om de ontwikkelomgeving op te zetten en draaiend te krijgen.

#### 1. Repository downloaden

```bash
Download de repository of clone deze met Git:
git clone https://github.com/boriskole/bp2_drankbuddy.git
```

#### 2. Database opzetten

```text
Zoek het bestand 'setup_script.sql' in de projectmap.
Importeer dit bestand in localhost/phpmyadmin als je XAMPP gebruikt.
Zorg ervoor dat de database 'bp2_drankbuddy' heet.
```

#### 3. Database verbinding configureren

**BELANGRIJK:** Voordat je de applicatie compileert of bouwt, moet je de databasegegevens aanpassen aan jouw lokale omgeving.

```text
Open het bestand:
src/main/java/nl/adacademie/drankbuddy/service/DatabaseConnectionProvider.java

Pas de volgende constanten aan naar jouw eigen database instellingen:
- DATABASE_CONNECTION_URL
- DATABASE_USERNAME
- DATABASE_PASSWORD
```

Bijvoorbeeld:

```java
private static final String DATABASE_CONNECTION_URL = "jdbc:mysql://localhost:3306/bp2_drankbuddy";
private static final String DATABASE_USERNAME = "jouw_gebruikersnaam";
private static final String DATABASE_PASSWORD = "jouw_wachtwoord";
```

#### 4. Applicatie bouwen

```bash
Navigeer naar de projectmap in de terminal en voer het volgende commando uit:
mvn clean package
```

Dit genereert een JAR-bestand in de map:

```text
target/DrankBuddy-1.0.0-jar-with-dependencies.jar
```

OF via IntelliJ IDEA:

```text
File -> Project Structure -> Artifacts
Maak een nieuwe artifact van het type 'Java'
Klik op 'From modules with dependencies...'
Selecteer het project 'DrankBuddy' en klik op 'OK'
Build vervolgens het project via IntelliJ IDEA
```

Het JAR-bestand is daarna te vinden in:

```text
out/artifacts/DrankBuddy_jar/
```

#### 5. Applicatie starten

```bash
Start de applicatie door het JAR-bestand uit te voeren:
java -jar target/DrankBuddy-1.0.0-jar-with-dependencies.jar
```

Of gebruik Maven:

```bash
mvn javafx:run
```

---

## Functionaliteiten

### Accountbeheer

- Inloggen met bestaande accounts
- Nieuwe accounts registreren
- Beveiligde toegang tot het systeem

### Categoriebeheer

- Nieuwe categorieën toevoegen
- Bestaande categorieën bewerken
- Categorieën verwijderen
- Overzicht van alle categorieën

### Productbeheer

- Nieuwe producten toevoegen
- Productgegevens bewerken
- Producten verwijderen
- Alle producten bekijken
- Producten koppelen aan categorieën

### Voorraadmutaties

- Voorraadwijzigingen registreren
- Mutaties bekijken per product
- Overzicht van alle voorraadmutaties
- Historisch overzicht van voorraadveranderingen

---

## Gebruik

1. Open de applicatie
2. Log in met je account of registreer een nieuw account
3. Gebruik het navigatiemenu om functies te beheren

### Navigatiemenu's

- **Categorieën** → beheer productcategorieën
- **Producten** → voeg producten toe, wijzig of verwijder ze
- **Voorraadmutaties** → registreer wijzigingen en bekijk de geschiedenis

### Probleemoplossing

Controleer het volgende als de applicatie niet werkt:

- Of MySQL draait
- Of de database correct is geïmporteerd
- Of de databasegegevens correct staan ingesteld in `DatabaseConnectionProvider.java`

---

## Technologieën

* JavaFX 25.0.1
* CSS (JavaFX CSS)
* MySQL Database
* JDBC
* Maven

---

## Implementatieplan

### Huidige staat van het systeem

Het DrankBuddy-systeem is volledig functioneel in een ontwikkelomgeving met de volgende kenmerken:

- JavaFX desktop applicatie
- Lokale MySQL database
- Basis authenticatie en autorisatie
- CRUD-operaties voor producten, categorieën en voorraadmutaties

### Acceptatiecriteria

Het systeem wordt als succesvol geïmplementeerd beschouwd wanneer:

- ✅ Alle gebruikers kunnen inloggen en hun taken uitvoeren
- ✅ Database is stabiel en performant (response tijd < 2 seconden)
- ✅ Geen kritieke bugs aanwezig
- ✅ 90% van de gebruikers heeft de training succesvol afgerond
- ✅ Backup en restore procedures zijn getest en functioneel

### Uitrol naar productieomgeving

Om DrankBuddy succesvol in een productieomgeving te implementeren, worden de volgende stappen uitgevoerd:

#### 1. Productieomgeving voorbereiden

- Installeren van Java Runtime Environment (JRE)
- Configureren van MySQL Server op de productiecomputer/server
- Aanmaken van de productie database `bp2_drankbuddy`
- Configureren van gebruikersrechten en databasebeveiliging

#### 2. Applicatie deployment

- Builden van een stabiele releaseversie via Maven
- Deployen van het JAR-bestand naar de productieomgeving
- Configureren van databaseverbindingen voor productiegebruik
- Testen van connectiviteit tussen applicatie en database

#### 3. Datamigratie

- Importeren van initiële categorieën en producten
- Controleren van dataconsistentie
- Uitvoeren van validatietests

#### 4. Gebruikerstraining

- Uitleg geven over:
    - Productbeheer
    - Voorraadmutaties
    - Accountbeheer
- Demonstratie van dagelijkse workflows
- Gebruikershandleiding beschikbaar stellen

#### 5. Monitoring en onderhoud

- Regelmatige database back-ups uitvoeren
- Prestatiecontrole van database en applicatie
- Bugfixes en updates documenteren
- Evaluatiemomenten inplannen met gebruikers

### Evaluatie van de implementatie

Na implementatie wordt het systeem geëvalueerd op de volgende onderdelen:

- Gebruiksvriendelijkheid van de interface
- Stabiliteit van de applicatie
- Snelheid van database-operaties
- Betrouwbaarheid van voorraadregistraties
- Tevredenheid van gebruikers
- Aantal gemelde bugs of storingen

De evaluatie wordt gebruikt om verbeterpunten te identificeren voor toekomstige versies van DrankBuddy.

---

## Stakeholdercommunicatie

Tijdens de ontwikkeling en implementatie van DrankBuddy wordt actief gecommuniceerd met stakeholders, waaronder gebruikers, ontwikkelaars en projectbegeleiders.

### Communicatiemethoden

- Wekelijkse voortgangsupdates
- Demonstraties van nieuwe functionaliteiten
- Feedbacksessies met gebruikers
- Issue tracking via GitHub Issues

### Betrokken stakeholders

- Eindgebruikers van het voorraadbeheersysteem
- Ontwikkelaars van het project
- Projectbegeleiders/docenten
- Testgebruikers

### Feedback en evaluatie

Feedback van stakeholders wordt verzameld via:

- Testmomenten
- Gebruikerservaringen
- Bug reports
- Verbeterpunten vanuit evaluaties

Op basis van deze feedback worden toekomstige verbeteringen en nieuwe functionaliteiten gepland.

---

## Bijdragen

Bijdragen maken de open source community een geweldige plek om te leren, inspireren en creëren. Alle bijdragen worden zeer gewaardeerd.

Als je een suggestie hebt die dit project beter maakt:

1. Fork de repository
2. Maak een nieuwe branch:

```bash
git checkout -b feature-naam
```

3. Commit je wijzigingen:

```bash
git commit -m "Voeg nieuwe feature toe"
```

4. Push naar je fork:

```bash
git push origin feature-naam
```

5. Open een pull request

Je kunt ook een issue openen met de tag `"enhancement"`.

---

## Roadmap

### Fase 1 — Basisfunctionaliteiten (Afgerond)

- [x] Productbeheer
- [x] Categoriebeheer
- [x] Voorraadmutaties registreren
- [x] Account registratie en login systeem
- [x] JAR-bestand genereren voor distributie

### Fase 2 — Rapportage en analyse (Q3 2026)

- [ ] Rapporten genereren (Excel/PDF export)
- [ ] Dashboard met statistieken en grafieken
- [ ] Verbeterde zoek- en filterfunctionaliteiten

### Fase 3 — Slim voorraadbeheer (Q4 2026)

- [ ] Automatische waarschuwingen bij lage voorraad
- [ ] Barcode scanner integratie
- [ ] Geavanceerde voorraadanalyses

### Fase 4 — Schaalbaarheid en uitbreiding (2027)

- [ ] Multi-user permissions en rollen
- [ ] Cloud-based deployment optie
- [ ] Mobile app versie

Zie de open issues op GitHub voor een volledige lijst van voorgestelde functionaliteiten en bekende problemen.

---

## Contact

Boris Kole  
boris.kole@student.ad-academie.nl

Project Repository:  
https://github.com/boriskole/bp2_drankbuddy

---

## Licentie

Dit project is ontwikkeld als onderdeel van een schoolopdracht voor de AD Academie.

---

<p align="center">
  Ontwikkeld met ❤️ voor efficiënt voorraadbeheer
</p>

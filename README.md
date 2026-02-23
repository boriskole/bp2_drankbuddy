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
```
Download de repository of clone deze met Git:
git clone https://github.com/[jouw-gebruikersnaam]/bp2_drankbuddy.git
```

#### 2. Database opzetten
```
Zoek het bestand 'setup_script.sql' in de projectmap.
Importeer dit bestand in localhost/phpmyadmin als je XAMPP gebruikt.
Zorg ervoor dat de database 'bp2_drankbuddy' heet.
```

#### 3. Database verbinding configureren

**BELANGRIJK:** Voordat je de applicatie compileert of bouwt, moet je de databasegegevens aanpassen aan jouw lokale omgeving.

```
Open het bestand: src/main/java/nl/adacademie/drankbuddy/service/DatabaseConnectionProvider.java

Pas de volgende constanten aan naar jouw eigen database instellingen:
- DATABASE_CONNECTION_URL (standaard: "jdbc:mysql://localhost:3306/bp2_drankbuddy")
- DATABASE_USERNAME (standaard: "root")
- DATABASE_PASSWORD (standaard: "admin")
```

Bijvoorbeeld:
```java
private static final String DATABASE_CONNECTION_URL = "jdbc:mysql://localhost:3306/bp2_drankbuddy";
private static final String DATABASE_USERNAME = "jouw_gebruikersnaam";
private static final String DATABASE_PASSWORD = "jouw_wachtwoord";
```

#### 4. Applicatie bouwen
```
Navigeer naar de projectmap in de terminal en voer het volgende commando uit:
mvn clean package

Dit genereert een JAR-bestand in de map: target/DrankBuddy-1.0.0-jar-with-dependencies.jar

OF

Via IntelliJ IDEA:
File -> Project Structure -> Artifacts
Maak een nieuwe artifact van het type 'Java' en click op 'From modules with dependencies...'
Selecteer het project 'DrankBuddy' en klik op 'OK'
Dan builden via IntelliJ IDEA en hij is te vinden in de map:
out/artifacts/DrankBuddy_jar/
```

#### 5. Applicatie starten
```
Start de applicatie door het JAR-bestand uit te voeren:
java -jar target/DrankBuddy-1.0.0-jar-with-dependencies.jar

Of gebruik Maven:
mvn javafx:run
```

## Functionaliteiten

### Accountbeheer:
```
Inloggen met bestaande accounts
Nieuwe accounts registreren
Beveiligde toegang tot het systeem
```

### Categoriebeheer:
```
Nieuwe categorieën toevoegen
Bestaande categorieën bewerken
Categorieën verwijderen
Overzicht van alle categorieën
```

### Productbeheer:
```
Nieuwe producten toevoegen
Productgegevens bewerken
Producten verwijderen
Alle producten bekijken
Producten koppelen aan categorieën
```

### Voorraadmutaties:
```
Voorraadwijzigingen registreren (voorraad verhogen/verlagen)
Mutaties bekijken per product
Overzicht van alle voorraadmutaties
Historisch overzicht van voorraadveranderingen
```

## Gebruik

Open de applicatie.

Log in met je gebruikersaccount of registreer een nieuw account.

Als de applicatie niet opstart of geen verbinding maakt met de database, controleer dan:
- Of MySQL draait
- Of de database 'bp2_drankbuddy' correct is geïmporteerd
- Of de inloggegevens in DatabaseConnectionProvider.java correct zijn ingesteld

Gebruik de navigatiemenu's om te schakelen tussen:
- **Categorieën:** Beheer productcategorieën
- **Producten:** Voeg producten toe, bewerk of verwijder ze
- **Voorraadmutaties:** Registreer voorraadwijzigingen en bekijk de geschiedenis

## Technologieën

* JavaFX 25.0.1
* CSS (JavaFX CSS) (voor styling)
* MySQL Database
* JDBC voor database connectiviteit
* Maven voor dependency management

## Implementatieplan

### Huidige staat van het systeem

Het DrankBuddy-systeem is volledig functioneel in een ontwikkelomgeving met de volgende kenmerken:
- JavaFX desktop applicatie
- Lokale MySQL database
- Basis authenticatie en autorisatie
- CRUD-operaties voor producten, categorieën en voorraadmutaties

#### Acceptatiecriteria
Het systeem wordt als succesvol geïmplementeerd beschouwd wanneer:
- ✅ Alle gebruikers kunnen inloggen en hun taken uitvoeren
- ✅ Database is stabiel en performant (response tijd < 2 seconden)
- ✅ Geen kritieke bugs aanwezig
- ✅ 90% van de gebruikers heeft de training succesvol afgerond
- ✅ Backup en restore procedures zijn getest en functioneel

## Bijdragen

Bijdragen maken de open source community een geweldige plek om te leren, inspireren en creëren. Alle bijdragen die je maakt worden **zeer gewaardeerd**.

Als je een suggestie hebt die dit beter zou maken, fork dan de repo en maak een pull request. Je kunt ook simpelweg een issue openen met de tag "enhancement".
Vergeet niet om het project een ster te geven! Nogmaals bedankt!

1. Fork de repository

2. Maak een nieuwe branch:
```
git checkout -b feature-naam
```

3. Commit je wijzigingen:
```
git commit -m "Voeg nieuwe feature toe"
```

4. Push naar je fork:
```
git push origin feature-naam
```

5. Open een pull request

## Roadmap

- [x] Functie om producten toe te voegen, bewerken, bekijken en verwijderen
- [x] Functie om categorieën te beheren
- [x] Functie om voorraadmutaties te registreren
- [x] Account registratie en login systeem
- [x] JAR-bestand genereren voor distributie
- [ ] Rapporten genereren (Excel/PDF export)
- [ ] Dashboard met statistieken en grafieken
- [ ] Automatische waarschuwingen bij lage voorraad
- [ ] Barcode scanner integratie
- [ ] Multi-user permissions en rollen
- [ ] Cloud-based deployment optie
- [ ] Mobile app versie

Zie de [open issues](https://github.com/boriskole/bp2_drankbuddy/issues) voor een volledige lijst van voorgestelde features (en bekende problemen).

## Contact

Boris Kole - boris.kole@student.ad-academie.nl

Project Link: [https://github.com/boriskole/bp2_drankbuddy](https://github.com/boriskole/bp2_drankbuddy)

## Licentie

Dit project is ontwikkeld als onderdeel van een schoolopdracht voor de AD Academie.

---

**Ontwikkeld met ❤️ voor efficiënt voorraad beheer**

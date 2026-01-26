# 🍺 DrankBuddy

DrankBuddy is een moderne JavaFX applicatie voor het eenvoudig beheren van drank voorraden. Of je nu een particulier bent met een thuisbar, of een horecaondernemer met een uitgebreide voorraad, DrankBuddy helpt je om altijd overzicht te houden.

## 📋 Overzicht

DrankBuddy biedt een intuïtieve interface om je drankvoorraad te beheren, van bier en wijn tot sterke drank en frisdranken. Houd bij wat je hebt, wat je nodig hebt, en ontvang meldingen wanneer het tijd is om bij te bestellen.

## ✨ Features

- 📊 **Voorraad Beheer**: Houd je volledige drankvoorraad bij met gedetailleerde informatie
- 🔍 **Zoek & Filter**: Vind snel specifieke dranken in je collectie
- 📈 **Rapportages**: Inzicht in je voorraad en verbruik
- 🔔 **Notificaties**: Ontvang meldingen bij lage voorraad
- 💾 **Data Persistentie**: Je gegevens worden veilig opgeslagen
- 🎨 **Modern UI**: Gebruiksvriendelijke interface gebouwd met JavaFX

## 🚀 Aan de slag

### Vereisten

Om DrankBuddy te kunnen gebruiken heb je het volgende nodig:

- **Java Development Kit (JDK) 17 of hoger**
- **JavaFX SDK** (indien niet inbegrepen in je JDK)
- **Maven** of **Gradle** (voor het bouwen van het project)
- **IntelliJ IDEA** (aanbevolen, maar elke IDE met JavaFX ondersteuning werkt)

### Installatie

1. **Clone de repository:**
   ```bash
   git clone https://github.com/boriskole/bp2_drankbuddy.git
   cd bp2_drankbuddy
   ```

2. **Open het project in IntelliJ IDEA:**
   - File → Open → Selecteer de project directory
   - IntelliJ zal automatisch de project structuur detecteren

3. **Configureer JavaFX (indien nodig):**
   - File → Project Structure → Libraries
   - Voeg de JavaFX SDK library toe als deze nog niet aanwezig is

4. **Build het project:**
   
   Met Maven:
   ```bash
   mvn clean install
   ```
   
   Met Gradle:
   ```bash
   ./gradlew build
   ```

### Applicatie uitvoeren

**Via IntelliJ IDEA:**
- Navigeer naar de main class
- Klik op de groene "Run" knop

**Via command line:**

Met Maven:
```bash
mvn javafx:run
```

Met Gradle:
```bash
./gradlew run
```

## 🛠️ Development

### Project Structuur

```
bp2_drankbuddy/
├── src/
│   ├── main/
│   │   ├── java/          # Java source code
│   │   └── resources/     # FXML, CSS, images, etc.
│   └── test/
│       └── java/          # Unit tests
├── .gitignore             # Git ignore file
├── pom.xml / build.gradle # Build configuration
└── README.md              # Deze file
```

### Technologieën

- **JavaFX**: Voor de gebruikersinterface
- **Maven/Gradle**: Build en dependency management
- **JUnit**: Voor unit testing
- **FXML**: Voor UI layout definitie

### Ontwikkel in IntelliJ IDEA

1. **JavaFX Scene Builder**: 
   - Download Scene Builder voor visuele FXML editing
   - Configureer in IntelliJ: Settings → Languages & Frameworks → JavaFX

2. **VM Options voor JavaFX**:
   Als je JavaFX modules handmatig moet laden, voeg deze VM options toe:
   ```
   --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml
   ```

3. **Code Style**:
   - Gebruik de standaard IntelliJ Java code style
   - Zorg voor proper formatting voor commits

### Testing

Run alle tests:

Met Maven:
```bash
mvn test
```

Met Gradle:
```bash
./gradlew test
```

## 🤝 Bijdragen

Bijdragen aan DrankBuddy zijn welkom! Om bij te dragen:

1. Fork de repository
2. Maak een feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit je wijzigingen (`git commit -m 'Add some AmazingFeature'`)
4. Push naar de branch (`git push origin feature/AmazingFeature`)
5. Open een Pull Request

### Code Richtlijnen

- Schrijf duidelijke, self-documenting code
- Voeg comments toe waar logica complex is
- Schrijf unit tests voor nieuwe functionaliteit
- Volg de bestaande code style
- Update de documentatie indien nodig

## 📝 Licentie

Dit project is gelicenseerd onder de MIT License - zie het [LICENSE](LICENSE) bestand voor details.

## 👤 Auteur

**Boris Kole**

- GitHub: [@boriskole](https://github.com/boriskole)

## 🙏 Acknowledgments

- Dank aan alle contributors die hebben bijgedragen aan dit project
- JavaFX community voor de uitstekende documentatie en ondersteuning

## 📞 Contact

Heb je vragen of suggesties? Open een issue in deze repository!

---

**Happy inventory managing! 🍻**

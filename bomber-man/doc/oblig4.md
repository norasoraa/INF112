# Rapport – innlevering 4
**Team:** *Alliansen* (Gruppe 6): *Jostein Sæle, Aida Skarvatun, Anders Sortun, Guang Qing Zhang, Inana Baker, Nora Soraa*
* Lenke til Trello: https://trello.com/b/TOtWGW70/bomberman

## Prosjektrapport

OBS: vi har ikke gjort endringer på oblig1-3.md
Denne rapporten inneholder kun det som er nytt siden forrige sprint.
Det ligger dokumenter i /doc/B, men disse er ført inn i oblig4.md, så det er det samme som står der.
Klassediagram i doc-mappen har blitt oppdatert siden sist.
Henviser også til referatene ift spørsmålene til prosjektrapporten.

**Generelt:** siden det bare er litt over to uker siden vi skrev forrige prosjektrapport, er det ikke så mye endring siden sist.

### Roller
* Hvordan fungerer rollene i teamet? Trenger dere å oppdatere hvem som er teamlead eller kundekontakt?
   - I denne sprinten har vi beholdt rollene fra sist, og har ikke gjort noen endringer i disse.
   - Alle er enige om at rollene har fungert fint. 

### Prosjektmetodikk
* Er det noen erfaringer enten team-messig eller mtp prosjektmetodikk som er verdt å nevne?
	- Gruppen fremhever nytten av å ha fysiske møter to ganger i uken, og ha presise arbeidsoppgaver til hver person.
	- Vi syns vi har fått en god arbeidsflyt ift å skrive krav, diskutere implementasjoner, fordele arbeidsoppgaver, og skrive tester.
	- Ang ting vi kunne gjort annerledes: se retrospektiv for hele prosjektet under.

### Gruppedynamikk og kommunikasjon.
* Hvordan er gruppedynamikken? Er det uenigheter som bør løses? Hvordan fungerer kommunikasjonen for dere?
   - Fortsatt bra! Ikke så mye nytt å tilføye siden forrige gang.

### Retrospektiv for denne sprinten
* Gjør et kort retrospektiv hvor dere vurderer hva dere har klart til nå, og hva som kan forbedres. 
	- Vi er veldig fornøyd med hvordan spillet har blitt!
	- Vi syns vi har klart å implementere veldig mye variert funksjonalitet som gir en god brukeropplevelse.
	- Henviser til retrospektiv for hele prosjektet (under) for mer omfattende kommentarer.

* Under vurdering vil det vektlegges at alle bidrar til kodebasen. 
	- Ikke så mye nytt å nevne siden forrige gang.
	- Vi har videreført fokuset vi har hatt tidligere.

* Bli enige om maks tre forbedringspunkter fra retrospektivet, som skal følges opp under neste sprint.
	- Dette var siste sprint, så da har vi ingen oppfølgingspunkter.

### Retrospektiv for hele prosjektet
* For siste innlevering (Oblig 4): Gjør et retrospektiv hvor dere vurderer hvordan hele prosjektet har gått. Hva har dere gjort bra, hva hadde dere gjort annerledes hvis dere begynte på nytt?
	
	- I starten av prosjektet diskuterte vi grundig forskjellige forslag til hvilket spill vi skulle lage. Vi benyttet oss av gruppens tidligere erfaringer til å sette realistiske mål for prosjektet.
   - Vi er generelt fornøyd med valget vårt av spill. Det har bydt på passe store utfordringer, og opplevdes som gjennomførbart.
	- Da vi jobbet frem mot MVP syns vi det var litt vanskelig å jobbe selvstending, da veldig mye av funksjonaliteten måtte diskuteres i plenum, og den overordnede strukturen i koden ikke var på plass ennå. 
	- Vi følte kanskje at prosjektet ikke var stort nok for 6 personer i starten. 
	- Det var også veldig uvant å diskutere mer enn man kodet. Men, vi ser at det førte til gode beslutninger, og at løsningene ble mer robuste og gjennomtenkte. Fordel med flere perspektiver!
	- Det var mye å lære ift git / pushing / pulling / commits / håndtering av merge-konflikter. Men vi føler vi har blitt mye mer stødig på dette underveis.
	- Da vi var ferdig med MVP, hadde vi veldig mange av de grunnleggende funksjonalitetene på plass. Grunnleggende Model-, View- og Controller-objekter var implementert. Vi syns da det ble lettere å fordele individuelle arbeidsoppgaver som man kunne jobbe med selvstendig. 
   - Det hjalp også at vi fikk bedre rutine på gruppemøtene. Vi endte opp med følgende struktur:
      1. Gå gjennom kode/dokumentasjon skrevet siden forrige møte.
      2. Diskuter endringer/bugs ift eksisterende funksjonalitet i fellesskap.
      3. Jobb med dette, eller begynn å planlegg ny funksjonalitet.
      4. Fordel arbeidsoppgaver til neste gang. Kom gjerne med forslag til konkret implementasjon
	
	- Ting vi hadde gjort annerledes hvis vi begynte på nytt:
	   - Tydeligere arbeidsoppgaver fra starten.
	   - Bruke mer visuelle hjelpemiddel for å formidle ideer.
	   - Bli flinkere på å skrive kommentarer på kode man skriver, selv om implementasjonen ikke nødvendigvis er 'final'.
	   - Vi skrev tester litt i 'bolker', men vi kunne nok heller skrevet dem litt mer fortløpende.
	   - Generelt committe oftere, og i mindre porsjoner.

## Referater

### Torsdag 11. april
- **Tilstede:** Jostein, Aida, Nora, Guang, Anders, Inana
- **Gjort i dag:**
  - Gått gjennom krav og sjekket at alt er ferdigstilt
  - Timer: hvordan få eksplosjonene til å pause når spillet er i pause
  - Fikset testEnemyPlayerCollision()
- **Ingen oppgaver til neste gruppetime.**

### Mandag 15. april
- **Tilstede:** Jostein, Aida, Nora, Guang, Anders
- **Gjort i dag:**
  - Kartlagt hvilke funksjonaliteter vi vil implementere i siste sprint
  - Diskutert implementasjoner for disse funksjonalitetene
  - Fordelt arbeidsoppgaver
- **Arbeidsoppgaver for siste sprint:**
  0. Aida - Implementere nye timere for bomber og eksplosjoner.
  1. Anders - goToNextLevel-metode
  2. Ikke fordelt - Når du plukker opp en powerup -> Vis tekst som forteller hva en powerup gjør
  3. Nora - Tekst som viser hvilken level vi er på, istedenfor GameState
  4. Guang - Healthpoints viser som hjerter istedenfor et tall. F.ex 3 hjerter hvis du har tre liv.
  5. Nora - Ved GameOver -> Ha tekst som forteller om du gikk tom for tid eller døde.
  6. Jostein - Hvis vi får tid: ha retning og/eller animasjon på player og fiender.
  7. Inana - Ha en powerup som er +1 hearts

### Torsdag 18. april
- **Tilstede:** Jostein, Aida, Nora, Anders, Guang
- **Gjort i dag:**
  - Sett på nye implementasjoner
  - Bug: spilleren blir av og til usynlig før den tar skade igjen
  - Bug: spilleren tar ikke skade flere ganger når en fiende står lengre på samme posisjon, de må da bevege seg inn i hverandre på nytt
  - Jobbet videre med oppgaver vi har fra før eller funnet nye oppgaver i gruppetimen, også jobbet med bugsene
- **Til neste gang:**
  - Lage flere brett, alle kan lage forslag hvis de ønsker det
  - Hvis man har implementert noe nytt, skrive tester til dette hvis det mangler

### Mandag 22. april
- **Tilstede:** Jostein, Aida, Nora, Guang, Inana
- **Gjort i dag:**
  - Fikset bug: spilleren tar ikke skade flere ganger når en fiende står lengre på samme posisjon, de må da bevege seg inn i hverandre på nytt
  - Snakket om DefaultEnemySpeed og enemySpeed i constants og controller for å kunne manipulere farten fiendene beveger seg i
  - Jobbet med egne oppgaver, det som trengs rundt om i koden
- **Til neste gang**
  - Skrive krav for: animasjoner, ny powerup med hjerte, og tydeligere formidlinger til spiller gjennom tekst
  - Alle: fjerne ubrukte imports
  - Alle: lage flere brett
  - Anders: Test: globalTimerTest(), skrive om på denne

### Torsdag 25. april
- **Tilstede:** Jostein, Aida, Nora, Guang, Anders
- **Jobbet med i dag og videre:**
  - Nora: setLevel() i GameModel; ikke laste inn ny LevelReader, men bruke funksjonalitet vi allerede har i LevelReader:setNewLevel()
  - Aida: Tekst når vi plukker opp en powerup; ha tekst som forklarer hva denne powerupen gjør
  - Guang: Vise max bombs på skjermen; ha et tall på skjermen som viser hvor mange bomber spilleren kan legge ut
  - Jostein: Ta vekk konstruktør i LevelReader, oppdatere dokumentasjon for LevelReader.
  - Anders: Lage brett

### Mandag 29. april
- **Tilstede:** Jostein, Aida, Nora, Guang, Anders, Inana
- **Gjort i dag:**
  - Laget nytt interface TestableModel for public metoder som bare brukes i tester
  - Ryddet litt opp i GameModel generelt
  - Skrevet prosjektrapport
- **Arbeidsoppgaver:**
  - Jostein: Renskrive oblig4
  - Nora: Sende referat for å innføre i oblig4
  - Aida: Prøve å rydde i GameView, Sentrere powerup tekst
  - Guang: Lage klassediagram, Dra gameinfo tekst lenger opp i GameView
  - Inana: Tegne powerup animasjoner, Skrive krav: animasjoner
  - Anders: Lage brett, Rydde i kode og finskrive
- **For alle:**
  - Slette ubrukte imports
  - Slette ubrukt kode
  - Skrive javadoc hvis det mangler

### Torsdag 2. mai
- **Tilstede:** Jostein, Aida, Nora, Guang, Anders, Inana
- **Gjort i dag:**
   - Aida har delt opp View i flere klasser siden sist, og vi har skrevet javadoc for disse klassene.
   - Jobbet med powerups-animasjoner som Inana har laget.
   - Laget nye leveler, og stemt over hvilke vi vil ha med i spillet.
   - Ferdigstille koden og diskutert presentasjonen.

- **Til neste gang:**
   - Nyte det fine været! 😄
   - (og tenke litt på presentasjonen)


## KRAV

- Oppdater hvilke krav dere har prioritert, hvor langt dere har kommet og hva dere har gjort siden forrige gang. Er dere kommet forbi MVP? Forklar hvordan dere prioriterer ny funksjonalitet.
  - Denne sprinten har vi jobbet mest med å gjøre spillet mer brukervennlig, utvide/forbedre eksisterende funksjonalitet, og implementere animasjoner.
  - Vi har også fokusert mye på å rydde opp i koden, slette ubrukte metoder, koble public metoder opp mot interfaces, skrive kommentarer og tester.
  - Vi anser nå spillet som ferdig!
  - Dette er siste sprint, så da planlegger vi ingen ny funksjonalitet.

---

## FERDIGE KRAV:

### 1. Neste level kan startes når forrige level er ferdig

- **Brukerhistorier:**
   - "Som spillutvikler ønsker jeg å ha flere nivåer, sånn at spillopplevelsen blir mer variert".
   - "Som spiller er det viktig med en følelse av progresjon, f.ex ved at du kommer til et nytt nivå når forrige nivå er ferdig".

- **Arbeidsoppgaver:**
   - Har en int som er currentLevel.
   - Lag en `goToNextLevel`-metode i GameModel
      - Den skal kalle `setLevel()` med riktig navn.
      - Ha f.ex en hashmap<Integer, String>, der vi mapper level-tallet til riktig filnavn.
      - Hvis vi er på siste level -> loop tilbake til første level.
   - Må lage metode i View som kan kalles for å oppdatere `cellSize` etc, siden det nye GameBoard-objektet kan ha andre dimensjoner.
      - Denne metoden må kalles når den neste levelen startes.

- **Akseptansekriterier:**
   - Test - Sjekk at `goToNextLevel` fører til at `currentLevel` økes med +1 (med mindre det er siste level, og da skal den loope tilbake til level1).
   - Test - Sjekk at `goToNextLevel` gjør at et nytt GameBoard lastes inn.


### 2. Healthpoints vises som hjerter i stedet for et tall

- **Brukerhistorier:**
   - "Som spiller ønsker jeg å se hjerter i stedet for tall for å representere mine healthpoints, slik at spillopplevelsen blir mer interessant".
   - "Som spiller ønsker jeg å kunne se antall gjenværende hjerter tydelig, slik at det er lett å se når jeg mister healthpoints eller får et nytt liv".

- **Arbeidsoppgaver:**
   - Lag en hjerte-sprite 25x25 px.
   - Lag en metode i GameView: `drawPlayerHearts()`
      - Den skal tegne hjerter på skjermen som symboliserer antall liv spilleren har igjen.
      - Bruk en konstant til å definere størrelsen på bildet.
      - Metoden skal kalles i `drawGame()` metoden i GameView.

- **Akseptansekriterier:**
   - Manuell test - Ved oppstart av nytt level skal det vises tre hjerter under healthpoints på skjermen.
   - Manuell test - Når spilleren tar skade skal et hjerte forsvinne under healthpoints på skjermen.
   - Manuell test - Når spilleren plukker opp en hjerte-powerup skal det komme et nytt hjerte under healthpoints på skjermen.


### 3. Ny hjerte-powerup

- **Brukerhistorier:**
   - "Som spiller synes jeg det er kjekkere å kunne ha en powerup for et ekstra liv i spillet. På denne måten kan jeg forlenge spillopplevelsen."

- **Arbeidsoppgaver:**
   - Legg til en ekstra powerup for hjerter i `activate`-metoden i PowerUp-klassen.
      - Skal kalle `increaseLife`-metoden når spilleren har plukket opp en hjerte-powerup.
   - Lag en `increaseLife`-metode i Player
      - Det skal bli maks 3 hjerter som liv.
      - Et ekstra liv skal bare legges til hvis healthpoints til spilleren er under 3.

- **Akseptansekriterier:**
   - Test - Sjekk om spilleren har fått et ekstra liv etter å ha plukket opp en hjerte-powerup, med unntak av hvis spillerens healthpoints er på sin maks.


### 4. Animasjoner

- **Brukerhistorier:**
   - "Som en spiller vil jeg se animasjoner for å forbedre spillopplevelsen og gjøre det tydeligere når handlinger utføres av spilleren og fiender".

- **Arbeidsoppgaver:**
   - Tegne animasjoner for spiller, fiender, bomber og eksplosjoner.
   - En 2D-TextureRegion-array kan brukes for å representere en animasjon i programmet. I arrayen vil hver rad representere en 'tilstand' (f.ex at spilleren løper mot venstre). Hver kolonne vil representere ett bilde i denne animasjonen. Ha en animasjonsIndex (int) som oppdateres jevnlig, og representerer hvilken kolonne animasjonen er på.
   - Implementer en metode i GameView for å konstruere en animasjons-array fra et sprite-bilde.
   - Endre eksisterende kode for å bruke animasjonene i stedet for statiske bilder.

- **Akseptanskriterier:**
   - Visuell test - Sjekk at spillerens og fiendenes animasjon endres avhengig av retningen de beveger seg i.
   - Visuell test - Sjekk at spilleren har egne animasjoner for å stå i ro og gå.
   - Visuell test - Sjekk at når en bombe eksploderer, skal det vises en animasjon for dette.
   - Visuell test - Sjekk at animasjonene for spilleren og fiendene er jevne og naturlige under bevegelse.


## Kommentarer til Kode
* Ingen


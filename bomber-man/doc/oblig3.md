# Innlevering 3
**Team:** *Alliansen* (Gruppe 6): *Jostein Sæle, Aida Skarvatun, Anders Sortun, Guang Qing Zhang, Inana Baker, Nora Soraa*
* Lenke til Trello: https://trello.com/b/TOtWGW70/bomberman

## Prosjektrapport

OBS: vi har ikke gjort endringer på oblig1.md eller oblig2.md.
Denne rapporten inneholder kun det som er nytt siden forrige sprint.
Det ligger dokumenter i /doc/B, men disse er ført inn i oblig3.md, så det er det samme som står der.
Klassediagram i doc-mappen har blitt oppdatert siden sist.
Henviser også til referatene ift spørsmålene til prosjektrapporten.

### Roller
* Hvordan fungerer rollene i teamet? Trenger dere å oppdatere hvem som er teamlead eller kundekontakt?
  - **Anders:** Har fortsatt som lyd-ansvarlig, kundekontakt og Discord-ansvarlig. Han syns dette har gått greit.
  - **Jostein:** Har fortsatt som Teamlead og har vært bildeansvarlig. Har gått greit, det er ikke ønske i teamet om å bytte teamlead.
  - **Aida:** Har fortsatt med å jobbe mest med programmering. Syns dette går fint.
  - **Inana:** Fikk i utgangspunktet rolle som test-ansvarlig, men har byttet til å jobbe mer med bilder og programmering av fiender i denne sprinten. Fremover ønsker hun også å være aktiv i rollen som test-ansvarlig.
  - **Nora:** Har hatt ansvar for å skrive referater, og hun ønsker å fortsette med det. Har også hatt ansvar for GameBoard og LevelReader, og ellers jobber litt med det som trengs. Er fornøyd med det.
  - **Guang:** Har vært testansvarlig, ansvarlig for booking av grupperom til gruppetimer, samt jobbet med klassediagram og Interfaces. Har også sørget for oversikt over test-dekning av koden.

Oppsummert: som sist har vi oppdaterte rollene underveis, og generelt er folk fornøyd med rollene de har nå.

### Prosjektmetodikk
* Er det noen erfaringer enten team-messig eller mtp prosjektmetodikk som er verdt å nevne?
   - Fra forrige gang ønsket vi å bli tydeligere til å spesifisere arbeidsoppgaver Dette har vi fokusert mye på i denne sprinten, og vi er enige om at dette har gjort det enklere å jobbe selvstendig.
   - I starten av hver gruppetime har vi tatt en oppsummering av kode som er skrevet siden forrige. Dette har gjort det enklere å holde oversikt over endringer som andre har gjort i koden.
   - Vi har videreført rutinen med å ha to møter i uken, og vi syns vi har hatt godt utbytte av dette.
   - En erfaring noen har gjort seg, er at det kan være vanskelig / uoversiktelig når man skal merge en branch med main, og det kan være mange konflikter som må håndteres. Vi har prøvd å løse det ved å pushe jevnlig, og unngå å jobbe på de samme objektene. Men, noen merge-konflikter kan likevel oppstå.
   - Ellers er vi alle enige om at teamet fungerer godt

### Gruppedynamikk og kommunikasjon.
* Hvordan er gruppedynamikken? Er det uenigheter som bør løses?
   - Vi syns ting fungerer bra! Vi har gode diskusjoner og kommer med innspill til hverandre.
   - Det hender vi har litt forskjellig ideer ift hvordan funksjonalitet skal implementeres, men dette løser vi fint i fellesskap.

* Hvordan fungerer kommunikasjonen for dere?
   - Kommunikasjon fungerer fint! Jevnlige gruppemøter og bruk av Discord gjør at vi er i kontakt mange ganger i uken. 
   - Hvis noen ikke har mulighet til å komme på møtene, sier de i fra på Discord. De kan lese referatet fra møtet for å få en oppsummering.


### Retrospektiv
* Gjør et kort retrospektiv hvor dere vurderer hva dere har klart til nå, og hva som kan forbedres. 
   - Vi har oppnådd det vi ønsket å gjøre bedre sist, nemlig å tydeligere definere arbeidsoppgaver til hvert enkelt medlem i gruppen (se notat over).
   - En ting vi diskuterte sist, var at vi ønsket å oppdatere Trello-boardet mer jevnlig. Formålet med dette var å holde oversikt over hvilke arbeidsoppgaver som skulle gjøres. Dog, syns vi det har vært mer nyttig å lese referatene og txt-filen med krav, da disse inneholder mer utfyllende, oppdatert og relevant informasjon. 
   - Siden vi hadde litt færre krav denne sprinten, har vi klart å ferdigstille hvert enkelt krav i høyere grad enn forrige sprint.
   - Kan forbedres: vi har ikke funnet en 100% treffsikker rutine for å unngå merge-konflikter.

* Under vurdering vil det vektlegges at alle bidrar til kodebasen. 
   - Jostein har mange commits, men dette er delvis fordi de består av mange
    små endringer, f.ex at en commit bare inneholder en kommentar.
   - OBS ift navn på Git: "Mona Lisa" er Guang.
   - I tillegg gjelder samme som sist, med at noen av Jostein sine commits er gjort etter gruppetime, der alle har bidratt. F.ex er denne prosjektrapporten skrevet i fellesskap, og alle har kommet med innspill (men Jostein har comittet).
   - Som sist, er det forskjell på hvor hyppig folk committer. Noen pusher sjeldnere, men med større endringer. Når man jobber med en omfattende implementasjon blir det gjerne til at man pusher sjeldnere.

* Bli enige om maks tre forbedringspunkter fra retrospektivet, som skal følges opp under neste sprint.
   - Vi ønsker å bli litt flinkere til å skrive dokumentasjon mens vi jobber med koden.

## Referater

### Torsdag 7. mars
- **Tilstede:** Jostein, Aida, Nora, Guang, Inana, Anders
- `isWallAt()`:
  - Ny metode i GameBoard som sjekker om det er en vegg på posisjonen.
  - Hvis vi kommer over steder i koden hvor dette sjekkes manuelt, kan vi bytte det ut med denne metoden

**Gjort i dag:**
- Ferdigstilt det meste av oblig2
- Må ses over og sys sammen
- Jobbet litt videre med individuelle oppgaver, blant annet retting av tester

### Mandag 11. mars
- **Tilstede:** Jostein, Aida, Nora, Guang, Anders
- **Plan videre:**
  - Ferdigstille krav vi holder på med, det mangler tester
  - Fikse bugs som er nevnt i oblig2
- **Arbeidsfordeling på tester:**
  - Guang, Aida: Jobbe videre med test i GameModel
  - Jostein: Krav 1, Krav 2, Krav 3
  - Nora: Krav 4, Skrive checkAndTakePlayerDamage i GameModel og teste denne
  - Anders: Krav 5

### Torsdag 14. mars
- **Tilstede:** Nora, Anders, Quang, Jostein, Aida, Inana
- **Begynt å diskutere videre krav:**
  - Ligger under Nye_Krav.txt
- **Plan neste gang:**
  - Anders: Bilder, legge inn illustrasjonene vi har på riktig plass.
  - Jostein og Nora: controll.damagePlayer
  - Nora: Nå blir koordinatene i brettet lest fra bunnen og opp - fikse dette.
  - Inana og Aida: jobbe med implementering av powerups
  - Quang: Begynne på startskjerm. drawStartScreen: valgfritt hvordan dette skal se ut, men ha med hvordan tastetrykkene fungerer til spilleren
- **Generelle ting som må fikses etterhvert:**
  - Får feilmelding på noen pcer når man resizer skjermen.

### Mandag 18. mars
- **Tilstede:** Jostein, Aida, Nora, Guang, Inana
- **I dag:**
  - Har snakket litt i fellesskap om powerup
  - Funnet en løsning for å tegne brettet riktig vei
- **Til neste gang:**
  - Jobbe videre med oppgavene våre
  - En del av disse står på forrige referat

### Torsdag 21. mars
- **Tilstede:** Jostein, Anders, Nora, Guang, Inana
- **Jostein:**
  - Jobber med skalering av skjermen
  - Tegne forskjellige game state, i view
- **Nora:**
  - Skrive krav om at fiendene kan bevege seg
- **Inana:**
  - Javadoc for PowerUp
  - Fiender kan bevege seg
- **Guang:**
  - Tester, hvordan kan vi vite test coverage
- **Anders:**
  - Gå til forskjellige game state, i controller
- **Aida:**
  - PowerUpFactory
  - Javadoc for Player
- Ser vi public metoder eller klasser som mangler javadoc kan vi gjerne skrive disse

### Torsdag 4. april
- **Tilstede:** Jostein, Anders, Aida, Inana, Nora
- **Dette har vi snakket om at må fikses:**
  - Få alle tester til å passere
  - Skal ikke kunne spille under startskjermen
  - Spøkelser skal ikke kunne bevege seg under gameover
  - Fikse pauseskjerm
  - Kunne gå mellom de ulike gamestates uten problemer
  - Se på prosjektrapport
  - Lage klassediagram
  - Fiender skal ikke kunne gå gjennom eksplosjoner
  - Etter level finished kan du trykke enter og fortsette å spille selv om tiden er 0
- **I gruppetimen ble dette fikset:**
  - Kan ikke spille under startskjerm
  - Spøkelser beveger seg ikke under gameover
- **Dokumentasjon som mangler:**
  - Se listen

### Mandag 8. april
- **Tilstede:** Jostein, Aida, Nora, Guang, Anders, Inana
- **Gjort i dag:**
  - Skrevet prosjektrapport i fellesskap
- **Må gjøres:**
  - Tester trenger kommentarer og dokumentasjon
  - Javadoc generelt i koden
  - Ferdigstille listen på dokumentasjon som mangler fra forrige gang
- **Individuelle oppgaver:**
  - Inana: Ferdigstille tester for at fiendene beveger seg
  - Anders: Test: når vi trykker enter blir gamestate PLAYING
  - Guang: Klassediagram, Dokumentasjon: BombFactory, Explosion, ViewableEntity
  - Aida: Dokumentasjon: EntityModel
  - Jostein: Dokumentasjon: Controller, Forsøke å få brettet til å passe enda bedre inn på skjermen
  - Nora: Variabler i BomberKeyboard til protected, Resten av dokumentasjon
  - Alle skriver dokumentasjon der de ser at det er nødvendig.


## KRAV

- Oppdater hvilke krav dere har prioritert, hvor langt dere har kommet og hva dere har gjort siden forrige gang. Er dere kommet forbi MVP? Forklar hvordan dere prioriterer ny funksjonalitet.
  - Denne sprinten har vi jobbet med å ferdigstille testene for MVP-kravene fra sist, samt lage nye krav og implementere disse.
  - Vi har implementert all koden som trengs for å tilfredsstille de nye kravene, samt testene.
  - Spillet har nå mesteparten av den grunnleggende funksjonaliteten vi ønsket å få til. Det er selvfølgelig mange ting vi kunne utviklet videre, men vi fokuserer på kvalitet av eksisterende funksjonalitet, heller enn kvantitet.
  - Vi prioriterte ny funksjonalitet ved å vurdere hvor viktig vi anser det for spiller-opplevelsen, og hvorvidt vi tror vi får tid til å ferdigstille det.

---

## FERDIGE KRAV:

### 1. Fiendene kan bevege seg

- **Brukerhistorier:**
  - "Som spiller ønsker jeg at fiendene i spillet kan bevege seg, slik at spillopplevelsen blir mer dynamisk og utfordrende."
   - "Som spillutvikler er det viktig at fiendene skal kunne reagere på endringer i spillopplevelsen, for eksempel å endre retning når de møter hindringer."
   - "Som spillutvikler er det viktig at spilleren mister healthpoints hvis den blir tatt av en fiende."

- **Arbeidsoppgaver:**
   - Lage metode som gir neste tilfeldige retning som fienden skal bevege seg i.
   - Lage metode for å regne ut den nye posisjonen til fienden og flytte den dit.
   - Gi ny tilfeldig retning som fienden skal bevege seg i når den møter en hindring.
   - Hvis en fiende beveger seg inn i spilleren, skal spilleren miste healthpoints.

- **Akseptanskriterier:**
   - Test - Sjekk at fienden flytter seg i forskjellige tilfeldige retninger.
   - Test - Når fienden beveger seg skal fiendens posisjon endres i henhold til den beregnede nye posisjonen.
   - Test - Når fienden kommer i kontakt med spilleren, skal spillerens healthpoints reduseres.
   - Manuell test - Se at fiendene beveger seg jevnt over brettet og bytter retning når de kommer til en hindring.

### 2. Implementer Powerups

- **Brukerhistorier:**
   - "Som spiller har jeg lyst til å oppgradere karakteren min underveis i spillet, da dette gir mer spennende gameplay."
   - "Som spillutvikler har jeg lyst på muligheten til å enkelt legge inn nye powerups, uten å måtte gjøre større endringer i koden."

- **Arbeidsoppgaver:**
   - Lage et PowerUp-objekt. Tar inn type (int) som argument.
   - Lage metode som gjør en effekt i respons på at Powerup aktivers.
   - Når en barrel eksploderer -> trill terning og sjekk om powerup skal lages. Hvis ja -> trill terning og sjekk hvilken type powerup som skal lages. Legg så Powerupen inn i en liste i model.
   - I move-metoden til player, sjekk om posisjonen overlapper en powerup. Hvis ja -> aktiver metoden som gjør effekten til powerupen, og slett den fra listen.

- **Akseptanskriterier:**
   - Test - Sjekk at powerups blir opprettet når en barrel sprenger.
   - Test - Sjekk at aktivering av en powerup gir den ønskede effekten i player/model.

### 3. Bytter ut de fargede rutene med faktiske bilder

- **Brukerhistorier:**
  - "Som spiller verdsetter jeg det visuelle aspektet, at det er fine bilder med god oppløsning."
   - "Som spillutvikler syns jeg det er viktig at de ulike karakteren i spillet lett kan skilles fra hverandre visuelt, f.ex ved bruk av forskjellige farger for forskjellige karakterer."

- **Arbeidsoppgaver:**
   - Legge inn bilder som Jostein/Inana/Guang har laget i ressurser.
   - Laste dem inn i View.
   - Tegne opp bildene på riktig sted med riktig dimensjon
   - Bruk view.dispose() for å fjerne bildene når programmet lukkes.

- **Akseptanskriterier:**
   - Manuell test - Ser at bildene tegnes opp på riktig sted og ser bra ut.

### 4. Lage startskjerm

- **Brukerhistorier:**
   - "Som spiller er det viktig at spillet viser meg hvordan kontrollene funker."
   - "Som spillutvikler ønsker jeg at startskjermen skal presentere spillet på en tiltalende måte."

- **Arbeidsoppgaver:**
   - I view: lag drawStartScreen-metode.
   - I kontrollen: hvis GameState = START_SCREEN, ikke oppdater modellen ennå. 
   - I tegningen: vis startskjermen hvis GameState = START_SCREEN.
   - Når spilleren trykker ENTER settes GameState til PLAYING.

- **Akseptanskriterier:**
   - Manuell test - Sjekk at vi kan tegne opp startskjermen.
   - Test - sjekk at GameState går til PLAYING når enter blir trykket ned.

### 5. GameBoard tegnes innenfor designert bredde / høyde.

- **Brukerhistorier:**
   - "Som spillutvikler ønsker jeg at spilleren skal kunne lage sine egne brett, og dermed må de passe innenfor skjermen, selv om størrelsen på brettet varierer."
   - "Som spiller ønsker jeg å kunne endre størrelsen på skjermen etter behov".

- **Arbeidsoppgaver:**
   - Definer et skjerm-område som brettet skal tegnes på.
   - Sjekk at både GameBoard sin høyde og bredde (ganget med cellSize) er innenfor dette skjerm-området sin høyde og bredde, respektivt.
   - Hvis ikke, må cellSize justeres slik at det kommer innenfor.
   - Sjekke at brettet tegnes korrekt, selv om libGDX-vinduet endrer størrelse.

- **Akseptanskriterier:**
   - Manuell test - Sjekker at brettet tegnes innenfor designert bredde og høyde, selv om vi endrer dimensjonene til grid. 
   - Manuell test - Prøv å endre størrelsen på vinduet, og sjekk at brettet fremdeles tegnes riktig.


## Kommentarer til Kode
* Det har hendt at BombTest :: BombLeadsToExplosionTest ikke har passert, uten at vi har klart å finne årsaken. Stort sett passerer den alltid. Så det er mulig at denne testen fremdeles er litt ustabil. Mulig vi kommer til å endre på funksjonalitet for Bombene i neste sprint.
* Ellers ingen kommentarer.
* Se dokumentasjon og kommentarer i koden.
# Rapport – innlevering 1
* Team: *Alliansen* (Gruppe 6): *Jostein Sæle, Aida Skarvatun, Anders Sortun, Guang Qing Zhang, Inana Baker, Nora Soraa*

# KORT OPPSUMMERING:

Vi hadde et møte 15.02, der vi så tilbake på arbeidsperioden og diskuterte hvordan det hadde gått.
Gruppen syns generelt det har gått bra denne perioden. Det var mye planlegging og dokumentasjon i starten, og ikke så mye programmering. Dette var litt uvant for flere, da de var mer vant med å kunne 'jobbe i sitt eget tempo'. Men gruppen er enig om at det har vært lærerikt og interessant. Enkelte i gruppen har også programmert litt på egenhånd for å eksperimentere, og så presentert sine ideer for resten i plenum. Mange felles diskusjoner, der alle kunne komme med innspill og spørsmål, sørget for at alle hadde mulighet til å bidra til hvilken retning prosjektet skulle ta. Generelt føler gruppen at det er et godt felles grunnlag for å jobbe videre med programmering nå.

Arbeidsmetodikken (beskrevet senere) har for det meste innebært at hele gruppen jobber samlet.
Vi prøvde en runde med parprogrammering i mindre grupper, og dette var generelt noe gruppen var positiv til å gjøre mer av fremover. Vi syns generelt at parprogrammering bidro til at det ble lettere å fange opp feil, lettere å finne gode løsninger, og få trening i å forklare/konkretisere sine egne ideer.
Det ble også fremhevet at man bør gi hverandre litt tid for å kunne tenke selv, og passe på at alle får prøvd å skrive  kode, siden vi alle jobber i ulikt tempo. Det kan være lett for å gi innspill uten at det egentlig er nødvendig.

Jostein hadde et ønske om å prøve en liten runde med test-drevet utvikling, og de andre i gruppen var enige i at dette kunne være lærerikt. Det blir kanskje tungvint å kjøre TDD på hele prosjektet, men kan være nyttig å ha prøvd metodikken.

Ift hvor mange oppgaver vi tror vi klarer å få til i.la neste iterasjon, tror vi at vi skal klare alle punktene under MVP.


# RØFF BESKRIVELSE AV SPILLET:
* Ref. det original Bomberman-spillet for NES: (https://www.retrogames.cz/play_085-NES.php)

Et 2D-kart som spilleren kan bevege seg i.
   * Kun ett bilde (kamera står i ro)
   * Vegger som spilleren ikke kan gå gjennom eller sprenge
   * Oljetønner som kan sprenges vekk

Spillfigur som spilleren kan styre med piltastene.
   * Kan gå opp, ned, til høyre og venstre.
   * Kan ikke gå på skrå
   * Kan legge ned bomber, som sprenger alle oljetønner innenfor en viss avstand.
   * Kan legge ned max 2 bomber på en gang.

Spøkelser som beveger seg rundt, og kan skade spilleren hvis de kommer ani.
   * Spøkelsene kan ikke gå gjennom vegger eller tønner
   * Spøkelsene kan bli sprengt vekk av bombene.
   * Spilleren får da mer poeng

Powerups ligger inni noen av tønnene.
   * Dukker opp når tønnen er sprengt.
   * Powerups kan f.ex gi mer liv, større bombeeksplosjon, midlertidig uovervinnelighet etc.

Hvis spilleren mister all sine hitpoints, er spillet over, og score vises på skjermen.
   * Spillet kan startes på nytt.

Global klokke som teller ned, og hvis tiden går ut før spilleren har drept alle spøkelsene, er  spillet over.
   * Spillet kan startes på nytt.



# ROLLEFORDELING:

Programmering
* Alle på gruppen har ansvar

Team lead (Jostein)
* Hvorfor: holde overordnet oversikt.
* Jostein har tid og erfaring med spill-utvikling og team-lead.
* Ansvar:
   *Holder oversikt over prosjektet
   *Holder oversikt over krav som skal oppnås
   *Oversikt over hvilken dokumentasjon som må lages

Sekretær (Nora)
* Hvorfor: vi trenger å huske hva vi bestemte på møtene. Hvis ikke alle kan møte, 
  kan de heller lese referat.
* Ansvar:
   *Skriver referat fra møter. Disse kan ligge på Discord

Lyd-ansvarlig (Jostein)
* Hvorfor: vi trenger noen som kan holde oversikt over lydene i prosjektet.
* Jostein har erfaring med lyd/musikk til spill.
* Ansvar:
   *Finner / lager lyd til spillet
   *Se til at vi ikke bryter opphavsrett
   *Inkluder kilder ved behov

Bilde-ansvarlig (Aida)
* Hvorfor: vi trenger noen som kan holde oversikt over bildene i prosjektet.
* Ansvar:
   *Finner / lager bilder til spillet
   *Se til at vi ikke bryter opphavsrett
   *Inkluder kilder ved behov

Discord-ansvarlig og Kundekontakt (Anders)
* Hvorfor: vi trenger noen som kan ha ansvar for kanalene på Discord-gruppen.
* Vi trenger noen som kan kommunisere med kunder (?)

Test-ansvarlig (Guang og Inana)
* Hvorfor: vi trenger noen som kan holde oversikt over testene vi har, og testene som må lages.

Booking-ansvarlig (Guang)
* Hvorfor: trenger noen som kan booke grupperom på mandager / torsdager




# PROSJEKTMETODIKK

Vi har bestemt å bruke Kanban.
   * Vi bruker Trello for å holde oversikt.
   * Vi arbeider med max 2-3 arbeidsoppgaver av gangen.

Møtehyppighet - 2x / uke:
   * Hver gruppetime på torsdag kl 14.15 - 16.00 (Høyteknologi-senteret)
   * Hver mandag kl 10.00 - 12.00 (Realfagsbygget)
   * De som ikke har mulighet til å møte kan lese referat på Discord.

Foreløpig arbeidsmetodikk:
   * Har brukt de felles møtene til å samkjøre, og bli enig om veien videre.
   * Bruker trello til å holde oversikt over arbeidsoppgaver som må gjøres / holder på med.
   * Diskuterer problemer i plenum og sørger for at alle blir enige.
   * Tegner på tavlen / viser skjermbilde for å kommunisere ideer.
   * Bruker Discord gruppechat for kommunikasjon mellom møtene.
   * Har 'hjemmelekse' til neste møte, som alle kan jobbe med.
   * Alle puller fra felles repo.
   * Prøver å unngå at flere jobber i samme fil. Kan bli konflikter når vi pusher til felles repo.
   
Tanker vi har gjort oss for fremtidig metodikk:
   * Skal flere jobbe på samme klasse, i små team på 2-3 stk? Kan kanskje være lurt - flere sjekker at vi følger spesifikasjonene, og lettere å diskutere / oppdage bugs og potensielle problemer med koden. Kan også minne hverandre om å ta pauser (Pomodoro-technique: 25 min jobbing, 5 min pause)
   * Bestemme Policy for når vi pusher til main? Sjekke at alt virker først (skrevet tester?). Gi beskjed til de andre når man har pushet.
   * Hvem skal lage tester?
   * Alle skal prøve litt parprogrammering (A4 i oppgaveteksten)



# SPESIFIKASJON:

Kort beskrivelse av mål med appen:
   * Å lage en spillbar klone av Bomberman med bilder, lyd, enkle kontroller, poeng, game over- og game finished-skjerm.
   * Noe som er litt gøy å spille, har litt sjarm og personlighet.
	* Skal funke bra uten bugs.

Prioritert liste over brukerhistorier:
   * "Som utvikler er det viktig med god struktur i koden og godt mappeoppsett for å enkelt finne og endre kode"
   * "Som programmør er det viktig at jeg får en visuell representasjon av det jeg lager, sånn at jeg kan teste det manuelt."
	* "Som spiller er det viktig at kontrollene er responsive, sånn at jeg raskt kan se hvilke tastetrykk som fører til handling i spillet"
	* "Som spiller er det viktig med god visuell oversikt over b.la poengsum, bomber og andre status-verdier"
	* "Som spiller er det viktig at jeg får visuell indikasjon når spilleren tar skade, sånn at jeg kan reagere umiddelbart"
	* "Som litt svaksynt er det viktig at tekst ikke blir for liten på skjermen"	
	* "Som programmør er det viktig at koden dokumenteres godt, sånn at det blir enklere å forstå den (java-Logging?)"

# MINIMUM VIABLE PRODUCT:
Enkel representasjon av kartet vises på skjermen (enkel grid).
   * Trenger ikke å ha bilder, kun farger.
	* Vegger vises i svart, tomme ruter i hvitt.

Spiller, fiender, bomber og eksplosjoner representert visuelt
   * Spiller vises i blått
   * Tønner vises i grønt
	* Bomber vises i rødt
	* Eksplosjoner i gult
   * Fiender vises i grått? Fiender står i ro (foreløpig)

Spilleren kan legge ut bomber ved tastetrykk.
   * Bomber har timer som teller ned, og eksploderer når timer = 0.
	* Eksplosjon i alle retninger, lengde 2, stoppes av vegg.
   * Hvis en eksplosjon kommer ani en fiende, dør fienden.

Spilleren kan beveges med tastatur.
   * Kan ikke gå ut av grid.
   * Kan ikke gå inn i vegger
   * Kan ikke gå inn i fiender.

Spilleren kan ta skade:
   * Hvis hen kommer ani eksplosjoner
   * Hvis spilleren prøver å bevege seg inn på en fiende.

Spilleren har HP, hvis spilleren dør slutter spillet.
Hvis spilleren har drept alle fiendene = MÅL.
Kan ha tekst som sier om spillet er i gang eller game-over etc.

Kan ha global timer som teller ned.
   * Hvis tiden går ut = game over
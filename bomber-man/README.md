# INF112 Project – *Bomberman Clone*

* Team: *Alliansen* (Gruppe 6): *Jostein Sæle, Aida Skarvatun, Anders Sortun, Guang Qing Zhang, Inana Baker, Nora Soraa*
* Lenke til Trello: https://trello.com/b/TOtWGW70/bomberman

# Om spillet
Bomberman er fanget i en labyrint av oljetønner som stenger veien for ham.
Heldigvis har han et uendelig reservoir av bomber, som han kan bruke for å sprenge seg ut. Men han må passe seg for å ikke bli sprengt av sine egne bomber! Når han har lagt fra seg en bombe, har han et par sekunder til å komme seg unna.
Det er også spøkelser i nærheten som han må passe seg for, og som gir ham poeng hvis
han klarer å sprenge dem. Spreng alle spøkelsene for å komme til neste level, før tiden går ut!

# Kontroller
* Start spillet med ENTER.
* Styr BomberMan med piltastene. 
* Legg ut bomber med SPACE
* Pause spillet med ENTER
* Når spillet er over/vunnet, gå til neste level med ENTER

# Kjente feil
* Ingen
* (Fra forrige gang har vi fikset at bomber og eksplosjoner ikke stoppet når spillet ble satt på pause)

# Maven Setup
This project comes with a working Maven `pom.xml` file. You should be able to import it into Eclipse using *File → Import → Maven → Existing Maven Projects* (or *Check out Maven Projects from SCM* to do Git cloning as well). You can also build the project from the command line with `mvn clean compile` and test it with `mvn clean test`.

Pay attention to these folders:
* `src/main/java` – Java source files go here (as usual for Maven) – **IMPORTANT!!** only `.java` files, no data files / assets
* `src/main/resources` – data files go here, for example in an `assets` sub-folder – **IMPORTANT!** put data files here, or they won't get included in the jar file
* `src/test/java` – JUnit tests
* `target/classes` – compiled Java class files

	
# Kjøring
You can run the project with Maven using `mvn exec:java`. Change the main class by modifying the `main.class` setting in `pom.xml`:

```
		<main.class>inf112.skeleton.app.Main</main.class>
```

Running the program should open a window with a start screen, containing some text (header and playing instructions) and some images. 

Pressing ENTER should start the game, displaying a 2D-grid with textures on the left and some info-text on the right. The player can be moved with the arrowkeys. Moving into walls / barrels / enemies shouldn't be possible (but enemies can move into the player). Trying to move into enemies should make the player take damage. 
Enemies should move around the screen, and if they hit the player, the player should take damage.

Pressing ENTER when playing should pause the game. Pressing ENTER again should resume it.

The player can lay bombs in their current location with SPACE. The bomb should explode after a few seconds. A sound effect should play.
If the player is on a tile with an explosion on it, the player should take damage.
If an explosion hits another bomb, this should trigger this bomb to explode too.

When a barrel explodes, there's a 20% chance that a powerup will appear where the barrel was. (If you didn't get a powerup from any barrels, bad luck, try again!).
If the player grabs a powerup, it should 1) increase explosion range or 2) increase the max amount of bombs player can lay down at one time or 3) increase healthPoints (max 3).

If the player takes damage for any reason, a sound effect should play and the player should blink for a second. The player shouldn't be able to take more damage during this time. If the player gets <= 0 HP or the time runs out, the game should stop and "Game Over" should appear. In this state, pressing ENTER should restart the game.

If the player manages to kill all the enemies, the game should stop and
"You Win!" should appear. Pressing ENTER in this state should start the next level, or restart the game if there are no more levels.

You may have to compile first, with `mvn compile` – or in a single step, `mvn compile exec:java`.

# Testing
Run unit tests with `mvn test` – unit test files should have `Test` in the file name, e.g., `ExampleTest.java`. This will also generate a [JaCoCo](https://www.jacoco.org/jacoco) code coverage report, which you can find in [target/site/jacoco/index.html](target/site/jacoco/index.html).

Use `mvn verify` to run integration tests, if you have any. This will do everything up to and including `mvn package`, and then run all the tests with `IT` in the name, e.g., `ExampleIT.java`.

# Jar Files

If you run `mvn package` you get everything bundled up into a `.jar` file + a ‘fat’ Jar file where all the necessary dependencies have been added:

* `target/NAME-VERSION.jar` – your compiled project, packaged in a JAR file
* `target/NAME-VERSION-fat.jar` – your JAR file packaged with dependencies

Run Jar files with, for example, `java -jar target/NAME-VERSION-fat.jar`.


If you have test failures, and *really* need to build a jar anyway, you can skip testing with `mvn -Dmaven.test.skip=true package`.

# Git Setup
If you look at *Settings → Repository* in GitLab, you can protect branches – for example, forbid pushing to the `main` branch so everyone have to use merge requests.


# Credits
NOTE: Resources marked with 'CC0' are Creative Commons Licence 0, 
which means 'free for all uses, no credits required'.

## Imported resources
* `src/main/resources/pixel_font.ttf`- CC0
* `src/main/resources/AudioFile/Doctor_DreamChip.mp3` – CC0
* `src/main/resources/AudioFiles/NCone_Funky_Beats.mp3` – CC0
* `src/main/resources/AudioFiles/hurt.wav` – CC0
* `src/main/resources/AudioFiles/MenuSound.wav` – CC0
* `src/main/resources/AudioFiles/Powerup3.wav` – CC0
* `src/main/resources/AudioFiles/SmallExplosion3.5.wav` – CC0
* `src/main/resources/AudioFiles/Success.wav` – CC0

## Self made resources
* NOTE: We decided that all self-made resources will be licenced under Creative Commons Licence 0. 
* `src/main/resources/Images/Arrow keys.png` – Guang Qing Zhang
* `src/main/resources/Images/black_texture.png` – Jostein Sæle
* `src/main/resources/Images/Space bar.png` – Guang Qing Zhang
* `src/main/resources/Images/EnterKey.png` – Guang Qing Zhang
* `src/main/resources/sprites/spriteSheet.png` – Jostein Sæle, Inana Baker
* All txt-files with level info are made by the group.

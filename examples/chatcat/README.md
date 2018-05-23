# testenvironment

# Installationsanleitung:

### Linux
Diese Anleitung ist für Linux-Systeme (konkret: Ubuntu 16.04) geschrieben.

### SSH-Key
Es ist ratsam (und für diese Anleitung notwendig), unter **github**
einen **SSH-Key** einzurichten. Dazu geht ihr rechts oben auf euer Profil, dann
`Settings`, `SSH & GPG Keys`. Dort steht dann, wie es weiter geht. Den Punkt
über `ssh-agent` kann weggelassen werden.

Eventuell muss man nachher einen Neustart machen, wenn das clonen von Projekten
nicht funktioniert.

### Installationsort:
- Ich gehe davon aus, dass man alles in einem gesonderten Ordner speichert.
  ```
  mkdir <gewünschter/ordner>
  cd <gewünschter/ordner>
  ```

- Durch das Ausführen von `mvn install` in den jeweiligen Projektordnern wird das Projekt in einem lokalen Repository auf dem Rechner installiert. Dieses ist im Ordner `~/.m2`

## Installationsskript

**Im Wurzelverzeichnis dieses Projekts gibt es ein Shell-Skript namens
  `autoInstallAndUpdate.sh`, welches das ganze Projekt inkl. Dependenzen
  selbstständig installiert. Für DFKIler gibt es das Skript
  `DFKIautoInstallAndUpdate.sh`, das genau das Gleiche tut, allerdings die
  Projekte aus dem DFKI-internen Gitlab zieht.**

Voraussetzung ist das Einrichten eines SSH-Keys (s.o.).
Außerdem sollte man sich über die Ordnerstruktur im klaren sein (s.o.).
Für DFKIler: Im Gitlab muss auch ein SSH-Key hinterlegt sein.

Dann muss man das Skript mit
```
sh autoInstallAndUpdate.sh
```
oder als DFKIler mit
```
sh DFKIautoInstallAndUpdate.sh
```
starten.

**Wenn man das Skript nochmal ausführt, dann updatet es selbstständig alle Projekte und installiert sie ggfs.**

## Schritt für Schritt:

### Laden und installieren der DFKI-Projekte:

- **hfc-database** herunterladen und installieren:
  ```
  git clone git@github.com:yoshegg/hfc-database.git
  cd hfc-database
  mvn install
  cd ..
  ```
  *das Installieren kann etwas Zeit in Anspruch nehmen.*

- **rudimant** herunterladen und installieren:
  ```
  git clone git@github.com:yoshegg/rudimant.git
  cd rudimant
  mvn install
  cd ..
  ```

- **dipal** herunterladen und **nicht** installieren (Es fehlen Dependenzen, die allerdings auch nicht nötig sind für das testenvironment):
  ```
  git clone git@github.com:yoshegg/dipal.git
  ```

- **dialoguemanager** herunterladen und **nicht** installieren (Es fehlen Dependenzen, die allerdings auch nicht nötig sind für das testenvironment):
  ```
  git clone git@github.com:yoshegg/dialoguemanager.git
  ```

- **cplan** wird mit Sicherheit auch mal benötigt werden:
  ```
  git clone git@github.com:bkiefer/cplan.git
  cd cplan
  mvn install
  cd ..
  ```

### vad (*voice activity detection*) inkl. Dependenzen herunterladen und installieren:
- **java-flac-encoder** herunterladen und installieren:
  ```
  git clone https://github.com/bkiefer/java-flac-encoder.git
  cd java-flac-encoder
  mvn install
  cd ..
  ```

- **mrcp4j** herunterladen und installieren:
  ```
  git clone git@github.com:yoshegg/mrcp4j.git
  cd mrcp4j
  mvn install
  cd ..
  ```

- **tarot-nrec10** herunterladen und installieren:
  ```
  git clone git@github.com:yoshegg/tarot-nrec10.git
  cd tarot-nrec10
  mvn install
  cd ..
  ```

- **graff** herunterladen und installieren:
  ```
  git clone git@github.com:yoshegg/graff.git
  cd graff
  mvn install
  cd ..
  ```

- **vad** herunterladen, Branch wechseln und installieren:
  ```
  git clone git@github.com:yoshegg/vad.git
  cd vad
  git checkout developer
  mvn install
  cd ..
  ```

- **srgs2xml** herunterladen und installieren:
  ```
  git clone git@github.com:bkiefer/srgs2xml.git
  cd srgs2xml
  mvn install
  cd ..
  ```

### Testenvironment herunterladen, installieren und benutzen:
- **testenvironment** herunterladen:
  ```
  git clone git@github.com:yoshegg/testenvironment.git
  cd testenvironment
  ```

- Dem Projekt ist eine beispielhafte `.rudi`-Datei beigefügt, die erst einmal kompiliert werden muss. Dafür kommt `rudimant` zum Einsatz.
  - Zuerst muss allerdings noch `uncrustify` installiert werden:
    ```
    sudo apt install uncrustify
    ```
  - Um die `.rudi`-Dateien in `src/main/rudi` zu kompilieren, folgendes Shell-Skript ausführen:
    ```
    ./compile
    ```

- Jetzt ist es möglich, das `testenvironment` zu installieren:
  ```
  mvn install
  ```

- Nun könnt ihr das Programm starten und selbst rumprobieren.
    - Ihr könnt das Programm in Netbeans importieren (oder Eclipse) und es dann ausführen.
    - Alternativ geht das über die Kommandozeile:
      ```
      ./openGui
      ```

## Zusätzliches

- Man sollte natürlich die jeweiligen Projekte seiner Gruppe nicht vergessen:
    - `git clone git@github.com:yoshegg/socrates.git`
    - `git clone git@github.com:yoshegg/reco.git`


## Beispielbefehle für hfc-database
- das Suchfenster startet man zum Beispiel mit folgendem Befehl:
  ```
  ./startServer.sh -i src/test/resources/ontos/pal.ini
  ```

- alle Elemente, deren Prädikat `<rdf:type>` ist, d.h. die Typen/Klassen von jedem Element in der Ontologie werden ausgegeben:
  ```
  select ?s ?o where ?s <rdf:type> ?o
  ```

- alle Elemente, deren Typ (`<rdf:type>`) `<owl:Class>` ist:
  ```
  select ?s where ?s <rdf:type> <owl:Class>
  ```

- alle Elemente, deren Typ (`<rdf:type>`) `<owl:Class>` ist und wo im Subjekt das Wort `Child` vorkommt:
  ```
  select ?s where ?s <rdf:type> <owl:Class> filter SContains ?s "Child"
  ```
  *`Scontains` und ähnliche Befehle finden sich im Projekt `hfc`, Paket `de.dfki.lt.hfc.operators`*

- `distinct` ist quasi das bekannte UNIX `uniq`;
  das `&`sorgt dafür, dass das erste Resultat weiter verfeinert wird:
  ```
  select distinct ?s where <dom:Child> <rdfs:subClassOf> ?o & ?s <rdfs:domain> ?o
  ```

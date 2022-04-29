# ChatCat Example (German translation below)

Installation instructions:

## Linux
These instructions are for Linux-Systems (concretely: Ubuntu 20.04 or higher).

The biggest part of the installation possibly has already happened: the installation of VOnDA, i.e. of it's compiler and runtime jar (see in the root directory of this repository)

The example can now be build as follows:

```
# generate .java files from the .rudi source files
./compile
# compile the .java files into an executable .jar
mvn clean install
# start the mini-GUI
./openGui
# type e.g. "Hello" into the text input field or simply wait
```

# ChatCat Beispiel

Installationsanleitung:

## Linux
Diese Anleitung ist für Linux-Systeme (konkret: Ubuntu 20.04 oder
höher) geschrieben.

Der größte Teil der Installation ist schon passiert: die Installation von VOnDA, d.h. des Compilers und runtime-jar

Das Beispiel kann jetzt folgendermaßen gebaut und gestartet werden

```
# generiere die .java aus den .rudi Dateien
./compile
# uebersetze die .java Dateien zu einer ausführbaren .jar
mvn clean install
# Mini-GUI starten
./openGui
# z.B. "Hello" in das Textfeld eintippen oder einfach warten
```
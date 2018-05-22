#!/bin/bash
MAVEN_REPO="$HOME/.m2/repository"

GRAFF_VERSION=0.4-SNAPSHOT
HFC_VERSION=1.2.0-SNAPSHOT
HFC_DB_VERSION=1.3-SNAPSHOT
PALTECS_UTILS_VERSION=2.0-SNAPSHOT

# ARGS: groupId artefact version
function mvi() {
    jar_path="$MAVEN_REPO/`echo $1 | sed 's|\.|/|g'`/$2/$3/$2-$3.jar"

    mvn install:install-file -Dfile="$jar_path" -DgroupId="$1" \
        -DartifactId="$2" -Dversion="$3" -Dpackaging=jar \
        -DlocalRepositoryPath=repo
}

mkdir repo

mvi de.dfki.mlt graff "$GRAFF_VERSION"

mvi de.dfki.lt.hfc hfc "$HFC_VERSION"

mvi de.dfki.lt.hfc hfc-db "$HFC_DB_VERSION"

mvi pal palTECS-utils "$PALTECS_UTILS_VERSION"

TECS_VERSION=2.0.6
mvi de.dfki.tecs libtecs "$TECS_VERSION"

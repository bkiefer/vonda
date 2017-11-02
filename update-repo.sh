#!/bin/bash
MAVEN_REPO="$HOME/.m2/repository"

GRAFF_VERSION=0.4-SNAPSHOT
HFC_VERSION=1.2.0-SNAPSHOT
HFC_DB_VERSION=1.3-SNAPSHOT
SRGS2XML_VERSION=0.9
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

mvi de.dfki.mlt srgs-parser "$SRGS2XML_VERSION"

mvi de.dfki.mlt srgs-parser "$SRGS2XML_VERSION"

mvi pal palTECS-utils "$PALTECS_UTILS_VERSION"

# the following stuff is in http://www.forschungssoftware.de/nexus/content/groups/public/
# but it's not reliable, so we hope it's in the system wide repo from where we
# put it into our local repo
CPLAN_CCG_VERSION=1.1-SNAPSHOT
mvi de.dfki.mlt cplan-ccg "$CPLAN_CCG_VERSION"

CPLAN_CORE_VERSION=1.1-SNAPSHOT
mvi de.dfki.mlt cplan-core "$CPLAN_CORE_VERSION"

DATAVIZ_VERSION=0.1-SNAPSHOT
mvi de.dfki.mlt dataviz "$DATAVIZ_VERSION"

TECS_VERSION=2.0.6
mvi de.dfki.tecs libtecs "$TECS_VERSION"

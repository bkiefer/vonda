#!/bin/bash
MAVEN_REPO="$HOME/.m2/repository"
LOCAL_REPO=repo
scriptdir=`dirname $0`
cd "$scriptdir"

# ARGS: groupId artefact version
function mvi() {
    if test -z "$what" || echo " $what " | grep -q " $2 "; then
        jar_path="$MAVEN_REPO/`echo $1 | sed 's|\.|/|g'`/$2/$3/$2-$3.jar"
        pom_path="${jar_path%%jar}pom"

        mvn install:install-file -Dfile="$jar_path" -DgroupId="$1" \
            -DartifactId="$2" -Dversion="$3" -Dpackaging=jar \
            -DlocalRepositoryPath="$LOCAL_REPO" -DpomFile="$pom_path"
    fi
}

if test "$1" = "-u" ; then
    shift
    foo=$LOCAL_REPO
    LOCAL_REPO="$MAVEN_REPO"
    MAVEN_REPO="$foo"
fi
what="$*"

mkdir $LOCAL_REPO 2>/dev/null

mvi de.dfki.mlt graff 0.4-SNAPSHOT

mvi de.dfki.lt.hfc hfc 1.2.3-SNAPSHOT

mvi de.dfki.lt.hfc hfc-db 1.3-SNAPSHOT

mvi pal palTECS-utils 2.0-SNAPSHOT

mvi de.dfki.tecs libtecs 2.0.6

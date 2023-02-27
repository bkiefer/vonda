#!/bin/bash
MAVEN_REPO="$HOME/.m2/repository"
LOCAL_REPO=repo
scriptdir=`dirname $0`
cd "$scriptdir"

usage() {
    echo "Usage $0 [-u]"
    echo "Move artefacts from the local .m2/repository to the local repo directory"
    echo "or vice versa"
    echo "if the -u argument is given, $LOCAL_REPO --> $MAVEN_REPO"
    echo "otherwise the other way round"
}

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

if test -n "$1"; then
    if test "$1" = "-u" ; then
        shift
        foo=$LOCAL_REPO
        LOCAL_REPO="$MAVEN_REPO"
        MAVEN_REPO="$foo"
    else
        usage
        exit 0
    fi
fi
what="$*"

mkdir $LOCAL_REPO 2>/dev/null

mvi de.dfki.lt.hfc hfc 1.4.3

#mvi de.dfki.lt.hfc hfc-thrift 2.0-SNAPSHOT

#!/bin/bash
MAVEN_REPO="$(cygpath -aw D:/M2_REPO)"

HFC_VERSION=1.4.2

mkdir repo

mvn install:install-file -Dfile=$(cygpath -w $MAVEN_REPO/de/dfki/lt/hfc/hfc/$HFC_VERSION/hfc-$HFC_VERSION.jar) -DgroupId=de.dfki.lt.hfc -DartifactId=hfc -Dversion=$HFC_VERSION -Dpackaging=jar -DlocalRepositoryPath=repo

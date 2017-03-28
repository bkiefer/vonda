#!/bin/bash
MAVEN_REPO="$(cygpath -aw D:/M2_REPO)"

GRAFF_VERSION=0.4-SNAPSHOT
HFC_VERSION=1.2.0-SNAPSHOT
HFC_DB_VERSION=1.2-SNAPSHOT
SRGS2XML_VERSION=0.9

mkdir repo

mvn install:install-file -Dfile=$(cygpath -w $MAVEN_REPO/de/dfki/mlt/graff/$GRAFF_VERSION/graff-$GRAFF_VERSION.jar) -DgroupId=de.dfki.mlt -DartifactId=graff -Dversion=$GRAFF_VERSION -Dpackaging=jar -DlocalRepositoryPath=repo

mvn install:install-file -Dfile=$(cygpath -w $MAVEN_REPO/de/dfki/lt/hfc/hfc/$HFC_VERSION/hfc-$HFC_VERSION.jar) -DgroupId=de.dfki.lt.hfc -DartifactId=hfc -Dversion=$HFC_VERSION -Dpackaging=jar -DlocalRepositoryPath=repo

mvn install:install-file -Dfile=$(cygpath -w $MAVEN_REPO/de/dfki/lt/hfc/hfc-db/$HFC_DB_VERSION/hfc-db-$HFC_DB_VERSION.jar) -DgroupId=de.dfki.lt.hfc -DartifactId=hfc-db -Dversion=$HFC_DB_VERSION -Dpackaging=jar -DlocalRepositoryPath=repo

mvn install:install-file -Dfile=$(cygpath -w $MAVEN_REPO/de/dfki/mlt/srgs-parser/$SRGS2XML_VERSION/srgs-parser-$SRGS2XML_VERSION.jar) -DgroupId=de.dfki.mlt -DartifactId=srgs-parser -Dversion=$SRGS2XML_VERSION -Dpackaging=jar -DlocalRepositoryPath=repo

# the following stuff is in http://www.forschungssoftware.de/nexus/content/groups/public/
# but it's not reliable, so we hope it's in the system wide repo from where we
# put it into our local repo
CPLAN_CCG_VERSION=1.1-SNAPSHOT
mvn install:install-file -Dfile=$(cygpath -w $MAVEN_REPO/de/dfki/mlt/cplan-ccg/$CPLAN_CCG_VERSION/cplan-ccg-$CPLAN_CCG_VERSION.jar) -DgroupId=de.dfki.mlt -DartifactId=cplan-ccg -Dversion=$CPLAN_CCG_VERSION -Dpackaging=jar -DlocalRepositoryPath=repo

CPLAN_CORE_VERSION=1.1-SNAPSHOT
mvn install:install-file -Dfile=$(cygpath -w $MAVEN_REPO/de/dfki/mlt/cplan-core/$CPLAN_CORE_VERSION/cplan-core-$CPLAN_CORE_VERSION.jar) -DgroupId=de.dfki.mlt -DartifactId=cplan-core -Dversion=$CPLAN_CORE_VERSION -Dpackaging=jar -DlocalRepositoryPath=repo

DATAVIZ_VERSION=0.1-SNAPSHOT
mvn install:install-file -Dfile=$(cygpath -w $MAVEN_REPO/de/dfki/mlt/dataviz/$DATAVIZ_VERSION/dataviz-$DATAVIZ_VERSION.jar) -DgroupId=de.dfki.mlt -DartifactId=dataviz -Dversion=$DATAVIZ_VERSION -Dpackaging=jar -DlocalRepositoryPath=repo

TECS_VERSION=2.0.1
mvn install:install-file -Dfile=$(cygpath -w $MAVEN_REPO/de/dfki/tecs/libtecs/$TECS_VERSION/libtecs-$TECS_VERSION.jar) -DgroupId=de.dfki.tecs -DartifactId=libtecs -Dversion=$TECS_VERSION -Dpackaging=jar -DlocalRepositoryPath=repo

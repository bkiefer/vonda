#!/bin/sh
here=`pwd`
scriptdir=`dirname $0`
cd "$scriptdir"
. ./dependencies.sh
for cmd in $prereqs; do
    if test -z "`type -all $cmd 2>/dev/null`" ; then
        toinstall="$toinstall $cmd"
    fi
done
if test -n "$toinstall"; then
    echo "Install ${toinstall} first"
    exit 1
fi
# Install the modules in the repo/ directory into your local .m2/repository
#./update-repo.sh -u
mkdir locallibs
cd locallibs
here=`pwd`
# Clone the given modules into the locallibs directory and put them into your
# local .m2/repository
for d in $githubdeps; do
    name=${d%%~*}
    ver=${d##*~}
    if test -d $name; then
        cd $name
        git pull
    else
        git clone https://github.com/bkiefer/$name.git
        cd $name
    fi
    if test \! "$name" = "$ver"; then
        git checkout "$ver"
    fi
    if test -f install_locallibs.sh; then
        ./install_locallibs.sh
    fi
    mvn install
    cd "$here"
done
cd ..

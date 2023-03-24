#!/bin/sh
prereq="mvn"
for cmd in $prereq; do
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
for d in graff_0.7.3 hfc_1.5.0 openccg dataviz j2emacs cplan_1.2.4 srgs2xml_1.3.3; do
    name=${d%%_*}
    ver=${d##*_}
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
    mvn install
    cd "$here"
done
cd ..

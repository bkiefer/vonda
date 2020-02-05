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
mkdir locallibs
cd locallibs
# Clone the given modules into the locallibs directory and put them into your
# local .m2/repository
for d in graff openccg dataviz j2emacs cplan srgs2xml; do
  git clone https://github.com/bkiefer/$d.git
  cd $d
  mvn install
  cd ..
done
# Install the modules in the repo/ directory into your local .m2/repository
./update-repo.sh -u
cd ..

#!/bin/sh
prereq="mvn bison jflex"
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
for d in openccg dataviz j2emacs cplan srgs2xml; do
  git clone https://github.com/bkiefer/$d.git
  cd $d
  mvn install
  cd ..
done
cd ..

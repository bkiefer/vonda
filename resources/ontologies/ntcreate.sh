#!/bin/bash
#set -x
for ext in owl rdfs rdf ; do
    for f in `find -L -name \*.$ext`; do
        ntfile="${f%%$ext}nt"
        if test "$f" -nt "$ntfile"; then
            rapper "$f" | sort | uniq > $ntfile
        fi
    done
done

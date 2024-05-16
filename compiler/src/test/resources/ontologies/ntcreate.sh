#!/bin/bash
for owl in `find -name *\.owl`; do
    ntfile="${owl%%owl}nt"
    if test "$owl" -nt "$ntfile"; then
        rapper "$owl" | sort | uniq > $ntfile
    fi
done

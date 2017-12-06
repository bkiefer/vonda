#!/bin/bash
# tt = Transaction Time
# adds <logic:true> to the front and "0"^^<xsd:long> to the back of a tuple;
# assumes that the input file is in N-Triple/Tuple form and ends in '.';
# call with:  tt.sh <infile> <outfile>
sed 's/\(.*\)\.$/<logic:true> \1"0"^^<xsd:long> ./' $1 > $2
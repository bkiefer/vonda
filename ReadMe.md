# VOnDA (Versatile Ontology based Dialogue management Architecture)

## Introduction

VOnDA is a framework for the implementation of dialogue management
functionality in dialogue systems for virtual agents. Although
domain-independent, VOnDA is tailored towards dialogue systems with a focus on
social communication, which implies the need of a long-term memory and high
user adaptivity.

VOnDA's theoretical underpinning is the Information State-Update approach,
which bears great similarity to the Belief-Desire-Intent approach to artificial
agents. The Information State is realized using the special RDF data storage
and reasoning component HFC that is designed to handle time-varying data.

## Installation

Prerequisites for installing VOnDA are:

- OpenJDK 8
- maven build tool [https://maven.apache.org/]()
- thrift 0.9.3 communication library [http://archive.apache.org/dist/thrift/0.9.3/]()
- get openccg, dataviz, j2emacs, cplanner, srgs2xml libraries and finally vonda from github:
  ```
  for d in openccg dataviz j2emacs cplan srgs2xml vonda; do
    git clone https://github.com/bkiefer/$d.git
    cd $d
    mvn install
    cd ..
  done
  ```

## Documentation

Detailed user documentation can be found in the doc folder of the project.

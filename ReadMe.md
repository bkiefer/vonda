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

The following installation instructions should work on Ubuntu 16.04

Prerequisites for installing VOnDA are:
- OpenJDK 8, maven build tool, git, and uncrustify (optional)
  ```
  sudo apt install openjdk-8-jdk maven git uncrustify
  ```

- thrift 0.9.3 communication library
  See https://thrift.apache.org/docs/install/debian first
  Check on the thrift website how to install. Make sure the C++ and Java
  bindings are provided after installation:

  ```
  wget http://archive.apache.org/dist/thrift/0.9.3/thrift-0.9.3.tar.gz
  tar xf thrift-0.9.3.tar.gz
  cd thrift-0.9.3
  ./configure --with-java
  cd ..
  ```

- get VOnDA, install some dependencies from github, compile them, and finally
  the framework itself
  ```
  git clone https://github.com/bkiefer/vonda.git
  cd vonda
  # install dependencies
  ./install_locallibs.sh
  mvn install
  ```


There is also a visual debugger/GUI for VOnDA: https://github.com/yoshegg/rudibugger

## Documentation

Detailed user documentation can be found in the doc folder of the project.

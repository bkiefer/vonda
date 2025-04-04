# VOnDA (Versatile Ontology based Dialogue management Architecture)

[![mvn build](https://github.com/bkiefer/vonda/actions/workflows/maven.yml/badge.svg)](https://github.com/bkiefer/vonda/actions/workflows/maven.yml)

## Introduction

VOnDA is a framework for the implementation of reactive dialogue management
functionality in dialogue systems for virtual agents. Although
domain-independent, VOnDA is tailored towards dialogue systems with a focus on
social communication, which implies the need of a long-term memory and high
user adaptivity.

VOnDA heavily draws inspiration from the Information State-Update approach,
which bears great similarity to the Belief-Desire-Intent approach to artificial
agents. The Information State is realized using the special RDF data storage
and reasoning component HFC that is designed to handle time-varying data.

Rather than being a complete dialogue framework, VOnDA is a hybrid
rule-based/statistical implementation layer that can implement / simulate
various approaches, from state-based to probabilistic reasoning.

## Installation

The following installation instructions are tested on Ubuntu 22.04

Prerequisites for installing VOnDA are:
- OpenJDK 11, maven build tool, and git
  ```
  sudo apt install openjdk-11-jdk maven git
  ```

- get VOnDA and compile the framework
  ```
  git clone https://github.com/bkiefer/vonda.git
  git submodule update --init --recursive
  git submodule update --recursive --remote
  cd vonda
  mvn install
  ```

There is also a [visual debugger/GUI](https://github.com/yoshegg/rudibugger) for VOnDA.

The [vonda-nlp](https://github.com/bkiefer/vonda-nlp) github repository contains an example use of rasa NLP for a more sophisticated NLU. More NL componentes are planned to be added there.

## Documentation

Detailed user documentation can be found in the doc folder of the project. It
contains a step-by-step guide how to build your first small conversational
agent with VOnDA. the [vonda-chatcat](https://github.com/bkiefer/vonda-chatcat)
project contains the agent described in this guide, if you don't want to create
it by hand

## Version information

This is version 3.0 of VOnDA. If you have used an older version,
especially 2.x, please consult the documentation about the breaking
changes and the automatic converter, which is provided in this git
repository on a special branch.

#!/bin/sh
git submodule update --init --recursive --remote
git pull --recurse-submodules

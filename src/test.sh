#!/bin/bash
make
java Main inputs/BinaryTree.java 
clang-4.0 -o out1 out.ll

#!/bin/bash

mkdir build
mkdir release
mkdir build/resources
javac -d build src/com/mercadolibre/Main.java
cp src/resources/mock_template build/resources/mock_template
cd build
jar cmf ../src/META-INF/MANIFEST.MF ../release/syi-mock.jar *
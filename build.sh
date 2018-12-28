#!/bin/bash
mkdir -p out
mkdir -p lib
cd lib
wget https://projects.mklpiening.de/maltejson/downloads/MalteJson_1.2.2.jar
cd ../src
javac -source 1.7 -target 1.7 -d ../out/ -cp "../lib/craftbukkit-1.12.2.jar:../lib/MalteJson_1.2.2.jar" de/lukasherz/snowballFight/SnowballFight.java de/lukasherz/snowballFight/JsonFile.java de/lukasherz/snowballFight/events/SnowballMovesListener.java
cd ../out/
jar xf ../lib/MalteJson_1.2.2.jar
jar cf SnowballFight.jar de/ ../plugin.yml
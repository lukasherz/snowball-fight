# SnowballFight
Plugin for newer Minecraft versions (1.12.2+) to add snowball knockback und an optional headshot feature.

## Download
If you don't want to build the project by yourself, you can download it as a jar file [here](http://code.luguhe.net/projects/snowball-fight/downloads/ "SnowballFight Download").

## Dependencies
This project requires [Malte Json](https://git.mklpiening.de/mklpiening/MalteJson "go to project site") and [CraftBukkit 1.12.2](https://www.spigotmc.org/wiki/buildtools/#1-12-2 "go to spigot wiki").

## Build
After installing [Oracle JDK](http://www.oracle.com/technetwork/java/javase/downloads/index.html) or [OpenJDK](http://openjdk.java.net/), checkout the source:

```bash
git https://github.com/luguhe/snowball-fight.git
cd snowball-fight
```

Add the CraftBukkit 1.12.2 (see [here](https://www.spigotmc.org/wiki/buildtools/#1-12-2 "go to spigot wiki") how to build) named `craftbukkit_1.12.2.jar` in the directory `lib`.
```bash
mkdir lib
mv /path/to/craftbukkit_1.12.2.jar lib/craftbukkit_1.12.2.jar 
```

Now build the project:
```bash
./build.sh
```

Afterwads you will find the `SnowballFight.jar` under `out`.

## Copyright
SnowballFight is open-source software released under the MIT license. Please see the `LICENSE` file for more details.
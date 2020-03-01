#!/bin/bash
mvn deploy -Dregistry=https://maven.pkg.github.com/gtasa-savegame-editor -Dtoken="${GH_TOKEN}"

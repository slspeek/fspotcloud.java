#!/bin/bash
java -Dpause=30000 -Dendpoint=${application.id}.appspot.com -Dbot.secret=$bot.secret.userinput -Ddb=$photos.db.userinput  -jar $INSTALL_PATH/peer-jar-with-dependencies.jar

VERSION=$1
BOT_SECRET=$2
java -cp "../peer/build/libs/peer-$VERSION.jar" \
      -Ddb="../peer/src/test/resources/photos.db" \
      -Dendpoint="localhost:8080/j2ee-war" \
      -Dbot.secret="${BOT_SECRET}" \
      -Dpause=2 \
      -Dphoto.dir.original="file:///home/steven/Photos" \
      -Dphoto.dir.override="file://$(pwd)/../peer/src/test/resources/Photos" \
      com.googlecode.fspotcloud.peer.Main   

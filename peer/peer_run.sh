VERSION=$1
BOT_SECRET=$2
END_POINT=$3
java -cp "../peer/build/libs/peer-$VERSION.jar" \
      -Ddb="../peer/src/test/resources/photos.db" \
      -Dendpoint=${END_POINT} \
      -Dbot.secret="${BOT_SECRET}" \
      -Dpause=2 \
      -Dphoto.dir.original="file:///home/steven/Photos" \
      -Dphoto.dir.override="file://$(pwd)/../peer/src/test/resources/Photos" \
      com.googlecode.fspotcloud.peer.Main   

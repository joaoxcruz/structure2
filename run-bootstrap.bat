REM set the class path,
REM assumes the build was executed with maven copy-dependencies
SET BASE_CP=shodrone.app.bootstrap\target\shodrone.app.bootstrap-1.4.0-SNAPSHOT.jar;shodrone.app.bootstrap\target\dependency\*;

REM call the java VM, e.g, 
java -cp %BASE_CP% bootstrap.app.lapr4.shodrone.shodroneBootstrap

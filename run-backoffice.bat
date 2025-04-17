REM set the class path,
REM assumes the build was executed with maven copy-dependencies
SET BASE_CP=shodrone.app.backoffice.console\target\shodrone.app.backoffice.console-1.4.0-SNAPSHOT.jar;shodrone.app.backoffice.console\target\dependency\*;

REM call the java VM, e.g, 
java -cp %BASE_CP% console.backoffice.app.lapr4.shodrone.shodroneBackoffice

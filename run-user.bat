REM set the class path,
REM assumes the build was executed with maven copy-dependencies
SET BASE_CP=shodrone.app.user.console\target\shodrone.app.user.console-1.4.0-SNAPSHOT.jar;shodrone.app.user.console\target\dependency\*;

REM call the java VM, e.g, 
java -cp %BASE_CP% eapli.shodrone.app.user.console.shodroneUserApp

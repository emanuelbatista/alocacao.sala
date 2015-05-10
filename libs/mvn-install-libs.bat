ECHO OFF
rem Esse script instala a dependência do SwingR no repositório Maven
rem Solução temporária enquanto não temos o código-fonte do módulo
mvn install:install-file -Dfile="swingr.jar" -DgroupId=br.com.recursive.swingr -DartifactId=swingr -Dversion=1.0 -Dpackaging=jar

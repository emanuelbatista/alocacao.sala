# Esse script instala a dependência do SwingR no repositório Maven
# Solução temporária enquanto não temos o código-fonte do módulo
script_path=$(readlink -f "$0")
path=$(dirname $script_path)
mvn install:install-file -Dfile="$path/swingr.jar" -DgroupId=br.com.recursive.swingr -DartifactId=swingr -Dversion=1.0 -Dpackaging=jar

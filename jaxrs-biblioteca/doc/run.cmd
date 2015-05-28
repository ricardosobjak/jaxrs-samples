#
# An example showing how to use the commandline tools included in this
# package.
#

set WADL_HOME=D:\programas\rest\wadl-dist-1.1.3
set PATH=%PATH%;%WADL_HOME%\bin

mkdir .\gen-wadl

wadl2java.bat -o ./gen-wadl -s jersey1x -p br.edu.utfpr.jersey.resources http://localhost:8080/RestLivro/rest/application.wadl

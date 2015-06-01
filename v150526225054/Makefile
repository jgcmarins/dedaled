
##
# 
# Tue May 26 22:54:08 BRT 2015
# author: Joao Gustavo Cabral de Marins
# e-mail: jgcmarins@gmail.com
# 
##

all: compile package
cleancompile:
	rm -rf build
	mkdir build
compile: cleancompile
	javac -cp build src/br/usp/icmc/ssc0103/dedaled/**/**/*.java src/br/usp/icmc/ssc0103/dedaled/**/*.java -d build
cleanpackage:
	rm -rf package
	mkdir package
package: cleanpackage
	echo "Main-Class: br.usp.icmc.ssc0103.dedaled.main.Main" > build/manifest.txt
	jar cvfm package/Dedaled.jar build/manifest.txt -C build .
run:
	java -jar package/Dedaled.jar
cleanfiles:
	rm -rf files
run201611:
	java -jar package/Dedaled.jar 2016 1 1
run15:
	java -jar package/Dedaled.jar 2016 1 15
run60:
	java -jar package/Dedaled.jar 2016 3 2

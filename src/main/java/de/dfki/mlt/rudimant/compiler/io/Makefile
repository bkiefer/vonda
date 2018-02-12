ROOT       = $(HOME)/src/external/jflex
#JFLEX      = $(ROOT)/jflex/bin/jflex
JFLEX	   = jflex
JAVA       = java
JAVAC      = javac
BISON	   = bison


# --------------------------------------------------

%.java : %.y
	$(BISON) -Wall -rall --language java $<

all: VondaGrammar.java VondaLexer.java UnicodeEscapes.java

%.java: %.flex
	$(JFLEX) $<

clean:
	rm -f *.class
	rm -f *~
	rm -f *.java
	rm -f VondaGrammar.output

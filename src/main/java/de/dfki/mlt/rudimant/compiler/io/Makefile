ROOT       = $(HOME)/src/external/jflex
#JFLEX      = $(ROOT)/jflex/bin/jflex
JFLEX	   = jflex
JAVA       = java
JAVAC      = javac
BISON	   = bison


# --------------------------------------------------

%.java : %.y
	$(BISON) -Wall -rall --language java --debug $<
#	$(BISON) -Wall -rall --language java -Wcounterexamples --debug $< 2>&1 | tee counterexamples.out

all: VondaGrammar.java VondaLexer.java UnicodeEscapes.java

%.java: %.flex
	$(JFLEX) $<

clean:
	rm -f *.class
	rm -f *~
	rm -f VondaGrammar.java VondaLexer.java UnicodeEscapes.java
	rm -f VondaGrammar.output

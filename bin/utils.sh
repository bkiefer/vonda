here=`pwd`
if test -f "logback.xml"; then
    silent="$silent -Dlogback.configurationFile=logback.xml "
else
    silent="-Dorg.slf4j.simpleLogger.defaultLogLevel=warn "
fi

java --version | grep -q ' 1[1-6]\.' && \
    silent="${silent}--illegal-access=permit "

silent="${silent}--add-modules=ALL-SYSTEM \
--add-exports=jdk.compiler/com.sun.tools.javac.api=ALL-UNNAMED \
--add-exports=jdk.compiler/com.sun.tools.javac.code=ALL-UNNAMED \
--add-exports=jdk.compiler/com.sun.tools.javac.file=ALL-UNNAMED \
--add-exports=jdk.compiler/com.sun.tools.javac.parser=ALL-UNNAMED \
--add-exports=jdk.compiler/com.sun.tools.javac.tree=ALL-UNNAMED \
--add-exports=jdk.compiler/com.sun.tools.javac.util=ALL-UNNAMED "

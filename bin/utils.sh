script_dir=`dirname $0`
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

if realpath . > /dev/null 2>&1 ; then
    :
else
    # does not exist on Mac OSX
    realpath() {
        target=$1

        cd `dirname $target`
        target=`basename $target`

        # Iterate down a (possible) chain of symlinks
        while [ -L "$target" ]
        do
            target=`readlink $target`
            cd `dirname $target`
            target=`basename $target`
        done

        # Compute the canonicalized name by finding the physical path
        # for the directory we're in and appending the target file.
        phys_dir=`pwd -P`
        echo "$phys_dir/$target"
    }
fi

getscriptdir() {
    script_dir=`realpath "$0"`
    dirname "$script_dir"
}

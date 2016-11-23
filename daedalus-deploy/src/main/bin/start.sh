#!/bin/sh
bin_path=`pwd`
cd ${bin_path}/..
base_path=`pwd`

JAVA_HOME="/Library/Java/JavaVirtualMachines/jdk1.8.0_101.jdk/Contents/Home"
JAVA_OPTS="-Duser.timezone=GMT+8 -server -Xms2048m -Xmx2048m -Djava.awt.headless=true -Djava.net.preferIPv4Stack=true -Dfile.encoding=UTF-8"
#APP_HOME=~/workplace/test/provider-1.0-SNAPSHOT/
APP_MAIN=com.bullet.sweet.daedalus.deploy.ApplicationLauncher

echo 'base_path:'${base_path}

for tradePortalJar in ${base_path}/lib/*.jar
do
   CLASSPATH="$CLASSPATH":"$tradePortalJar"
done
CLASSPATH="$base_path/conf:$CLASSPATH"

tradePortalPID=0

getTradeProtalPID(){
    javaps=`$JAVA_HOME/bin/jps -l | grep $APP_MAIN`
    if [ -n "$javaps" ]; then
        tradePortalPID=`echo $javaps | awk '{print $1}'`
    else
        tradePortalPID=0
    fi
}

startup(){
    getTradeProtalPID
    echo "================================================================================================================"
    if [ $tradePortalPID -ne 0 ]; then
        echo "$APP_MAIN already started(PID=$tradePortalPID)"
        echo "================================================================================================================"
    else
        echo -n "Starting $APP_MAIN"
        $JAVA_HOME/bin/java $JAVA_OPTS -classpath $CLASSPATH $APP_MAIN
        getTradeProtalPID
        if [ $tradePortalPID -ne 0 ]; then
            echo "(PID=$tradePortalPID)...[Success]"
           echo "================================================================================================================"
        else
            echo "[Failed]"
            echo "================================================================================================================"
        fi
    fi
}

startup

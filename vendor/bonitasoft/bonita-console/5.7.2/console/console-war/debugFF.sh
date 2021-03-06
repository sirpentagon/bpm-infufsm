#!/bin/bash
export BONITATARGETVERSION=5.6-SNAPSHOT
export CONSOLETARGETVERSION=5.6-SNAPSHOT

export WORKSPACEPATH=/opt/projets/bonita/
export PATHTOPROJECT=$WORKSPACEPATH/console/trunk
export MAVENREPOPATH=/opt/apache/repository
export BONITA_HOME="/home/anthony/bonita"

##GWT / GWTx
##gwt
export CLASSPATH="$MAVENREPOPATH/com/google/gwt/gwt-user/2.3.0/gwt-user-2.3.0.jar":"$MAVENREPOPATH/com/google/gwt/gwt-dev/2.3.0/gwt-dev-2.3.0.jar"
export CLASSPATH=$CLASSPATH:"$MAVENREPOPATH/com/google/code/gwtx/gwtx/1.5.3/gwtx-1.5.3.jar"

##BONITA Engine
export CLASSPATH=$CLASSPATH:"$MAVENREPOPATH/org/ow2/bonita/bonita-server/$BONITATARGETVERSION/bonita-server-$BONITATARGETVERSION.jar"
export CLASSPATH=$CLASSPATH:"$MAVENREPOPATH/antlr/antlr/2.7.6/antlr-2.7.6.jar"
export CLASSPATH=$CLASSPATH:"$MAVENREPOPATH/commons-codec/commons-codec/1.3/commons-codec-1.3.jar"
export CLASSPATH=$CLASSPATH:"$MAVENREPOPATH/commons-collections/commons-collections/3.1/commons-collections-3.1.jar"
export CLASSPATH=$CLASSPATH:"$MAVENREPOPATH/dom4j/dom4j/1.6.1/dom4j-1.6.1.jar"
export CLASSPATH=$CLASSPATH:"$MAVENREPOPATH/net/sf/ehcache/ehcache-core/2.2.0/ehcache-core-2.2.0.jar"
export CLASSPATH=$CLASSPATH:"$MAVENREPOPATH/org/codehaus/groovy/groovy-all/1.7.8/groovy-all-1.7.8.jar"
export CLASSPATH=$CLASSPATH:"$MAVENREPOPATH/com/h2database/h2/1.2.139/h2-1.2.139.jar"
export CLASSPATH=$CLASSPATH:"$MAVENREPOPATH/org/hibernate/hibernate-commons-annotations/3.2.0.Final/hibernate-commons-annotations-3.2.0.Final.jar"
export CLASSPATH=$CLASSPATH:"$MAVENREPOPATH/org/hibernate/hibernate-core/3.5.6-Final/hibernate-core-3.5.6-Final.jar"
export CLASSPATH=$CLASSPATH:"$MAVENREPOPATH/org/hibernate/javax/persistence/hibernate-jpa-2.0-api/1.0.0.Final/hibernate-jpa-2.0-api-1.0.0.Final.jar"
export CLASSPATH=$CLASSPATH:"$MAVENREPOPATH/org/hibernate/hibernate-search/3.2.1.Final/hibernate-search-3.2.1.Final.jar"
export CLASSPATH=$CLASSPATH:"$MAVENREPOPATH/javassist/javassist/3.8.0.GA/javassist-3.8.0.GA.jar"
export CLASSPATH=$CLASSPATH:"$MAVENREPOPATH/javax/transaction/jta/1.1/jta-1.1.jar"
export CLASSPATH=$CLASSPATH:"$MAVENREPOPATH/org/apache/lucene/lucene-analyzers/2.9.3/lucene-analyzers-2.9.3.jar"
export CLASSPATH=$CLASSPATH:"$MAVENREPOPATH/org/apache/lucene/lucene-core/2.9.3/lucene-core-2.9.3.jar"
export CLASSPATH=$CLASSPATH:"$MAVENREPOPATH/org/apache/lucene/lucene-snowball/2.9.3/lucene-snowball-2.9.3.jar"
export CLASSPATH=$CLASSPATH:"$MAVENREPOPATH/org/apache/solr/solr-core/1.4.0/solr-core-1.4.0.jar"
export CLASSPATH=$CLASSPATH:"$MAVENREPOPATH/org/apache/solr/solr-solrj/1.4.0/solr-solrj-1.4.0.jar"
export CLASSPATH=$CLASSPATH:"$MAVENREPOPATH/org/apache/chemistry/opencmis/chemistry-opencmis-client-api/0.2.0-incubating/chemistry-opencmis-client-api-0.2.0-incubating.jar"
export CLASSPATH=$CLASSPATH:"$MAVENREPOPATH/org/apache/chemistry/opencmis/chemistry-opencmis-client-bindings/0.2.0-incubating/chemistry-opencmis-client-bindings-0.2.0-incubating.jar"
export CLASSPATH=$CLASSPATH:"$MAVENREPOPATH/org/apache/chemistry/opencmis/chemistry-opencmis-client-impl/0.2.0-incubating/chemistry-opencmis-client-impl-0.2.0-incubating.jar"
export CLASSPATH=$CLASSPATH:"$MAVENREPOPATH/org/apache/chemistry/opencmis/chemistry-opencmis-commons-impl/0.2.0-incubating/chemistry-opencmis-commons-impl-0.2.0-incubating.jar"
export CLASSPATH=$CLASSPATH:"$MAVENREPOPATH/org/apache/chemistry/opencmis/chemistry-opencmis-commons-api/0.2.0-incubating/chemistry-opencmis-commons-api-0.2.0-incubating.jar"
export CLASSPATH=$CLASSPATH:"$MAVENREPOPATH/org/apache/chemistry/opencmis/chemistry-opencmis-test-util/0.2.0-incubating/chemistry-opencmis-test-util-0.2.0-incubating.jar"


##SECURITY
##security-model
export CLASSPATH=$CLASSPATH:"$PATHTOPROJECT/security/security-model/src/main/java"

##security-rpc
export CLASSPATH=$CLASSPATH:"$PATHTOPROJECT/security/security-rpc/src/main/java"

##csecurity-server
export CLASSPATH=$CLASSPATH:"$PATHTOPROJECT/security/security-server/src/main/java"

##security-view
export CLASSPATH=$CLASSPATH:"$PATHTOPROJECT/security/security-view/src/main/java"

##security-war
export CLASSPATH=$CLASSPATH:"$PATHTOPROJECT/security/security-war/src/main/java"

##FORMS
##forms-model
export CLASSPATH=$CLASSPATH:"$PATHTOPROJECT/forms/forms-model/src/main/java"

##forms-rpc
export CLASSPATH=$CLASSPATH:"$PATHTOPROJECT/forms/forms-rpc/src/main/java"

##forms-server
export CLASSPATH=$CLASSPATH:"$PATHTOPROJECT/forms/forms-server/src/main/java"

##forms-view
export CLASSPATH=$CLASSPATH:"$PATHTOPROJECT/forms/forms-view/src/main/java"
export CLASSPATH=$CLASSPATH:"$PATHTOPROJECT/forms/forms-view/src/main/resources"

##forms-application
export CLASSPATH=$CLASSPATH:"$PATHTOPROJECT/forms/forms-application/src/main/java"

##CONSOLE
##console-model
export CLASSPATH=$CLASSPATH:"$PATHTOPROJECT/console/console-model/src/main/java"

##console-rpc
export CLASSPATH=$CLASSPATH:"$PATHTOPROJECT/console/console-rpc/src/main/java"

##console-server
export CLASSPATH=$CLASSPATH:"$PATHTOPROJECT/console/console-server/src/main/java"

##console-view
export CLASSPATH=$CLASSPATH:"$PATHTOPROJECT/console/console-view/src/main/java"
export CLASSPATH=$CLASSPATH:"$PATHTOPROJECT/console/console-view/src/main/resources"

##console-war
export CLASSPATH=$CLASSPATH:"$PATHTOPROJECT/console/console-war/src/main/java"

java -Xmx512m -XX:MaxPermSize=256m -cp $CLASSPATH -Dfile.encoding=UTF-8 -DBONITA_HOME=$BONITA_HOME -Djava.util.logging.config.file=$BONITA_HOME/external/logging/logging.properties -Djava.security.auth.login.config=$BONITA_HOME/external/security/jaas-standard.cfg -Dexo.data.dir=$BONITA_HOME/external/xcmis/ext-exo-data -Dorg.exoplatform.container.standalone.config=$BONITA_HOME/external/xcmis/ext-exo-conf/exo-configuration-hsql.xml -Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,server=y,address=8000,suspend=y -Dcatalina.base="$PATHTOPROJECT/console/console-war/target/tomcat" com.google.gwt.dev.DevMode -gen "$PATHTOPROJECT/console/console-war/target/.generated" -logLevel INFO -war "$PATHTOPROJECT/console/console-war/target/console-war-$CONSOLETARGETVERSION" -port 8888 -startupUrl console/homepage?ui=user org.bonitasoft.console.devBonitaConsoleFF

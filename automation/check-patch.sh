#!/bin/bash -ex

# Clean and then create the artifacts directory:
artifacts="exported-artifacts"
rm -rf "${artifacts}"
mkdir -p "${artifacts}"

# Create a settings file that uses the our artifactory server as
# proxy for all repositories:
settings="$(pwd)/settings.xml"
cat > "${settings}" <<.
<settings>
  <mirrors>

    <mirror>
      <id>ovirt-artifactory</id>
      <url>http://artifactory.ovirt.org/artifactory/ovirt-mirror</url>
      <mirrorOf>*</mirrorOf>
    </mirror>

    <mirror>
      <id>maven-central</id>
      <url>http://repo.maven.apache.org/maven2</url>
      <mirrorOf>*</mirrorOf>
    </mirror>

  </mirrors>
</settings>
.

# Clean and then create the local repository:
repository="repository"
rm -rf "${repository}"
mkdir -p "${repository}"

# Prepare an alias for the "mvn" command including all
# the required options:
mvn="mvn -s ${settings} -Dmaven.repo.local=${repository}"

# There may be several versions of Java installed in the build
# enviroment, and we need to make sure that Java 8 is used, as
# it is required by the code generator:
export JAVA_HOME="/usr/lib/jvm/java-1.8.0"

# Build the artifacts:
${mvn} install -Dadoc.linkcss=true
for format in adoc csv html json xml
do
  mv target/generated-${format}/* "${artifacts}"
done

# Run findbugs:
${mvn} findbugs:findbugs

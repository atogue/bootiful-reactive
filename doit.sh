#!/bin/bash
## go to the local folder (usually maven repository)
## where spring-cloud-contract-stub-runner-x.x.x.jar artifactId has been downloaded
echo "Please, remember to run this script in the same folder containing scc stub artifact!"
echo ""
echo "Also remember the Java VM version 15 or higher is required."
echo ""
cd ${local.maven.repository}/org/springframework/cloud/spring-cloud-contract-stub-runner/3.0.0/
echo "inside the local maven repository (remember to define its parent folder in this script):"
echo ""
echo "run jar spring cloud contract stub artifact ..."
echo ""
## run the spring artifact with stubRunner references like api backend or client jar to be executed
java -jar -Dserver.port=8090 \
          spring-cloud-contract-stub-runner-3.0.0.jar \
          --stubrunner.workOffline=true \
          --stubrunner.stubs-mode="local" \
          --stubrunner.ids=org.craftchain:bootiful-reactive:+:8080 \

echo ""
echo "stubs execution has been done !!!"
echo ""
echo "exit and back to previous folder."
## return to previous folder
cd -

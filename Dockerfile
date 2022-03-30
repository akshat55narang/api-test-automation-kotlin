FROM maven
MAINTAINER akshatnarang

COPY . api-test-automation-kotlin/

WORKDIR api-test-automation-kotlin/

RUN ["mvn", "test"]



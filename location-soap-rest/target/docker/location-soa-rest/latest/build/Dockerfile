FROM fuse-java-openshift-rhel8:1.10-21
ENV JAVA_APP_DIR=/deployments
LABEL org.label-schema.description="Spring Boot example running a Camel route defined in XML" org.label-schema.version=1.0.0-SNAPSHOT org.label-schema.schema-version=1.0 org.label-schema.build-date=2022-03-27T23:37:21.367296 org.label-schema.name=location-soap-rest org.label-schema.vcs-ref=3f3224814619a6f554bcd744119c16d5c1472a55 org.label-schema.vcs-url=https://github.com/abdelhamidfg/location-soap-rest.git
EXPOSE 8090 8778 9779
COPY /deployments /deployments/

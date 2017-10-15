MAINTAINER ETICA SATELITES <etica.satelites@asefa.es>

EXPOSE  8090 443

ENV application "docker-service"

ADD service.sh /opt/service/
RUN chmod 0600 /opt/service/service.sh
RUN chmod +x /opt/service/service.sh

ADD build/*.jar  /opt/service/

ENTRYPOINT ["/opt/service/service.sh", "run"]

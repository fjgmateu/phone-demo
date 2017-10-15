FROM java:8

MAINTAINER FJGMATEU <frangarmateu@gmail.com>

EXPOSE  8761 443

ENV application "eureka-server"

ADD service.sh /opt/service/
RUN chmod 0600 /opt/service/service.sh
RUN chmod +x /opt/service/service.sh

ADD build/*.jar  /opt/service/

ENTRYPOINT ["/opt/service/service.sh", "run"]

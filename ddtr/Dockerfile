FROM styl3r/vanilla:latest

LABEL version="1.0"
LABEL description="VIO DDTR"


RUN ["mkdir", "-p", "/apps/home"] 
COPY . /apps/home
WORKDIR /apps/home

EXPOSE 8080
EXPOSE 5005
ENTRYPOINT ["./docker-entry.sh"]



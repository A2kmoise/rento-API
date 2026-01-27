FROM ubuntu:latest
LABEL authors="moise"

ENTRYPOINT ["top", "-b"]
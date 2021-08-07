start() {
    mvn spring-boot:run $@
}

upDB() {
    docker-compose up -d
}

downDB() {
    docker-compose down
}

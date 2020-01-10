# eventuate-local
This is project implement microservice and eventsourcing try using eventuate-local platform.

Before start the project
1. Run pre script to build package and docker images for each project

``` shellscript
    chmod +x pre-run.sh
    ./pre-run.sh
```

2. Start the service by docker-compose

 ```shellscript
    docker-compose -f docker-compose-eventuate-local.yml up --build
    docker-compose -f docker-compose-eventuate-local.yml down #To stop the service
 ```

# AdventOfCode2025

## Structure

This project includes the following modules:

| Path             | Description                              |
|------------------|------------------------------------------|
| [server](server) | A runnable Ktor server implementation    |

## Building

To build the project, use one of the following tasks:

| Task                    | Description                                                          |
|-------------------------|----------------------------------------------------------------------|
| `./gradlew build`       | Build everything                                                     |
| `./gradlew buildFatJar` | Build an executable JAR of the server with all dependencies included | |

## Running

To run the project, use one of the following tasks:

| Task                                 | Description                            |
|--------------------------------------|----------------------------------------|
| `./gradlew :server:run`              | Run the server                         |

## Docker

use docker-compose.yml to run the server in a container


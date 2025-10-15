# AdventOfCode2025

## Structure

This project includes the following modules:

| Path             | Description                              |
|------------------|------------------------------------------|
| [server](server) | A runnable Ktor server implementation    |
| [web](web)       | Front-end Kotlin scripts for the browser |

## Building

To build the project, use one of the following tasks:

| Task                                            | Description                                                          |
|-------------------------------------------------|----------------------------------------------------------------------|
| `./gradlew build`                               | Build everything                                                     |
| `./gradlew :server:buildFatJar`                 | Build an executable JAR of the server with all dependencies included |
| `./gradlew :server:buildImage`                  | Build the docker image to use with the fat JAR                       |
| `./gradlew :server:publishImageToLocalRegistry` | Publish the docker image locally                                     |
| `./gradlew -t :web:build`                       | Build WASM scripts continuously                                      |

## Running

To run the project, use one of the following tasks:

| Task                                 | Description                            |
|--------------------------------------|----------------------------------------|
| `./gradlew :server:run`              | Run the server                         |
| `./gradlew :server:runDocker`        | Run using the local docker image       |
| `./gradlew -t :web:wasmJsBrowserRun` | Run scripts in a browser, without Ktor |


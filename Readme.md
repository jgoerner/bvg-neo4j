<!-- PROJECT LOGO -->
<br />
<p align="center">
    <img src="images/logo.png" alt="Logo" width="120" height="120">
  <h3 align="center">BVG - Neo4j</h3>

  <p align="center">
    Berlin Subway pathfinding with Neo4j 
    <br>
    #WeilWirDichLieben
  </p>
</p>



<!-- TABLE OF CONTENTS -->
## Table of Contents

* [About the Project](#about-the-project)
  * [Built With](#built-with)
* [Getting Started](#getting-started)
  * [Prerequisites](#prerequisites)
  * [Installation](#installation)
* [Usage](#usage)
* [Contact](#contact)
* [Acknowledgements](#acknowledgements)



<!-- ABOUT THE PROJECT -->
## About The Project

<p align="center">
          <img src="images/about.gif">
</p>


### Built With
- 🌱 [Spring Boot](https://spring.io/projects/spring-boot)
- 🕸 [Neo4j](https://neo4j.com/)
- 🐳 [Docker](https://www.docker.com/)
- ☕️ [Coffee](https://www.buymeacoffee.com/jgoerner)
- ❤️ [Love](https://www.youtube.com/watch?v=NyoTvgPn0rU)


<!-- GETTING STARTED -->
## Getting Started

### Prerequisites

In order to run the code in this repository, you have to make sure you have 
- Docker installed
- an internet connection (used to fetch [APOC library](https://neo4j.com/developer/neo4j-apoc/))

### Installation

1. Start Docker
2. Start the Neo4j contianer via `./gradlew dockerRun` (it might take a bit until the container is fully up, fetching [APOC](https://neo4j.com/developer/neo4j-apoc/) during the start)
3. Create the graph via `./gradlew bootRun --args="--data.rebuildAtStart=true"` (can be run withot arguments, after first run)
4. Get yourself a ☕️ while the graph gets populated


<!-- USAGE EXAMPLES -->
## Usage

The application is build on top of a Neo4j graph based on [Berlin's subway system](https://en.wikipedia.org/wiki/Berlin_U-Bahn) inluding 175 stations (`nodes`) and 398 connections (`vertices`) across all nine Berlin subway lines. 

It was build to showcase the usage of Neo4j & it's pathfinding capabilities in concjunciton with Spring Boot, therefore primarily supporting the following use cases (_note: all use cases can be found in the Postman collection under `/api/BVG.postman_collection.json`_):

<details>

  <summary>⚡️ Find the shortest route between two stations</summary>
  If you simply want to find the shortest (in terms of number of stations) route between two stations, e.g.:


  ```curl
    [GET] http://localhost:8080/route?from=Alexanderplatz&to=Mehringdamm
  ```

  will yield

  <br>

  ```json
    {
    "segments": [
        {
            "from": {
                "name": "Alexanderplatz"
            },
            "to": {
                "name": "Jannowitzbrücke"
            },
            "line": "U8",
            "duration": 1
        },
        {
            "from": {
                "name": "Jannowitzbrücke"
            },
            "to": {
                "name": "Heinrich-Heine-Straße"
            },
            "line": "U8",
            "duration": 2
        },
        {
            "from": {
                "name": "Heinrich-Heine-Straße"
            },
            "to": {
                "name": "Moritzplatz"
            },
            "line": "U8",
            "duration": 1
        },
        {
            "from": {
                "name": "Moritzplatz"
            },
            "to": {
                "name": "Kottbusser Tor"
            },
            "line": "U8",
            "duration": 2
        },
        {
            "from": {
                "name": "Kottbusser Tor"
            },
            "to": {
                "name": "Prinzenstaße"
            },
            "line": "U3",
            "duration": 2
        },
        {
            "from": {
                "name": "Prinzenstaße"
            },
            "to": {
                "name": "Hallesches Tor"
            },
            "line": "U3",
            "duration": 2
        },
        {
            "from": {
                "name": "Hallesches Tor"
            },
            "to": {
                "name": "Mehringdamm"
            },
            "line": "U6",
            "duration": 2
        }
    ]
}
  ```
    
</details>

<details>

  <summary>🚫 Find the shortest route excluding certain lines</summary>
  If you simply want to find the shortest route between two stations without certain lines, e.g.:


  ```curl
    [GET] http://localhost:8080/route?from=Alexanderplatz&to=Mehringdamm&exclude=U8,U5
  ```

  will yield

  <br>

  ```json
  {
    "segments": [
        {
            "from": {
                "name": "Alexanderplatz"
            },
            "to": {
                "name": "Klosterstraße"
            },
            "line": "U2",
            "duration": 2
        },
        {
            "from": {
                "name": "Klosterstraße"
            },
            "to": {
                "name": "Märkisches Museum"
            },
            "line": "U2",
            "duration": 1
        },
        {
            "from": {
                "name": "Märkisches Museum"
            },
            "to": {
                "name": "Spittelmarkt"
            },
            "line": "U2",
            "duration": 2
        },
        {
            "from": {
                "name": "Spittelmarkt"
            },
            "to": {
                "name": "Hausvogteiplatz"
            },
            "line": "U2",
            "duration": 2
        },
        {
            "from": {
                "name": "Hausvogteiplatz"
            },
            "to": {
                "name": "Stadtmitte"
            },
            "line": "U2",
            "duration": 2
        },
        {
            "from": {
                "name": "Stadtmitte"
            },
            "to": {
                "name": "Kochstraße / Checkpoints Charlie"
            },
            "line": "U6",
            "duration": 1
        },
        {
            "from": {
                "name": "Kochstraße / Checkpoints Charlie"
            },
            "to": {
                "name": "Hallesches Tor"
            },
            "line": "U6",
            "duration": 1
        },
        {
            "from": {
                "name": "Hallesches Tor"
            },
            "to": {
                "name": "Mehringdamm"
            },
            "line": "U6",
            "duration": 2
        }
    ]
}
  ```
   
</details>

<details>

  <summary>⏱ Find the quickest route without</summary>
  If you simply want to find the fastest (in terms of number duration) route between two stations, e.g.:


  ```curl
    [GET] http://localhost:8080/route?from=Paradestraße&to=Boddinstraße&strategy=fastest
  ```

  will yield

  <br>

  ```json
    {
    "segments": [
        {
            "from": {
                "name": "Paradestraße"
            },
            "to": {
                "name": "Platz der Luftbrücke"
            },
            "line": "U6",
            "duration": 1
        },
        {
            "from": {
                "name": "Platz der Luftbrücke"
            },
            "to": {
                "name": "Mehringdamm"
            },
            "line": "U6",
            "duration": 2
        },
        {
            "from": {
                "name": "Mehringdamm"
            },
            "to": {
                "name": "Gneisenaustraße"
            },
            "line": "U7",
            "duration": 1
        },
        {
            "from": {
                "name": "Gneisenaustraße"
            },
            "to": {
                "name": "Südstern"
            },
            "line": "U7",
            "duration": 2
        },
        {
            "from": {
                "name": "Südstern"
            },
            "to": {
                "name": "Hermannplatz"
            },
            "line": "U7",
            "duration": 2
        },
        {
            "from": {
                "name": "Hermannplatz"
            },
            "to": {
                "name": "Boddinstraße"
            },
            "line": "U8",
            "duration": 1
        }
    ]
}
  ```
    
</details>

<details>

  <summary>📊 Get route summary</summary>
  If you're interested in some additional summary statistics, e.g.:


  ```curl
    [GET] http://localhost:8080/route?from=Paradestraße&to=Boddinstraße&summarized=true&strategy=fastest
  ```

  will yield

  <br>

  ```json
    {
    "segments": [
        {
            "from": {
                "name": "Paradestraße"
            },
            "to": {
                "name": "Platz der Luftbrücke"
            },
            "line": "U6",
            "duration": 1
        },
        {
            "from": {
                "name": "Platz der Luftbrücke"
            },
            "to": {
                "name": "Mehringdamm"
            },
            "line": "U6",
            "duration": 2
        },
        {
            "from": {
                "name": "Mehringdamm"
            },
            "to": {
                "name": "Gneisenaustraße"
            },
            "line": "U7",
            "duration": 1
        },
        {
            "from": {
                "name": "Gneisenaustraße"
            },
            "to": {
                "name": "Südstern"
            },
            "line": "U7",
            "duration": 2
        },
        {
            "from": {
                "name": "Südstern"
            },
            "to": {
                "name": "Hermannplatz"
            },
            "line": "U7",
            "duration": 2
        },
        {
            "from": {
                "name": "Hermannplatz"
            },
            "to": {
                "name": "Boddinstraße"
            },
            "line": "U8",
            "duration": 1
        }
    ],
    "stations": 7,
    "duration": 9,
    "strategy": "fastest"
}
  ```
   
    
</details>

---

If you're already familiar with some basic Neo4j concepts, you might just want to explore the Graph via the web UI:

1. open your browser at `localhost:7474`
2. login with user `neo4j` and password `bvg`

<!-- CONTACT -->
## Contact

Joshua Görner - [jgoerner](https://www.linkedin.com/in/jgoerner/) - joshua.goerner[at]gmail.com


<!-- ACKNOWLEDGEMENTS -->
## Acknowledgements
* [O. Drew](https://github.com/othneildrew/Best-README-Template) - nice GH Readme template

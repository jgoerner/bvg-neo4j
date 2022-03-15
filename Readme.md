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

It was build to showcase the usage of Neo4j & it's pathfinding capabilities in concjunciton with Spring Boot, therefore primarily supporting the following use cases:

<details>

  <summary>⚡️ Find the shortest path between two stations</summary>
    
</details>

<details>

  <summary>🚫 Find the shortest path excluding certain lines</summary>
    
</details>

<details>

  <summary>⏱ Find the quickest path without</summary>
    
</details>

<details>

  <summary>📊 Get path summary</summary>
    
</details>

<details>

  <summary>🧪 Explore the graph via the UI</summary>

  If you're already familiar with some basic Neo4j concepts, you might just want to explore the Graph via the web UI:

  1. open your browser at `localhost:7474`
  2. login with user `neo4j` and password `bvg`
    
</details>


<!-- CONTACT -->
## Contact

Joshua Görner - [jgoerner](https://www.linkedin.com/in/jgoerner/) - joshua.goerner[at]gmail.com


<!-- ACKNOWLEDGEMENTS -->
## Acknowledgements
* [O. Drew](https://github.com/othneildrew/Best-README-Template) - nice GH Readme template

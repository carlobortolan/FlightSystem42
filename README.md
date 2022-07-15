# FlightSystem42

An app for saving your planned flights, view detailed information and statistics as well as to entertain you during your
flight.

## Description

//TODO

## Getting Started

To start the FlightSystem you only need to run the main-method of the class
FlightSystem.java ```(main/java/com/example/eist22t02zweiundvierziger2022/FlightSystem.java)```

## Help

The server application runs on the default port (8080). In case this port is already in use you can change the port:

1. updating the client port: Replace ".baseUrl("http://localhost:8081/")" with ".baseUrl("http://localhost:<newHost>/")"
   at line 36 of the server/Client.java class
2. changing the port of the application: Go to FlightSystem.java.```[RUN]``` -> ```[Edit Configurations ...]```
   -> ```[Modify options]``` -> ```[Add VM options]``` -> input ```-Dserver.port=<newHost>``` in the VM Options field.

## Authors

Fabian Fritz
Carlo Bortolan (carlo.bortolan@tum.de)

## License

* Copyright (c)  2022, Carlo Bortolan, Fabian Fritz
* Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
  the License. You may obtain a copy of the License [here](http://www.apache.org/licenses/LICENSE-2.0) Unless required
  by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language
  governing permissions and limitations under the License.

## Sources

APIs, images, trailers, inspiration etc.

* [Lufthansa API]()
* [GoogleMaps API]()
* [FlightRadar API]()
* [OpenWeatherMap API]()
* [AutoCompleteComboBoxListener]()
* [README-Template](https://gist.github.com/DomPizzie/7a5ff55ffa9081f2de27c315f5018afc)

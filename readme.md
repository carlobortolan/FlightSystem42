# FlightSystem42

An app for saving your planned flights, view detailed information and statistics as well as to entertain you during your
flight.

## Description

//TODO

## Getting Started

To start the FlightSystem you only need to run the main-method of the class
_FlightSystem.java_ (```main/java/com/example/eist22t02zweiundvierziger2022/FlightSystem.java```)

## Help

The server application runs on the default port (8080). In case this port is already in use you can change the port:

1. updating the client port: Replace ```.baseUrl("http://localhost:8081/")```
   with ```.baseUrl("http://localhost:<newHost>/")```
   at line 36 of the server/Client.java class


2. changing the port of the application: Go to FlightSystem.java._[RUN]_ -> _[Edit Configurations ...]_
   -> _[Modify options]_ -> _[Add VM options]_ -> input ```-Dserver.port=<newHost>``` in the VM Options field.

## Authors

Fabian Fritz

Carlo Bortolan (carlo.bortolan@tum.de)

## License

Copyright (c)  2022, Carlo Bortolan, Fabian Fritz

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
the License. You may obtain a copy of the License [here](http://www.apache.org/licenses/LICENSE-2.0).

Unless required
by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language
governing permissions and limitations under the License.

## Sources

APIs

* [Lufthansa API](https://developer.lufthansa.com/docs/read/Home)
* [GoogleMaps API](https://developers.google.com/maps?hl=de)
* [GoogleMaps API](https://github.com/googlemaps/google-maps-services-java)
* [FlightRadar API](https://de.flightaware.com/live/flight/map/)
* [OpenWeatherMap API](https://openweathermap.org/api)

Icons

* [Arrows](https://www.subpng.com/png-gye36l/download.html)
* [Weather Icons 1](https://gist.github.com/h0wardch3ng/03047ea601e47e1476176833fd95efa0)
* [Weather Icons 2](https://prime.leitz-easyprint.com/easyprint/new-label/59cd232ba6434f1fad8e651a)
* [Airline icons](https://www.lufthansagroup.com/de/unternehmen/)
* [Lufthansa logo](https://commons.wikimedia.org/wiki/File:Lufthansa_Logo_2018.svg)

Images, trailers, inspirations etc.

* [Entertainment background](https://www.vecteezy.com/vector-art/1409192-abstract-geometric-banner)
* [POI background](http://travelservices.com.pk/)
* [Service background](https://www.freepik.com/free-photo/abstract-blur-defocused-seat-airplane-interior_4011543.htm)
* [Flight background](https://wallpaperaccess.com/summer-4k-ultra-wide#google_vignette)
* [Lufthansa plane1](https://www.lufthansa.com/se/en/seat-maps)
* [Service - Water](https://www.suedkurier.de/storage/image/9/2/8/9/12589829_shift-644x395_1wJzds_HrirBN.jpg)
* [Service - Apple juice](https://pajaten.de/wp-content/uploads/2021/01/1aaaa-1.jpg)
* [Service - Coke](https://prospekte.metro.de/3985/988341/pages/84d4aa48ffd3f6e9f503807813970e4860ec5217-at1000.jpg)
* [Service - Hot Chocolate](https://www.einfachbacken.de/sites/einfachbacken.de/files/styles/full_width_tablet_4_3/public/2020-08/heisse_schokolade.jpg?h=4521fff0&itok=j9bCRKO_)
* [Service - Coffee](https://www.dreamstime.com/stock-photo-espresso-coffee-cup-beans-vintage-table-image90374872)
* [Service - Tea](https://www.stuttgarter-zeitung.de/media.media.50511cf2-a992-4e73-bb76-ae9477f631b0.original1024.jpg)
* [Service - Beer](https://cdn.pixabay.com/photo/2016/03/30/13/05/beer-1290633_1280.jpg)
* [Service - Champagne](https://images.unsplash.com/photo-1630771496884-46ce7c270a52?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1yZWxhdGVkfDE1fHx8ZW58MHx8fHw%3D&w=1000&q=80)
* [Service - Martini](https://media.istockphoto.com/photos/martinis-with-shaker-picture-id90150166?k=20&m=90150166&s=612x612&w=0&h=pTXiDIIUAiLcvqgcvCDLzXYs1xsF7R1-ZXaBJ7dT-O4=)


* [FlightSafety Logo](https://www.lufthansa-aviation-training.com/documents/10605707/10606850/FSI-Logo.png/1a0384c4-154c-40b0-b937-5a01ecec87e4?t=1582283298000)
* [Movie Poster1](https://m.media-amazon.com/images/M/MV5BNGUxYWM3M2MtMGM3Mi00ZmRiLWE0NGQtZjE5ODI2OTJhNTU0XkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_.jpg)
* [Movie Poster2](https://m.media-amazon.com/images/M/MV5BYWZjMjk3ZTItODQ2ZC00NTY5LWE0ZDYtZTI3MjcwN2Q5NTVkXkEyXkFqcGdeQXVyODk4OTc3MTY@._V1_.jpg)
* [Movie Poster3](https://static.wikia.nocookie.net/bondwiki/images/f/f3/2006-casino-royale-poster.png/revision/latest?cb=20200314201737)
* [Movie Poster4](https://m.media-amazon.com/images/M/MV5BNzg4MjQxNTQtZmI5My00YjMwLWJlMjUtMmJlY2U2ZWFlNzY1XkEyXkFqcGdeQXVyODk4OTc3MTY@._V1_.jpg)
* [Movie Poster5](https://m.media-amazon.com/images/M/MV5BZTM2ZGJmNjQtN2UyOS00NjcxLWFjMDktMDE2NzMyNTZlZTBiXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_FMjpg_UX1000_.jpg)
* [Movie Poster6](https://m.media-amazon.com/images/M/MV5BM2MyNjYxNmUtYTAwNi00MTYxLWJmNWYtYzZlODY3ZTk3OTFlXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_.jpg)
* [Movie Poster7](https://m.media-amazon.com/images/M/MV5BY2NkZjEzMDgtN2RjYy00YzM1LWI4ZmQtMjIwYjFjNmI3ZGEwXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_.jpg)
* [Movie Poster8](https://upload.wikimedia.org/wikipedia/commons/c/c5/Lawrence_of_arabia_ver3_xxlg.jpg)
* [Movie Poster9](https://m.media-amazon.com/images/M/MV5BZmExNmEwYWItYmQzOS00YjA5LTk2MjktZjEyZDE1Y2QxNjA1XkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_FMjpg_UX1000_.jpg)
* [Movie Poster10](https://m.media-amazon.com/images/M/MV5BNjdjNGQ4NDEtNTEwYS00MTgxLTliYzQtYzE2ZDRiZjFhZmNlXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_FMjpg_UX1000_.jpg)
* [Movie Poster11](https://m.media-amazon.com/images/I/71715eBi1sL._AC_SY879_.jpg)
* [Movie Poster12](https://upload.wikimedia.org/wikipedia/commons/b/b5/12_Angry_Men_%281957_film_poster%29.jpg)
* [Movie Poster13](https://m.media-amazon.com/images/M/MV5BNDE4OTMxMTctNmRhYy00NWE2LTg3YzItYTk3M2UwOTU5Njg4XkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_.jpg)
* [Movie Poster14](https://m.media-amazon.com/images/M/MV5BMDJjNWE5MTEtMDk2Mi00ZjczLWIwYjAtNzM2ZTdhNzcwOGZjXkEyXkFqcGdeQXVyNDIzMzcwNjc@._V1_.jpg)
* [Movie Poster15](https://m.media-amazon.com/images/M/MV5BODk4MTkxMDE1Ml5BMl5BanBnXkFtZTgwNjE0NjEzOTE@._V1_.jpg)
* [Movie Poster16](https://m.media-amazon.com/images/I/51qbiQjZcGL._AC_.jpg)
* [Music Poster1](https://www.google.com/url?sa=i&url=https%3A%2F%2Fm.imdb.com%2Ftitle%2Ftt4654462%2Ftrivia%2F%3Fref_%3Dtt_ql_trv&psig=AOvVaw1S8bTwFJlO4GjTyU9tfhW1&ust=1658001746908000&source=images&cd=vfe&ved=0CAsQjRxqFwoTCNju2_zX-_gCFQAAAAAdAAAAABAJ)
* [Music Poster2](https://www.amazon.de/-/en/Ost/dp/B000FUH3KK)
* [Music Poster3](https://en.wikipedia.org/wiki/Poker_Face_%28song%29)
* [Music Poster4](https://www.amazon.de/Liberty-Action-Deluxe-Bosshoss/dp/B005OU0F1U)


* [Flight safety instruction](https://www.youtube.com/watch?v=YCoQwZ9BQ9Q)
* [Movie Trailer1](https://www.youtube.com/watch?v=lZMIrD36MG8)
* [Movie Trailer2](https://www.youtube.com/watch?v=vayksn4Y93A)
* [Movie Trailer3](https://www.youtube.com/watch?v=_AznzZAlwVA)
* [Movie Trailer4](https://www.youtube.com/watch?v=9l-ViOOFH-s&t=35s)
* [Movie Trailer5](https://www.youtube.com/watch?v=2ilzidi_J8Q)
* [Movie Trailer6](https://www.youtube.com/watch?v=UaVTIH8mujA)
* [Movie Trailer7](https://www.youtube.com/watch?v=5YnGhW4UEhc)
* [Movie Trailer8](https://www.youtube.com/watch?v=vZ734NWnAHA)
* [Movie Trailer9](https://www.youtube.com/watch?v=36mnx8dBbGE)
* [Movie Trailer10](https://www.youtube.com/watch?v=isOGD_7hNIY)
* [Movie Trailer11](https://www.youtube.com/watch?v=m01YktiEZCw)
* [Movie Trailer12](https://www.youtube.com/watch?v=FCctqbRrsBQ)
* [Movie Trailer13](https://www.youtube.com/watch?v=0n_HQwQU8ls)
* [Movie Trailer14](https://www.youtube.com/watch?v=gG22XNhtnoY)
* [Movie Trailer15](https://www.youtube.com/watch?v=euB6PWW6tcI)
* [Movie Trailer16](https://www.youtube.com/watch?v=2GfZl4kuVNI)
* [Movie Trailer17](https://www.youtube.com/watch?v=_13J_9B5jEk)
* [Movie Trailer18](https://www.youtube.com/watch?v=PLl99DlL6b4)
* [Music Video1](https://www.youtube.com/watch?v=dQw4w9WgXcQ)
* [Music Video2](https://www.youtube.com/watch?v=pS5d77DQHOI)
* [Music Video3](https://www.youtube.com/watch?v=bESGLojNYSo)
* [Music Video4](https://www.youtube.com/watch?v=D-f8GMXP7qI)


* [Scene-builder](https://gluonhq.com/products/scene-builder/)
* [JavaFX](https://gluonhq.com/products/javafx/)
* [AutoCompleteComboBoxListener](https://stackoverflow.com/questions/19924852/autocomplete-combobox-in-javafx/20282301#20282301)
* [Class diagram](https://www.visual-paradigm.com/guide/uml-unified-modeling-language/uml-class-diagram-tutorial/;WWWSESSIONID=AD30D0F031F24ACB04C07A8F0B2A0FF6.www1)
* [Include and exclude relationships](https://www.educative.io/answers/what-are-include-and-extend-relationships-in-a-use-case-diagram)
* [Spring Boot port configuration](https://stackoverflow.com/questions/21083170/how-to-configure-port-for-a-spring-boot-application)
* [README-Template](https://gist.github.com/DomPizzie/7a5ff55ffa9081f2de27c315f5018afc)
* [Copyright](https://www.jetbrains.com/help/idea/copyright.html)
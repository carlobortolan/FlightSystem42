# FlightSystem42

An app for saving your planned flights, view detailed information and statistics as well as to entertain you during your
flight.

## Description

#### Technology

The client application's user interface is build using the JavaFX framework (mainly FXML files) while the server is
based on the Java Spring Boot Framework. The application uses Gradle as its Build system.

#### Functionality

It is important to keep in mind that all used and displayed data is real as the app gets its data from different API's (
for more details view sources).
Therefore, you can search for any existing city/airport/flight in the world and will get live flight data and current
weather conditions all around the world.  
The functionality of the app can be divided into five main functional areas:

- __Search for flights__
    - You can search for real flights by going to the "_Search for flights_" tab and input a start and destination city.
      This can be done by either writing the city's name and then clicking on one of the suggested airports or by simply
      typing the IATA Code of the Airport you want to start from / arrive to. You can also choose to select a certain
      date (if not, today's date will automatically be used) and filter direct flights by pressing the "_Direct_"
      Button. After pressing "_Search_" if no flights were found you will get a notification, otherwise there will
      appear all found flights based on your search criteria with the most important information such as _start,
      terminal at start, destination, terminal at destination, departure time, estimated time of arrival duration and
      the flight
      number_.
    - Each flight consists of at least one flight-object which
      represents a single direct flight. So, for example when you search from _MUC_ to _JFK_ many flights will first fly
      to _FRA_ where you will have to change to another flight to _JFK_. On every flight you can choose to save the
      flight to your personal flight collection by pressing "_add to my flights_" and then going to the "_My Flights_"
      tab. Of course, if you have saved a delayed flight, you will be notified about it. Once you've added a flight you
      can also remove it by pressing "_Remove from my flights_".


- __Flight details__
    - View status: You can see the current status of a flight by pressing "_View status_". There you will be shown
      whether the flight is on time or if it is delayed (with the corresponding delay) as well as the original, expected
      and actual time of departure and arrival with the Gate and Terminal of both the start's and the destination's
      airport.

      If you like to, you can closely monitor the current position, altitude and velocity of your airplane by
      pressing "_Follow Flight on Map_" where a new window with a map and detailed statistics will appear.
    - View details: By pressing on "_View details_" you can see details of the city you're flying from/to such as the
      _name, country, weather (with corresponding icon), current temperature, current temperature feels like, min and
      max temperature of the day, wind direction, wind speed and on top of all that also an image of the city_.


- __Map / My locations service__
    - You can access a global map by pressing "_View details_" and then "_Open on map_" on any flight where you will be
      shown your current trim. From there you can view the city you're flying from/to and the app will provide you with
      a detailed description of the city, images and much more. You can also search for attractions/hotels/restaurants
      at your destination, filter them, read user feedback, book a hotel/restaurant by pressing the respective buttons
      and save selected locations to your personal collection. You can either access this collection by pressing "_Show
      favorites_" or by going to the "_My Locations tab_".


- __Media and entertainment system__
    - Watch flight instructions: Press on "_Instructions_" where a new window with a safety-instruction video will
      open.
    - Watch movies: Press on "_Movies_" where you will be able to watch any of the 17 preselected movies in HD-Quality
      by clicking on the cover of the movie you want to watch.
    - Listen to music: Press on "_Music_" and select the piece you want to listen to by clicking on its cover. You can
      also read the lyrics (or, if there are none - a detailed analysis of the piece) by clicking on the cover of the
      music player that appears once you've started a piece.


- __In-flight services__
    - Submit survey / review flight: To submit a survey you need to switch to the "_In-flight Service_" tab and enter
      the Flight-Number of the flight you want to rate and, if you like to, your name too. After that you will have to
      answer different questions by giving them a rating from 1 (=very bad) to 5 (=very good) and optionally add a text
      with feedback. After the completion of the survey you will get a gift card.
    - Request drinks: By clicking on "_Drinks_" you will be shown different drinks that you can request by pressing on
      the icon of the drink you want.
    - Request stewardess: By clicking on the stewardess icon on the right of the "_Drink/Survey_" Button a new alert
      will open where you can input your request that will be directly send to the server.

## Getting Started

### Start app

To start the FlightSystem you only need to run the main-method of the class
_FlightSystem.java_ (```main/java/com/example/eist22t02zweiundvierziger2022/FlightSystem.java```). This will
automatically start the app and the server.
The loading of the app should normally take from 5 up to 90 seconds, but may vary depending on your device (and if you
have just cloned it or already started it once before).

### Open server

The Spring Boot server has three relevant windows that you can access by either using the provided hyperlink or copying
and pasting the respective link in a browser.
(In case you changes the client/server port you will have to update the link)

1. [View submitted surveys](http://localhost:8081/Surveys) ``http://localhost:8080/Surveys``
2. [View drink orders](http://localhost:8081/Drinks) ``http://localhost:8080/Drinks``
3. [View current in-flight requests](http://localhost:8081/Requests) ``http://localhost:8080/Requests``

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

### APIs

* [Lufthansa API](https://developer.lufthansa.com/docs/read/Home)
* [GoogleMaps API](https://developers.google.com/maps?hl=de)
* [GoogleMaps API](https://github.com/googlemaps/google-maps-services-java)
* [FlightRadar API](https://de.flightaware.com/live/flight/map/)
* [OpenWeatherMap API](https://openweathermap.org/api)

### Icons

* [Arrows](https://www.subpng.com/png-gye36l/download.html)
* [Weather Icons 1](https://gist.github.com/h0wardch3ng/03047ea601e47e1476176833fd95efa0)
* [Weather Icons 2](https://prime.leitz-easyprint.com/easyprint/new-label/59cd232ba6434f1fad8e651a)
* [Airline icons](https://www.lufthansagroup.com/de/unternehmen/)
* [Lufthansa logo](https://commons.wikimedia.org/wiki/File:Lufthansa_Logo_2018.svg)

### Images, videos, inspirations etc.

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
* [Movie Poster 1](https://m.media-amazon.com/images/I/51qbiQjZcGL._AC_.jpg)
* [Movie Poster 2](https://upload.wikimedia.org/wikipedia/commons/c/c5/Lawrence_of_arabia_ver3_xxlg.jpg)
* [Movie Poster 3](https://m.media-amazon.com/images/M/MV5BYWZjMjk3ZTItODQ2ZC00NTY5LWE0ZDYtZTI3MjcwN2Q5NTVkXkEyXkFqcGdeQXVyODk4OTc3MTY@._V1_.jpg)
* [Movie Poster 4](https://www.juniqe.ch/apocalypse-now-retro-movie-poster-premium-poster-portrait-4018951.html#step=design&productId=4018951&frameId=false)
* [Movie Poster 5](https://static.wikia.nocookie.net/bondwiki/images/f/f3/2006-casino-royale-poster.png/revision/latest?cb=20200314201737)
* [Movie Poster 6](https://m.media-amazon.com/images/M/MV5BZTM2ZGJmNjQtN2UyOS00NjcxLWFjMDktMDE2NzMyNTZlZTBiXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_FMjpg_UX1000_.jpg)
* [Movie Poster 7](https://upload.wikimedia.org/wikipedia/commons/b/b5/12_Angry_Men_%281957_film_poster%29.jpg)
* [Movie Poster 8](https://m.media-amazon.com/images/M/MV5BM2MyNjYxNmUtYTAwNi00MTYxLWJmNWYtYzZlODY3ZTk3OTFlXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_.jpg)
* [Movie Poster 9](https://m.media-amazon.com/images/M/MV5BY2NkZjEzMDgtN2RjYy00YzM1LWI4ZmQtMjIwYjFjNmI3ZGEwXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_.jpg)
* [Movie Poster 10](https://m.media-amazon.com/images/M/MV5BZmExNmEwYWItYmQzOS00YjA5LTk2MjktZjEyZDE1Y2QxNjA1XkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_FMjpg_UX1000_.jpg)
* [Movie Poster 11](https://m.media-amazon.com/images/M/MV5BMDJjNWE5MTEtMDk2Mi00ZjczLWIwYjAtNzM2ZTdhNzcwOGZjXkEyXkFqcGdeQXVyNDIzMzcwNjc@._V1_.jpg)
* [Movie Poster 12](https://m.media-amazon.com/images/M/MV5BNjdjNGQ4NDEtNTEwYS00MTgxLTliYzQtYzE2ZDRiZjFhZmNlXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_FMjpg_UX1000_.jpg)
* [Movie Poster 13](https://m.media-amazon.com/images/M/MV5BODk4MTkxMDE1Ml5BMl5BanBnXkFtZTgwNjE0NjEzOTE@._V1_.jpg)
* [Movie Poster 14](https://m.media-amazon.com/images/M/MV5BNDE4OTMxMTctNmRhYy00NWE2LTg3YzItYTk3M2UwOTU5Njg4XkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_.jpg)
* [Movie Poster 15](https://m.media-amazon.com/images/M/MV5BNzg4MjQxNTQtZmI5My00YjMwLWJlMjUtMmJlY2U2ZWFlNzY1XkEyXkFqcGdeQXVyODk4OTc3MTY@._V1_.jpg)
* [Movie Poster 16](https://m.media-amazon.com/images/M/MV5BNGUxYWM3M2MtMGM3Mi00ZmRiLWE0NGQtZjE5ODI2OTJhNTU0XkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_.jpg)
* [Movie Poster 17](https://m.media-amazon.com/images/I/71715eBi1sL._AC_SY879_.jpg)

* [Music Poster1](https://www.google.com/url?sa=i&url=https%3A%2F%2Fm.imdb.com%2Ftitle%2Ftt4654462%2Ftrivia%2F%3Fref_%3Dtt_ql_trv&psig=AOvVaw1S8bTwFJlO4GjTyU9tfhW1&ust=1658001746908000&source=images&cd=vfe&ved=0CAsQjRxqFwoTCNju2_zX-_gCFQAAAAAdAAAAABAJ)
* [Music Poster2](https://www.amazon.de/-/en/Ost/dp/B000FUH3KK)
* [Music Poster3](https://en.wikipedia.org/wiki/Poker_Face_%28song%29)
* [Music Poster4](https://www.amazon.de/Liberty-Action-Deluxe-Bosshoss/dp/B005OU0F1U)


* [Music Poster 1](https://www.google.com/url?sa=i&url=https%3A%2F%2Fm.imdb.com%2Ftitle%2Ftt4654462%2Ftrivia%2F%3Fref_%3Dtt_ql_trv&psig=AOvVaw1S8bTwFJlO4GjTyU9tfhW1&ust=1658001746908000&source=images&cd=vfe&ved=0CAsQjRxqFwoTCNju2_zX-_gCFQAAAAAdAAAAABAJ)
* [Music Poster 2](https://www.amazon.de/Chopin-Etudes-Opp-10-Maurizio-Pollini/dp/B004HOZ6JK)
* [Music Poster 3](https://m.media-amazon.com/images/I/81MnZ1wREoL._SL1500_.jpg)
* [Music Poster 4](https://www.amazon.de/Evgeny-Kissin-plays-Chopin/dp/B00BT2IAN2)
* [Music Poster 5](https://i.ytimg.com/vi/Dfx_9BnTUHM/maxresdefault.jpg)
* [Music Poster 6](https://www.discogs.com/master/1933506-Ludwig-van-Beethoven-Vladimir-Ashkenazy-Piano-Sonatas-opp-109-110-111/image/SW1hZ2U6NjU1Njc1ODE=)
* [Music Poster 7](https://www.discogs.com/de/release/7544533-Bach-Grigory-Sokolov-The-Art-Of-Fugue)
* [Music Poster 8](https://is4-ssl.mzstatic.com/image/thumb/Music126/v4/1e/bc/98/1ebc98ef-c55a-19ce-8970-525ac838f844/886443706682.jpg/500x500bb.webp)


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
* [Movie Trailer13](https://www.youtube.com/watch?v=gG22XNhtnoY)
* [Movie Trailer14](https://www.youtube.com/watch?v=euB6PWW6tcI)
* [Movie Trailer15](https://www.youtube.com/watch?v=2GfZl4kuVNI)
* [Movie Trailer16](https://www.youtube.com/watch?v=_13J_9B5jEk)
* [Movie Trailer17](https://www.youtube.com/watch?v=PLl99DlL6b4)
* [Music Video1](https://www.youtube.com/watch?v=dQw4w9WgXcQ)
* [Music Video2](https://www.youtube.com/watch?v=pS5d77DQHOI)
* [Music Video3](https://www.youtube.com/watch?v=bESGLojNYSo)
* [Music Video4](https://www.youtube.com/watch?v=D-f8GMXP7qI)


* [Music Video 1](https://www.youtube.com/watch?v=dQw4w9WgXcQ)
* [Music Video 2](https://www.youtube.com/watch?v=l4zkc7KEvYM&t)
* [Music Video 3](https://www.youtube.com/watch?v=DPJL488cfRw&t)
* [Music Video 4](https://www.youtube.com/watch?v=KBPq-BNiCr8)
* [Music Video 5](https://www.youtube.com/watch?v=KhHVTeuW5zI)
* [Music Video 6](https://www.youtube.com/watch?v=0fOiCgdwLaw)
* [Music Video 7](https://www.youtube.com/watch?v=HlvNKc5pYrk)
* [Music Video 8](https://www.youtube.com/watch?v=KDJ6Wbzgy3E)


* [Scene-builder](https://gluonhq.com/products/scene-builder/)
* [JavaFX](https://gluonhq.com/products/javafx/)
* [AutoCompleteComboBoxListener](https://stackoverflow.com/questions/19924852/autocomplete-combobox-in-javafx/20282301#20282301)
* [Class diagram](https://www.visual-paradigm.com/guide/uml-unified-modeling-language/uml-class-diagram-tutorial/;WWWSESSIONID=AD30D0F031F24ACB04C07A8F0B2A0FF6.www1)
* [Include and exclude relationships](https://www.educative.io/answers/what-are-include-and-extend-relationships-in-a-use-case-diagram)
* [Spring Boot port configuration](https://stackoverflow.com/questions/21083170/how-to-configure-port-for-a-spring-boot-application)
* [README-Template](https://gist.github.com/DomPizzie/7a5ff55ffa9081f2de27c315f5018afc)
* [Copyright](https://www.jetbrains.com/help/idea/copyright.html)

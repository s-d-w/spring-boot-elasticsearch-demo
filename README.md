### Spring Boot Elasticsearch Demo

###### Overview
A simple Spring Boot app showing how to use Elasticsearch. It has only one functionality and that is to return the closest 'building' to the coordinates passed in. Embedded H2 is used for tests as well as the runtime database. The embedded version of ES was used. Liquibase is used to manage the schema as well as to put that schema in the embeded H2 db each time the app is started. You can find the migrations under src/main/resources.


###### Setup, Running, and Usage
To compile: mvn clean install
Then, to run: mvn spring-boot:run

The locations.csv is read during a bootstrap sequence orchestrated using @PostConstruct. a new 'building' is created for each row in the csv, persisted to the relational db (H2), then indexed into Elasticsearch. Though the relational db is never read from in this app, a typical use case is to fetch the 'building' from the relational db so as to edit it. Once editing is complete, it can be saved back to the relational db and indexed at the same time.

To retrieve the nearest building to a pair of gps coords, either curl it or put that in your browser:
http://localhost:8080/building/findnearest?lat=39.7&lon=-105.1
Which returns something like:
```javascript
{
  "nearestBuilding": {
    "latitude": 39.72757401,
    "longitude": -105.092103,
    "totalInsuredValue": 700000,
    "occupancy": "Permanent Dwelling (single family housing)",
    "yearBuilt": 1964,
    "squareFootage": 14259,
    "numberOfStories": 42,
    "construction": "masonry"
  },
  "originalLatitude": 39.7,
  "originalLongitude": -105.1,
  "distanceBetweenInKilometers": 3.1396147399368792
}
```


##### TODO
-add a UI that uses Open Street Maps
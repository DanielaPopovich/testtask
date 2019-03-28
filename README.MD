Feel free to complete this test task, override or change everything

For start app: 
1. Upadte tmdb.apikey in src\main\resources\application.properties
2. Run mvn clean install
3. Run mvn spring-boot:run

### Register user
curl -X POST --data "email=test@test2.com&password=test" http://localhost:8080/user/register/ -v

### View popular movies
curl -X POST --data "email=test@test2.com&password=test" http://localhost:8080/movie/popular/ -v

###Add favorite actor
curl -X POST --data "email=test@test2.com&password=test&actorFirstName=Test&actorLastName=Test" http://localhost:8080/actors/add/ -v

###Get user favorite actors
curl -X GET "http://localhost:8080/actors/favorite?email=test@test2.com&password=test"

###Delete user favorite actor
curl -X DELETE "http://localhost:8080/actors/remove?email=test@test2.com&password=test&actorId={actorId}"

###Mark a movie watched
curl -X POST --data "email=test@test2.com&password=test&movieId={movieId}" http://localhost:8080/movie/watched/ -v

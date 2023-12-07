Request:
```shell
curl -X GET \
  'http://localhost:8080/api/show/movie?movieName=YourMovieName' \
  -H 'Authorization: Bearer YourBearerToken' \
  -H 'Content-Type: application/json'
```

<hr>

Response:
```shell
{
  "showList": [
    {
      "showId": "abc123",
      "movieResponse": {
        "movieId": "movie_abc",
        "movieName": "Sample Movie 1",
        "releaseDate": "2023-12-31T00:00:00Z",
        "exitDate": "2024-01-31T00:00:00Z",
        "movieDuration": 120
      },
      "theatreResponse": {
        "theatreId": "theatre_xyz",
        "theatreName": "Sample Theatre 1",
        "auditoriumNumberToNumberOfSeats": {
          "1": 100,
          "2": 150
        },
        "theatreAddress": "123 Main Street",
        "theatreCity": "City Name",
        "theatreState": "State Name"
      },
      "auditorium": 1,
      "showTime": "2023-12-31T18:00:00Z"
    },
    {
      "showId": "def456",
      "movieResponse": {
        "movieId": "movie_abc",
        "movieName": "Sample Movie 2",
        "releaseDate": "2024-01-01T00:00:00Z",
        "exitDate": "2024-01-31T00:00:00Z",
        "movieDuration": 150
      },
      "theatreResponse": {
        "theatreId": "theatre_pqr",
        "theatreName": "Sample Theatre 2",
        "auditoriumNumberToNumberOfSeats": {
          "1": 120,
          "2": 180
        },
        "theatreAddress": "456 Broadway",
        "theatreCity": "Another City",
        "theatreState": "Another State"
      },
      "auditorium": 2,
      "showTime": "2024-01-01T14:00:00Z"
    }
  ],
  "message": "Successfully fetched all the movies!"
}
```
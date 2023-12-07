Request:
```shell
curl -X GET \
  'http://localhost:8080/api/movie?movieName=YourMovieName&page=0&size=10' \
  -H 'Content-Type: application/json' \
  -H 'Authorization: Bearer YOUR_TOKEN_HERE'
```

<hr>

Response:
```shell
{
  "movieList": [
    {
      "movieId": "abc123", // Example movie ID
      "movieName": "Sample Movie",
      "releaseDate": "2023-12-31T00:00:00Z",
      "exitDate": "2024-01-31T00:00:00Z",
      "movieDuration": 120
    },
    {
      "movieId": "def456", // Another example movie ID
      "movieName": "Another Movie",
      "releaseDate": "2024-02-15T00:00:00Z",
      "exitDate": "2024-03-15T00:00:00Z",
      "movieDuration": 135
    }
  ],
  "message": "Successfully got all the movies!"
}
```
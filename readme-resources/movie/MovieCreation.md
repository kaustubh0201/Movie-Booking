Request:
```shell
curl -X POST \
  http://localhost:8080/api/movie \
  -H 'Content-Type: application/json' \
  -H 'Authorization: Bearer YOUR_TOKEN_HERE' \
  -d '{
    "movieName": "Example Movie",
    "releaseDate": "2023-12-31T00:00:00Z",
    "exitDate": "2024-01-31T00:00:00Z",
    "movieDuration": 120
}'
```

<hr>

Response:
```shell
{
  "movieResponse": {
    "movieId": "abc123", // Example movie ID
    "movieName": "Sample Movie",
    "releaseDate": "2023-12-31T00:00:00Z",
    "exitDate": "2024-01-31T00:00:00Z",
    "movieDuration": 120
  },
  "message": "Movie has been successfully created!"
}
```
Request:
```shell
curl -X POST \
  http://localhost:8080/api/show \
  -H 'Content-Type: application/json' \
  -H 'Authorization: Bearer YOUR_TOKEN_HERE' \
  -d '{
    "movieId": "abc123", // Replace with the actual movie ID
    "theatreId": "xyz789", // Replace with the actual theatre ID
    "auditorium": 1,
    "showTime": "2024-01-01T18:00:00Z"
}'
```

<hr>

Response:
```shell
{
  "show": {
    "showId": "abc123", // Example show ID
    "movieId": "xyz789", // Example movie ID
    "theatreId": "def456", // Example theatre ID
    "auditorium": 1,
    "showTime": "2024-01-01T18:00:00Z"
  },
  "message": "Successfully created show!"
}
```
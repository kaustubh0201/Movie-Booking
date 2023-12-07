Request:
```shell
curl -X POST \
  http://localhost:8080/api/theatre \
  -H 'Content-Type: application/json' \
  -H 'Authorization: Bearer YOUR_TOKEN_HERE' \
  -d '{
    "theatreName": "Sample Theatre",
    "auditoriumNumberToNumberOfSeats": {
        "1": 100,
        "2": 150
    },
    "theatreAddress": "123 Main Street",
    "theatreCity": "City Name",
    "theatreState": "State Name"
}'
```

<hr>

Response:
```shell
{
  "theatre": {
    "theatreId": "abc123", // Example theatre ID
    "theatreName": "Sample Theatre",
    "auditoriumNumberToNumberOfSeats": {
      "1": 100,
      "2": 150
    },
    "theatreAddress": "123 Main Street",
    "theatreCity": "City Name",
    "theatreState": "State Name"
  },
  "message": "Theatre has been created successfully!"
}
```
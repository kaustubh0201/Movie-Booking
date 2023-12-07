Request:
```shell
curl -X GET \
  'http://localhost:8080/api/theatre/city?theatreCity=CityName&page=0&size=10' \
  -H 'Content-Type: application/json' \
  -H 'Authorization: Bearer YOUR_TOKEN_HERE'
```

<hr>

Response:
```shell
{
  "theatreList": [
    {
      "theatreId": "abc123",
      "theatreName": "Sample Theatre 1",
      "auditoriumNumberToNumberOfSeats": {
        "1": 100,
        "2": 150
      },
      "theatreAddress": "123 Main Street",
      "theatreCity": "City Name",
      "theatreState": "State Name"
    },
    {
      "theatreId": "def456",
      "theatreName": "Sample Theatre 2",
      "auditoriumNumberToNumberOfSeats": {
        "1": 120,
        "2": 130
      },
      "theatreAddress": "456 Elm Street",
      "theatreCity": "City Name",
      "theatreState": "State Name"
    }
  ],
  "message": "Successfully fetched theatres by theatre city."
}
```
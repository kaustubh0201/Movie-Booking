Request:
```shell
curl -X POST \
  'http://localhost:8080/api/create-booking-endpoint' \
  -H 'Authorization: Bearer YourBearerToken' \
  -H 'Content-Type: application/json' \
  -d '{
    "showId": "YourShowID",
    "bookedSeats": [1, 2, 3]  // Replace with the desired seat numbers
  }'
```

<hr>

Response:
```shell
{
  "booking": {
    "bookingId": "abc123", // Example booking ID
    "showId": "xyz789",    // Example show ID
    "username": "user123", // Example username
    "bookedSeats": [1, 2, 3] // Example booked seats
  },
  "message": "Booking done successfully"
}
```
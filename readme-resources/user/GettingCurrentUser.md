Request:
```shell
curl -X GET \
  'http://localhost:8080/api/currentUser' \
  -H 'Authorization: Bearer YOUR_BEARER_TOKEN'
```

<hr>

Response:
```shell
{
  "currentUser": {
    "userId": "someUserId",
    "name": "John Doe",
    "username": "johndoe",
    "emailId": "johndoe@example.com",
    "password": "hashedPassword",
    "roles": ["ROLE_USER"],
    "isVerified": true,
    "otp": 569874
  },
  "message": "Successfully got the value of the current user."
}
```
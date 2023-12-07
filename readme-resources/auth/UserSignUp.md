Request:

```shell
curl -X POST \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Your Name",
    "username": "your_username",
    "emailId": "your_email@example.com",
    "password": "your_password"
  }' \
  http://localhost:8080/api/auth/singup
```

<hr>

Response:

```shell
{
    "message": "User has been successfully registered and OTP has been sent for verification."
}
```



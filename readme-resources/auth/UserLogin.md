Request:
```shell
curl -X POST \
  -H "Content-Type: application/json" \
  -d '{
    "username": "your_username",
    "password": "your_password"
  }' \
  http://localhost:8080/api/auth/login
```

<hr>

Response:

```shell
{
  "message": "User your_username successfully logged in!",
  "accessToken": "your_access_token_here",
  "refreshToken": "your_refresh_token_here"
}
```
Request:

```shell
curl -X POST \
  -H "Authorization: Bearer your_refresh_token_here" \
  http://localhost:8080/api/auth/refresh-token
```

<hr>

Response:
```shell
{
  "message": "Access token has been refreshed!",
  "accessToken": "your_new_access_token_here",
  "refreshToken": "your_refresh_token_here"
}
```

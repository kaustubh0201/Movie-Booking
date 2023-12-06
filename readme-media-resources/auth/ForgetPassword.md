Request:
```shell
curl -X PATCH \
  "http://localhost:8080/api/auth/forget-password?emailId=your_email@example.com"
```

<hr>

Response:
```shell
{
  "message": "OTP has been sent to the emailId for password change."
}
```
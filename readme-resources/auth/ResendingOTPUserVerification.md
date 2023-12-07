Request:
```shell
curl -X PATCH \
  "http://localhost:8080/api/auth/resend-otp?emailId=your_email@example.com"

```

<hr>

Response:
```shell
{
  "message": "OTP has been sent for verification again!"
}
```
Request:
```shell
curl -X PATCH \
  "http://localhost:8080/api/auth/forget-password-verify?emailId=your_email@example.com&otp=your_otp_here&password=your_new_password_here"
```

<hr>

Response:

```shell
{
  "message": "Password has been changed successfully!"
}
```
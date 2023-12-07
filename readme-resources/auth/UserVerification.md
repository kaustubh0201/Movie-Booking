Request:

```shell
curl -X PATCH \
"http://localhost:8080/api/auth/verify?emailId=your_email@example.com&otp=your_otp_here"
```
<hr>

Response:
```shell
{
  "message": "User has been verified successfully!"
}
```
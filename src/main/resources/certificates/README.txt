cd c:\Users\Admin\IdeaProjects\b2x2\src\main\resources\certificates
certbot certonly -a standalone -d b2x2.com -d www.b2x2.com --register-unsafely-without-email
openssl pkcs12 -export -in fullchain.pem -inkey privkey.pem -out keystore.p12 -name b2x2 -CAfile chain.pem -caname root
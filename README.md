1. CRUD API
2. API to Prepare Large Excel sheets
3. Exception Handling
4. Table Mapping
5. Mapstruct
6. Flyway
7. Custom Annotations For Validation

# first-quarkus-app

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus Dev UI available at http://localhost:8080/q/dev/.

## Related Guides
- SmallRye OpenAPI ([guide](https://quarkus.io/guides/openapi-swaggerui)): Document your REST APIs with OpenAPI - comes with Swagger UI
- RESTEasy Reactive ([guide](https://quarkus.io/guides/resteasy-reactive)): A Jakarta REST implementation utilizing build time processing and Vert.x. This extension is not compatible with the quarkus-resteasy extension, or any of the extensions that depend on it.

# Commands to generate private/public keys for JWT.
1. mkdir jwt
2. openssl genrsa -out jwt/rsaPrivateKey.pem 2048
3. openssl rsa -pubout -in jwt/rsaPrivateKey.pem -out jwt/publicKey.pem
4. openssl pkcs8 -topk8 -nocrypt -inform pem -in jwt/rsaPrivateKey.pem -outform pem -out jwt/privateKey.pem
5. chmod 600 jwt/rsaPrivateKey.pem
6. chmod 600 jwt/privateKey.pem

# Keycloak OAuth2 / OIDC Demo Application

## Description

A Spring Boot application demonstrating Keycloak integration using the OAuth2/OpenID Connect (OIDC) protocols.

The application includes:
- user authentication via Keycloak
- protection of REST and web endpoints using Spring Security
- working with JWT tokens
- storing users in PostgreSQL
- user management via the Keycloak Admin Client

## Quick start Docker Compose

```bash
docker compose up
```
Will be activated:
- Keycloak → `http://localhost:9090`
- PostgreSQL → `localhost:5433`
- Spring Boot App → `http://localhost:8080`
```bash
    security:
    oauth2:
      resourceserver:
        jwt:
          issuer-uri: http://localhost:9090/realms/student
      client:
        provider:
          keycloak:
            issuer-uri: http://localhost:9090/realms/student
            user-name-attribute: preferred_username
        registration:
          keycloak:
            provider: keycloak
            client-id: <client-id>
            client-secret: <client-secret>
            client-name: <client-name>
            authorization-grant-type: authorization_code
            scope: openid, profile, email
```
  
## Configuration Keycloak
1. Realm: `student`
2. Client: `springsecurity`
   - Client ID: `springsecurity`
   - Client Secret: `...`
   - Client Authentication: `client_credentials`
   - Enable `Service Accounts`
   - Add Role: `ROLE_MEMBER`, `ROLE_MODERATOR`, `ROLE_ADMIN`
4. Config mapping `roles` in token (realm roles → claim `roles`)
5. identity providers

## Configuration identity provider(github)
from github takes a client-id, client-secret
```bash
Redirect URI: `http://localhost:9090/realms/student/broker/github/endpoint`
Client ID: <client-id>
Client Secret: <client-secret>
```

## Configuration github(OAuth2)
[developer settings](https://github.com/settings/developers) → OAuth Apps → new OAuth app
```bash
1. Application name: `name`
2. Homepage URL: `http://localhost:8080`
3. Authorization callback URL: `http://localhost:9090/realms/student/broker/github/endpoint`
Store tokens: `True`
Trust Email: `True`
```

## Technology stack
- Java 17
- Spring Boot 3 (Web, Security, OAuth2)
- Keycloak 19
- Docker / Docker Compose
- Maven

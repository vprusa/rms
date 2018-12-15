# README file for web app

## REST API

There are several end points that can be tested for each service/facade:

## Here are some examples of CURLs for each end point: 

List of endpoints with URIs:

```
curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET http://localhost:8080/rms-web/
```

User:

```
curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET http://localhost:8080/rms-web/users
```

Households:

```
curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET http://localhost:8080/rms-web/households
```

Shopping Items:

```
curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET http://localhost:8080/rms-web/shoppingitems
```


Shopping Lists:

```
curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET http://localhost:8080/rms-web/shoppinglists
```

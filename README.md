[![Build Status](https://travis-ci.com/coma123/Spring-Boot-Blog-REST-API.svg?branch=development)](https://travis-ci.com/coma123/Spring-Boot-Blog-REST-API) [![Sonarcloud Status](https://sonarcloud.io/api/project_badges/measure?project=coma123_Spring-Boot-Blog-REST-API&metric=alert_status)](https://sonarcloud.io/dashboard?id=coma123_Spring-Boot-Blog-REST-API) [![CII Best Practices](https://bestpractices.coreinfrastructure.org/projects/3706/badge)](https://bestpractices.coreinfrastructure.org/projects/3706)

# Mini Uber
![logo](./img/back.png)
NUBER is a ride sharing that hires independent contractors as drivers.

# Table of contents
- [Introduction](#introduction)
- [Talk for each service](#talk-for-each-service)
  - [Customer service](#customer-service)
  - [Driver serivce](#driver-service)
  - [Driver location service](#)
  - [Api gateway service](#)
  - [Config service](#)
  - [Payment service](#)
  - [Wallet service](#)
  - [Rating service](#)
- [Setup On Mac](#setup-on-mac)
- [Even Driven](#)
  - [Rabbit MQ](#)
- [Docker](#docker)
  - [Build](#build)
  - [Run locally](#run-locally)

### Customers

- Customer Details Payload
```json
{
    "customerId": "91d41932-62fb-4a91-9494-e6431f7757b0",
    "firstName": "Test",
    "lastName": "Test",
    "email": "anas.abbal10@gmail.com",
    "driverLocationDto": {
        "id": "b831f19b-e42f-4f19-a276-5756b592442e",
        "deleted": false,
        "active": true,
        "driverId": "91d41932-62fb-4a91-9494-e6431f7757b0",
        "name": "Driver",
        "available": true,
        "carId": null,
        "locationEntities": [
            {
                "id": "f05c7f3c-b921-4757-b034-b59c38361671",
                "active": null,
                "geoIp": {
                    "id": "7c72385c-d537-458d-ab06-5272b66313f9",
                    "ipAddress": "128.101.101.101",
                    "country": "United States",
                    "city": "Minneapolis",
                    "latitude": "44.8769",
                    "longitude": "-93.2535"
                }
            }
        ]
    },
    "bankAccount": {
        "id": "df2f244b-8063-43e8-8bd9-9326482dcfbd",
        "userId": "91d41932-62fb-4a91-9494-e6431f7757b0",
        "type": "SAVINGS_ACCOUNT",
        "status": "ACTIVE"
    },
    "walletDetails": {
        "id": "2688c99a-bfa9-4344-903e-667860061910",
        "createdAt": "2023-03-15T19:21:37.120503",
        "updatedAt": "2023-03-15T19:21:37.120503",
        "updatedBy": "NAS",
        "deleted": false,
        "active": true,
        "accountId": "df2f244b-8063-43e8-8bd9-9326482dcfbd",
      "creditCards": [
        {
          "id": "ad12862d-3154-4a10-8c33-478631f4c85a",
          "createdBy": "NAS",
          "holdName": "Anas",
          "number": "2000-2000-2000-2000",
          "expirationDate": "12/24",
          "cvv": "180"
        }
      ],
      "payments": [
        {
          "id": "ad12862d-3154-4a10-8c33-478631f4c85a",
          "createdAt": "2023-03-15T19:46:49.352771",
          "createdBy": "NAS",
          "updatedAt": "2023-03-15T19:46:49.352771",
          "updatedBy": "NAS",
          "amount": null,
          "creditCard": null,
          "paymentStatus": null,
          "barCode": null,
          "paymentType": null
        }
      ]
    }
}
```


| Method | Url                               | Description                                                                           | Sample Valid Request Body |
|--------|-----------------------------------|---------------------------------------------------------------------------------------| ------------------------- |
| POST   | /v1/customers                     | Create custmer                                                                        |[JSON](#usercreate) |
| GET    | /v1/customers/{customerId}        | Get customer profile by id                                                            |[JSON](#usercreate) |
| UPDATE | /v1/customers/{customerId}        | Update customer with id                                                               | [JSON](#usercreate)|
| GET    | /v1/customers/criteria            | Get customers by criteria                                                             | [JSON](#usercreate)|
| POST   | /v1/customers/send-request        | send request for available driver                                                     | [JSON](#usercreate)|

### Driver
| Method | Url                    | Description              | Sample Valid Request Body |
|--------|------------------------|--------------------------| ------------------------- |
| POST   | /v1/driver             | Create driver            |[JSON](#usercreate) |
| GET    | /v1/driver/{driverId}  | Get driver profile by id |[JSON](#usercreate) |
| UPDATE | /v1/driver/{driverId}  | Update driver with id    | [JSON](#usercreate)|
| GET    | /v1/customers/criteria | Get driver by criteria   | [JSON](#usercreate)|
| POST   | /v1/driver/accept-request   | accept request from customer    | [JSON](#usercreate)|


# Introduction
# Talk for Each service
##### Customer Service
- create account in Nuber
- update profile
- get available drivers
- send request to driver
- payment
- find job from Nuber carriers
- earn money from sending link to another client that create account in Nuber
- post rating for driver

##### Driver Service
# Setup On Mac

```
SSH: git@github.com:anasabbal/mini-uber-microservice.git
HTTPS: https://github.com/anasabbal/mini-uber-microservice.git
cd mini-uber-microservice
mvn clean install compile
mvn spring-boot:run
```
```
brew install update
```

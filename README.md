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

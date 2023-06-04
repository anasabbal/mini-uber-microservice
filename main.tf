provider "azurerm" {
  features {}
}

resource "azurerm-resource-group" "microservice" {
  name     = "microservice"
  location = "West Europe"
}

resource "azurerm_spring_cloud_app" "nuber-microservice" {
  name                = "nuber-microservice"
  resource_group_name = azurerm-resource-group.microservice.name
  service_name        = azurerm_spring_cloud_service.customer-service.name

  identity {
    type = "SystemAssigned"
  }
}

resource "azurerm_spring_cloud_service" "customer-service" {
  name                = "customer-service"
  resource_group_name = azurerm-resource-group.microservice.name
  location            = azurerm-resource-group.microservice.location
}

resource "azurerm_spring_cloud_service" "driver-service" {
  location            = azurerm-resource-group.microservice.location
  name                = "driver-service"
  resource_group_name = azurerm-resource-group.microservice.location
}


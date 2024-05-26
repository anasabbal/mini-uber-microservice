terraform {
  required_providers {
    google = {
      source  = "hashicorp/google"
      version = "4.51.0"
    }
  }
}

provider "google" {
  project = "intense-slice-423013-h9"
}

resource "google_compute_network" "vpc_network" {
  name = "terraform-network"
}

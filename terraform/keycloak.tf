provider "keycloak" {
  client_id = "admin-cli"
  username  = "admin"
  password  = var.KEYCLOAK_ADMIN_PASSWORD
  url       = "https://keycloak.devsec-keycloak.mfuchs.dev"
}

plugins {
  id("java")
}

group = "de.dpaii"
version = "0.0.1-SNAPSHOT"

repositories {
  mavenCentral()
}

dependencies {
  val keycloakVersion = "19.0.1"
  implementation("org.keycloak:keycloak-server-spi:$keycloakVersion")
  implementation("org.keycloak:keycloak-server-spi-private:$keycloakVersion")
  implementation("org.keycloak:keycloak-services:$keycloakVersion")
}

tasks.jar {
  setProperty("archiveFileName", "keycloak-last-login-event-listener.jar")
}

java {
  sourceCompatibility = JavaVersion.VERSION_11
  targetCompatibility = JavaVersion.VERSION_11
}

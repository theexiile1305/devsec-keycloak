package dev.mfuchs.devseckeycloak

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DevSecKeycloakApplication

fun main(args: Array<String>) {
	runApplication<DevSecKeycloakApplication>(*args)
}

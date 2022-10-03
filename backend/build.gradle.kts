import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootBuildImage

plugins {
	id("org.springframework.boot") version "2.7.4"
	id("io.spring.dependency-management") version "1.0.14.RELEASE"

	idea

	val kotlinVersion = "1.7.20"
	kotlin("jvm") version kotlinVersion
	kotlin("plugin.spring") version kotlinVersion
	kotlin("plugin.jpa") version kotlinVersion

	id("io.gitlab.arturbosch.detekt") version "1.21.0"
	id("org.jlleitschuh.gradle.ktlint") version "11.0.0"
}

group = "dev.mfuchs"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

dependencies {
	val springBootVersion = "2.7.4"
	implementation("org.springframework.boot:spring-boot-starter-actuator:$springBootVersion")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa:$springBootVersion")
	implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server:$springBootVersion")
	implementation("org.springframework.boot:spring-boot-starter-security:$springBootVersion")
	implementation("org.springframework.boot:spring-boot-starter-web:$springBootVersion")
	developmentOnly("org.springframework.boot:spring-boot-devtools:$springBootVersion")
	val kotlinVersion = "1.7.20"
	implementation("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.4")
	implementation("org.postgresql:postgresql:42.5.0")
	val springDocOpenApiVersion = "1.6.11"
	implementation("org.springdoc:springdoc-openapi-ui:$springDocOpenApiVersion")
	implementation("org.springdoc:springdoc-openapi-webmvc-core:$springDocOpenApiVersion")
	implementation("org.springdoc:springdoc-openapi-security:$springDocOpenApiVersion")
	implementation("org.springdoc:springdoc-openapi-kotlin:$springDocOpenApiVersion")
	implementation("org.flywaydb:flyway-core:9.4.0")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.named<BootBuildImage>("bootBuildImage") {
	isPublish = true
	docker {
		publishRegistry {
			username = System.getenv("CI_REGISTRY_USER")
			password = System.getenv("CI_REGISTRY_PASSWORD")
		}
	}
}
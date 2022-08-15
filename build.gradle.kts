import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.10"
    groovy
}

group = "tech.stephenlowery"
version = "0.1"

repositories {
    mavenCentral()
    maven(url = "https://jitpack.io")
}

dependencies {
    testImplementation(kotlin("test"))
    testImplementation("org.spockframework.spock:spock-core:spock-2.1")
    implementation("io.github.kotlin-telegram-bot.kotlin-telegram-bot:telegram:6.0.7")
    implementation("io.ktor:ktor-server-netty:1.6.3")
}

tasks.test {
    useJUnitPlatform()
}

tasks.create("stage") {
    dependsOn("build", "clean")
    mustRunAfter("clean")
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "tech.stephenlowery.MainKt"
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

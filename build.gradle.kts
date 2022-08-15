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

task<Jar>("fatJar") {
    manifest {
        attributes(mapOf("Main-Class" to "tech.stephenlowery.swolhallabot.MainKt"))
    }
    from(configurations.runtimeClasspath
        .get()
        .filter { it.exists() }
        .map { if (it.isDirectory) it else zipTree(it) }
    )
    with(tasks["jar"] as CopySpec)

    duplicatesStrategy = DuplicatesStrategy.INCLUDE
}

tasks.create("stage") {
    dependsOn("clean", "fatJar")
    mustRunAfter("clean")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}
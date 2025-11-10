plugins {
    kotlin("jvm") version "1.9.0"
    application
    id("org.jlleitschuh.gradle.ktlint") version "11.3.2"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.2")
    //testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.2")
    //testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.2")
}

application {
    mainClass.set("org.example.MainKt")
}

tasks {
    test {
        useJUnitPlatform()
        testLogging {
            events("passed", "skipped", "failed")
        }
    }
}

ktlint {
    version.set("0.50.0")
    verbose.set(true)
    outputToConsole.set(true)

    filter {
        exclude("**/test/**")
        exclude("**/*Test.kt")
        exclude("**/*Spec.kt")
    }
}

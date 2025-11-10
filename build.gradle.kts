plugins {
    kotlin("jvm") version "1.9.23" // Обновлено
    application
    id("org.jlleitschuh.gradle.ktlint") version "12.1.1" // Обновлено
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib") // Уточнено
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.2")
}

application {
    mainClass.set("org.example.MainKt")
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}
// Добавьте этот блок в корень build.gradle.kts
kotlin {
    jvmToolchain(21)  // Явно указываем JDK 21
}
ktlint {
    verbose.set(true)
    outputToConsole.set(true)
    filter {
        exclude("**/test/**")
        exclude("**/*Test.kt")
        exclude("**/*Spec.kt")
    }
}

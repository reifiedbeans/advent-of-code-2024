plugins {
    kotlin("jvm") version "2.0.21"

    id("org.jlleitschuh.gradle.ktlint") version "12.1.2"
}

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain(21)
}

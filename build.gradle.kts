import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.net.URI

plugins {
    kotlin("jvm") version "1.6.21"
    application
    id ("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "com.deviseworks"
version = "1.0-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

val mcver = "1.18.2"

repositories {
    mavenCentral()
    maven {
        url = URI("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    }
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("com.google.guava:guava:31.1-jre")
    compileOnly("org.spigotmc:spigot-api:${mcver}-R0.1-SNAPSHOT")
}

application {
    // Define the main class for the application.
    mainClass.set("com.deviseworks.inspecthorse.AppKt")
}

tasks.withType<KotlinCompile>{
    kotlinOptions{
        jvmTarget = "17"
    }
}

tasks.withType<ProcessResources>{
    val props = mapOf("version" to version)

    inputs.properties(props)
    filteringCharset = "UTF-8"
    filesMatching("plugin.yml"){
        expand(props)
    }
}
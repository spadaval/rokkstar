import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.10"
    application
}

application {
    mainClass.set("net.ascheja.rokkstar.rokkstarKt")
}

val startScripts: CreateStartScripts by tasks
startScripts.applicationName = "rokkstar"

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(project(":interpreter"))
    implementation(project(":parser"))
    implementation(project(":typesystem"))
    implementation(project(":ast"))

    testImplementation("junit:junit:4.13.2")
}
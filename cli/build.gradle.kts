plugins {
    kotlin("jvm") version "1.6.10"
    application
}

application {
    mainClass.set("net.ascheja.rokkstar.RokkstarKt")
}

val startScripts: CreateStartScripts by tasks
startScripts.applicationName = "rokkstar"



dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(project(":modules:interpreter"))
    implementation(project(":modules:parser"))
    implementation(project(":modules:typesystem"))
    implementation(project(":modules:ast"))

    testImplementation("junit:junit:4.13.2")
}
plugins {
    kotlin("jvm") version "1.6.10"
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(project(":modules:ast"))
    testImplementation("junit:junit:4.12")
}

plugins {
    kotlin("jvm")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(project(":ast"))
    testImplementation("junit:junit:4.12")
}

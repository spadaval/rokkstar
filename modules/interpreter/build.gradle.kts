plugins {
    kotlin("multiplatform") version "1.6.10"
}

kotlin {
    jvm()
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":modules:ast"))
                implementation(project(":modules:typesystem"))
                implementation("io.arrow-kt:arrow-core:1.0.1")
            }
        }
    }
}
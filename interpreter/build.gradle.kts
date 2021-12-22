plugins {
    kotlin("multiplatform") version "1.6.10"
}

kotlin {
    jvm()
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":ast"))
                implementation(project(":typesystem"))
                implementation("io.arrow-kt:arrow-core:1.0.1")
            }
        }
    }
}
plugins {
    kotlin("multiplatform")
}

kotlin {
    jvm()
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":ast"))
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}
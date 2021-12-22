plugins {
    kotlin("multiplatform") version "1.6.10"
}

kotlin {
    jvm()
    sourceSets {
        val commonMain by getting {
            kotlin.srcDir("src")
            dependencies {
                implementation(kotlin("stdlib-common"))
            }
        }
    }
}


plugins {
    kotlin("multiplatform")
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


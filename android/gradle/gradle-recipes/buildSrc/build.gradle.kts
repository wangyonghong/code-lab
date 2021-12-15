plugins {
    // 此处必须写明版本号
    kotlin("jvm") version "1.6.0"
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
    jcenter()
}

dependencies {
    implementation("com.android.tools.build:gradle:7.0.4")
    implementation(kotlin("stdlib"))
}
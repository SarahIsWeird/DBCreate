import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.31"
    id("org.jetbrains.compose") version "1.0.0-beta1"
}

group = "com.sarahisweird"
version = "1.0"

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
}

val exposedVersion: String by project

dependencies {
    implementation(compose.desktop.currentOs)

    implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")

    implementation("org.postgresql:postgresql:42.2.2")
    implementation("mysql:mysql-connector-java:5.1.48")
    implementation("org.xerial:sqlite-jdbc:3.30.1")
    implementation("com.microsoft.sqlserver:mssql-jdbc:6.4.0.jre7")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "16"
}

compose.desktop {
    application {
        mainClass = "com.sarahisweird.dbcreate.MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "DBCreate"
            packageVersion = "1.0.0"
        }
    }
}
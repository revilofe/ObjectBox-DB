import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


plugins {
    kotlin("jvm") version "1.6.10"
    application
}

group = "me.usuario"
version = "1.0-SNAPSHOT"

val objectboxVersion by extra ("3.1.2")

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("io.objectbox:objectbox-gradle-plugin:$objectboxVersion") //Gradle Plugin for ObjectBox (NoSQL for Objects)
    implementation("io.objectbox:objectbox-linux:$objectboxVersion") //add Linux platform dependencies to your project’s
    annotationProcessor("io.objectbox:objectbox-processor:$objectboxVersion") //dependencies to the ObjectBox Java API and annotation processor are added by default by the ObjectBox Gradle plugin

}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "11"
}

application {
    mainClass.set("MainKt")
}


//apply(plugin = "io.objectbox")

tasks.withType<JavaCompile>() {
    options.compilerArgs.add("-Aobjectbox.debug=true")
}




/**
# app/build.gradle
plugins {
    id 'com.android.application'
    id 'kotlin-android'
    id 'io.objectbox'
}
**/


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
    //implementation("android.arch.persistence.room:runtime:1.1.1")
    testImplementation(kotlin("test"))
    implementation("io.objectbox:objectbox-java:$objectboxVersion")
    implementation("io.objectbox:objectbox-gradle-plugin:$objectboxVersion") //Gradle Plugin for ObjectBox (NoSQL for Objects)
    implementation("io.objectbox:objectbox-linux:$objectboxVersion") //add Linux platform dependencies to your project’s
    //annotationProcessor("io.objectbox:objectbox-processor:$objectboxVersion")
    //annotationProcessor("android.arch.persistence.room:compiler:1.1.1") //dependencies to the ObjectBox Java API and annotation processor are added by default by the ObjectBox Gradle plugin
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

tasks.withType<JavaCompile>() {
    options.compilerArgs.add("-Aobjectbox.debug=true")
}


// apply the plugin after the dependencies block so it does not automatically add objectbox-android
// which would conflict with objectbox-android-objectbrowser on debug builds
//apply(plugin = "io.objectbox")
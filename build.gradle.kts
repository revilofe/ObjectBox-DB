import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

plugins {
    kotlin("jvm") version "1.6.10"
    kotlin("kapt") version "1.6.10"
    //kotlin("io.objectbox") version "3.1.2"
    //id("io.objectbox")
    application

}

group = "me.usuario"
version = "1.0-SNAPSHOT"

val objectboxVersion by extra ("3.1.2")

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.objectbox:objectbox-gradle-plugin:$objectboxVersion") //Gradle Plugin for ObjectBox (NoSQL for Objects)

    // some useful Kotlin extension functions
    implementation("io.objectbox:objectbox-kotlin:$objectboxVersion")
    implementation("io.objectbox:objectbox-java:$objectboxVersion")

    implementation("io.objectbox:objectbox-linux:$objectboxVersion") //add Linux platform dependencies to your project’s
    //implementation("io.objectbox:objectbox-android:$objectboxVersion")
    annotationProcessor("io.objectbox:objectbox-processor:$objectboxVersion")
    //object box
    //implementation("io.objectbox:objectbox-android:$objectboxVersion")

    testImplementation(kotlin("test"))

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

//Optional: Change the Model File Path
tasks.withType<JavaCompile>() {
    options.compilerArgs.add("-Aobjectbox.modelPath=$projectDir/schemas/objectbox.json")
}

//Optional: Change the MyObjectBox package
tasks.withType<JavaCompile>() {
    options.compilerArgs.add("-Aobjectbox.myObjectBoxPackage=com.example.project")
}


// apply the plugin after the dependencies block so it does not automatically add objectbox-android
// which would conflict with objectbox-android-objectbrowser on debug builds
apply(plugin = "io.objectbox")


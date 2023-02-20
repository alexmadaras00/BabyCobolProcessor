import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.21"
    application
    antlr
}

configure<SourceSetContainer> {
    named("main") {
        java.srcDir("src/main/gen")
        kotlin.srcDir("src/main/kotlin")
    }
}

group = "me.lxm"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.antlr:antlr4:4.11.1")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    dependsOn(tasks.generateGrammarSource)
    kotlinOptions.jvmTarget = "1.8"
}

tasks.generateGrammarSource {
    outputDirectory = File("src/main/gen")
}

application {
    mainClass.set("MainKt")
}
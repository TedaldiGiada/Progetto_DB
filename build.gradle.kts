plugins {
    java
    application
    id("com.github.johnrengelman.shadow") version "8.1.1" // per fat jar
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.mysql:mysql-connector-j:9.3.0")
    implementation("org.apache.commons:commons-lang3:3.17.0")
    implementation("com.google.guava:guava:33.4.8-jre")
    implementation("org.slf4j:slf4j-api:2.0.16")
    runtimeOnly("ch.qos.logback:logback-classic:1.5.12")

    testImplementation("org.assertj:assertj-core:3.27.3")
    val jUnitVersion = "5.11.3"
    testImplementation("org.junit.jupiter:junit-jupiter-api:$jUnitVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$jUnitVersion")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

application {
    mainClass.set("agricola.app.App") // package completo della classe main
}

tasks.shadowJar {
    archiveBaseName.set("Progetto_DB")
    archiveVersion.set("")
    archiveClassifier.set("") // rimuove il "-all" dal nome
    manifest {
        attributes["Main-Class"] = "agricola.app.App" // package completo
    }
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events(*org.gradle.api.tasks.testing.logging.TestLogEvent.values())
        showStandardStreams = true
    }
}
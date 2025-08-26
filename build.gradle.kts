plugins {
    java
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation ("com.mysql:mysql-connector-j:8.3.0")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.0")
}

application {
    mainClass.set("agriterra.App")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

tasks.test {
    useJUnitPlatform()
}
plugins {
}

group = "com.thomas-driscoll"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
}

tasks.bootJar{
    enabled = false
}

tasks.jar{
    enabled = true
}
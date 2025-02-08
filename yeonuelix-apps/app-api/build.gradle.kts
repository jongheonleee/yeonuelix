dependencies {
    implementation(project(":yeonuelix-core:core-usecase"))
    implementation("org.springframework.boot:spring-boot-starter-web")

    implementation(project(":yeonuelix-core:core-service"))
    implementation("org.springframework.boot:spring-boot-starter-actuator") // implementation -> runtimeOnly 직접 바라보면 안되기 때문에
}


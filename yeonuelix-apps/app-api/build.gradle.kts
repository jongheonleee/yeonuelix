dependencies {
    implementation(project(":yeonuelix-core:core-usecase"))
    implementation("org.springframework.boot:spring-boot-starter-web")

    runtimeOnly(project(":yeonuelix-core:core-service")) // implementation -> runtimeOnly 직접 바라보면 안되기 때문에
}


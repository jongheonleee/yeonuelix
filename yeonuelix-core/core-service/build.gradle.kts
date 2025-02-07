dependencies {
    implementation(project(":yeonuelix-core:core-usecase"))
    implementation(project(":yeonuelix-core:core-port"))
    implementation("org.springframework:spring-context")

    runtimeOnly(project(":yeonuelix-adapters:adapter-http"))
}
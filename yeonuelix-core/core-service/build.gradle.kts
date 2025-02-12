dependencies {
    implementation(project(":yeonuelix-core:core-usecase"))
    implementation(project(":yeonuelix-core:core-port"))
    implementation(project(":yeonuelix-commons"))
    implementation(project(":yeonuelix-core:core-domain"))

    implementation("org.springframework:spring-context")
    implementation("org.springframework.data:spring-data-commons")

    // 밑에 부분 추후에 runtimeOnly로 변경
    implementation(project(":yeonuelix-adapters:adapter-http"))
    implementation(project(":yeonuelix-adapters:adapter-persistence"))
    implementation(project(":yeonuelix-adapters:adapter-redis"))
}
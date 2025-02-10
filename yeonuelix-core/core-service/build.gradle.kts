dependencies {
    implementation(project(":yeonuelix-core:core-usecase"))
    implementation(project(":yeonuelix-core:core-port"))
    implementation("org.springframework:spring-context")

    // 밑에 부분 추후에 runtimeOnly로 변경
    implementation(project(":yeonuelix-adapters:adapter-http"))
    implementation(project(":yeonuelix-adapters:adapter-persistence"))
}
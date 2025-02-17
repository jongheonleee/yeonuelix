dependencies {
    implementation(project(":yeonuelix-core:core-usecase"))
    implementation(project(":yeonuelix-core:core-domain"))
    implementation(project(":yeonuelix-commons"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    implementation("org.springframework.boot:spring-boot-starter-batch")
    implementation("org.springframework:spring-tx")

    runtimeOnly(project(":yeonuelix-core:core-service"))
}
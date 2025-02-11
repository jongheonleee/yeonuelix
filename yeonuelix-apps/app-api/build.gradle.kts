dependencies {
    implementation(project(":yeonuelix-core:core-usecase"))
    implementation(project(":yeonuelix-core:core-service"))
    implementation(project(":yeonuelix-commons"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator") // implementation -> runtimeOnly 직접 바라보면 안되기 때문에

    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-aop")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
    implementation("org.springframework.security:spring-security-oauth2-client")

}

val appMainClassName = "yeo.nuel.lix.YeonuelixApplication"
tasks.getByName<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
    mainClass.set(appMainClassName)
    archiveClassifier.set("boot")
}


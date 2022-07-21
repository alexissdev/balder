plugins {
    id("balder.testing-conventions")
}

dependencies {
    implementation(project(":api"))
    implementation("org.apache.httpcomponents:httpclient:4.5.7")
    implementation("com.google.code.gson:gson:2.9.0")
}
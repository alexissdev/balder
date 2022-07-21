plugins {
    id("java")
    id("balder.common-conventions")
}

subprojects {
    apply(
        plugin = "balder.common-conventions"
    )

    dependencies {
        api("org.jetbrains:annotations:16.0.2")
    }
}
plugins {
    id("java")
    id("balder.common-conventions")
}

subprojects {
    apply(
        plugin = "balder.common-conventions"
    )
    apply(
        plugin = "balder.publishing-conventions"
    )

    dependencies {
        api("org.jetbrains:annotations:16.0.2")
    }
}
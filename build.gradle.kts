buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath ("com.android.tools.build:gradle:${App.gradlePlugin}")
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:${App.kotlin}")
        classpath("de.mannodermaus.gradle.plugins:android-junit5:${App.junit5}")
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
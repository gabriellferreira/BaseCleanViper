plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("kotlin-android")
    id("de.mannodermaus.android-junit5")
    id("com.apollographql.apollo").version(Versions.apollo)
}

android {
    compileSdkVersion(App.compileSdkVersion)
    buildToolsVersion(App.buildToolsVersion)

    defaultConfig {
        applicationId = "com.gabriellferreira.basecleanviper"
        minSdkVersion(App.minSdkVersion)
        targetSdkVersion(App.targetSdkVersion)
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "BASE_GRAPHQL_URL", "\"https://graphqlzero.almansi.me/api\"")
        buildConfigField("String", "BASE_REST_URL", "\"base_url\"")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
    }

    testOptions{
        junitPlatform{
            filters {
                includeEngines("spek2")
            }
        }
    }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${App.kotlin}")
    implementation("androidx.appcompat:appcompat:${Versions.Androidx.appcompat}")
    implementation("androidx.core:core-ktx:${Versions.Androidx.core}")
    implementation("androidx.recyclerview:recyclerview:${Versions.Androidx.recyclerView}")
    implementation("androidx.security:security-crypto:${Versions.Androidx.securityCrypto}")
    implementation("androidx.paging:paging-runtime-ktx:${Versions.Androidx.paging}")

    // Apollo
    implementation("com.apollographql.apollo:apollo-runtime:${Versions.apollo}")
    implementation("com.apollographql.apollo:apollo-normalized-cache-sqlite:${Versions.apollo}")
    implementation("com.apollographql.apollo:apollo-coroutines-support:${Versions.apollo}")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}")

    // Dagger
    implementation("com.google.dagger:dagger:${Versions.dagger2}")
    kapt("com.google.dagger:dagger-compiler:${Versions.dagger2}")
    kaptTest("com.google.dagger:dagger-compiler:${Versions.dagger2}")

    // Retrofit and OkHttp
    implementation("com.squareup.retrofit2:retrofit:${Versions.retrofit}")
    implementation("com.squareup.retrofit2:converter-gson:${Versions.retrofit}")
    implementation("com.squareup.okhttp3:logging-interceptor:${Versions.loggingInterceptor}")

    // Testing
    testImplementation("org.spekframework.spek2:spek-dsl-jvm:${Versions.spek}")
    testImplementation("org.spekframework.spek2:spek-runner-junit5:${Versions.spek}")
    testImplementation("org.jetbrains.kotlin:kotlin-reflect:${App.kotlin}")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutinesTest}")
    testImplementation("com.google.truth:truth:${Versions.truth}")
    testImplementation("io.mockk:mockk:${Versions.mockk}")
    testImplementation("androidx.arch.core:core-testing:${Versions.coreTesting}")
}

apollo {
    generateKotlinModels.set(true)
    service("mainSchema") {
        rootPackageName.set("com.gabriellferreira.basecleanviper")
        schemaPath.set("./schema.json")
    }
}

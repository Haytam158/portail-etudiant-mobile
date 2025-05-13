import com.android.build.api.dsl.Packaging


plugins {

    alias(libs.plugins.android.application)
    id("org.jetbrains.kotlin.kapt")
}

android {
    namespace = "com.portail.etudiant"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.portail.etudiant"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        javaCompileOptions {
            annotationProcessorOptions {
                arguments["room.schemaLocation"] = "$projectDir/schemas"

            }
        }

        packagingOptions {
            resources {
                excludes += "META-INF/LICENSE.md"
                excludes += "META-INF/LICENSE-notice.md" // Add more if needed
            }
        }

    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.ext.junit)

    testImplementation(libs.junit)
    testImplementation(libs.room.testing)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(libs.junit.v115)
    androidTestImplementation(libs.runner)



    // Room for Java
    implementation(libs.room.runtime)
    annotationProcessor(libs.room.compiler)

    // Optional: LiveData support (if needed)
    implementation(libs.lifecycle.livedata)


    // GridLayout
    implementation(libs.gridlayout)

}
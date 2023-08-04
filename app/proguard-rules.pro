# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# Add this global rule
-keepattributes Signature

# This rule will properly ProGuard all the model classes in
# the package com.yourcompany.models.
# Modify this rule to fit the structure of your app.
-keepclassmembers class com.aireadevs.megagifs.** {
  *;
}

# Please add these rules to your existing keep rules in order to suppress warnings.
# This is generated automatically by the Android Gradle plugin.
# ***** Ya no es necesario con la version com.squareup.okhttp3:okhttp:4.11.0 *****
#-dontwarn org.bouncycastle.jsse.BCSSLParameters
#-dontwarn org.bouncycastle.jsse.BCSSLSocket
#-dontwarn org.bouncycastle.jsse.provider.BouncyCastleJsseProvider
#-dontwarn org.conscrypt.Conscrypt$Version
#-dontwarn org.conscrypt.Conscrypt
#-dontwarn org.conscrypt.ConscryptHostnameVerifier
#-dontwarn org.openjsse.javax.net.ssl.SSLParameters
#-dontwarn org.openjsse.javax.net.ssl.SSLSocket
#-dontwarn org.openjsse.net.ssl.OpenJSSE

#********************** gms auth *********************************************

-keep class com.google.android.gms.auth.** { *; }

#*********************** Application *****************************************

-keep class android.app.Application{
    public <fields>;
    private <fields>;
    public <methods>; }
-keep public class * extends android.app.Application

#*********************** Google API *****************************************

-keep class com.google.** { *;}
-keep interface com.google.** { *;}
-dontwarn com.google.**
-keep class com.google.api.client.** { *; }
#-keepclassmembers class * { @com.google.api.client.util.Key <fields>; }
-keepattributes Signature,RuntimeVisibleAnnotations,AnnotationDefault
# Needed by google-api-client-android when linking against an older platform version
-dontwarn com.google.api.client.googleapis.extensions.android.**
# Needed by google-http-client-android when linking against an older platform version
-dontwarn com.google.api.client.extensions.android.**
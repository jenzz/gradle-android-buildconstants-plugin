Android Build Constants Plugin [![Build Status](https://travis-ci.org/jenzz/gradle-android-buildconstants-plugin.svg?branch=master)](https://travis-ci.org/jenzz/gradle-android-buildconstants-plugin)
==============================
A Gradle plugin for Android which generates both Java and XML constants as part of the build process.

Why do I need this?
===================
In short: To define constants that are accessible from everywhere in your Android app.

In long: There are two ways to define constants in Android. Both have their limitations.
* Creating constants using `buildConfigField` only adds entries to `BuildConfig.java` which means you cannot access them via XML.
* Creating a custom `constants.xml` resource file with entries like `<string name="constant">constant_value</string>`
lets you easily reference the constant via `@string/constant` in XML, but requires you to have a `Context` object on the Java side
to get the value using `getResources().getString(R.string.constant)`.

None of the above solutions is ideal. This plugin lets you specify constants in your `build.gradle` file
that will be translated into both a Java and an XML-based version with the same constant value.

How does it work?
=================
The plugin creates a new task per build type, e.g. *generateDebugBuildConstants*.
It hooks into the build process ahead of the *processDebugResources* task so it will be executed every time you assemble your project.
It supports incremental builds, i.e. if none of the inputs or outputs have changed, Gradle can skip the task (up-to-date).

Usage
-----
The plugin currently supports **Integer**, **Boolean** and **String** data types.

You can specify the constants using a closure in your `build.gradle` file like this:

```groovy
buildConstants {
  constants {
    aBoolean = true
    aString = 'string'
    aNumber = 123
  }
}
```

The plugin will then generate both a Java and an XML version of the constants like so:

**Java:**
```java
public final class BuildConstants {
  
  public static final boolean ABOOLEAN = true;
  public static final String ASTRING = "string";
  public static final int ANUMBER = 123;
  
  private BuildConstants() {
    throw new AssertionError("No instances.");
  }
}
```

**XML:**
```xml
<resources>
	<bool name="aboolean">true</bool>
	<string name="astring">string</string>
	<integer name="anumber">123</integer>
</resources>
```

Alternatively, you can define the constants using a map:

```groovy
buildConstants {
  constants = [
    aBoolean : true,
    aString : 'string',
    aNumber : 123
  ]
}
```

Or specify just a single constant (similar to `buildConfigField`):

```groovy
buildConstants {
  constant 'single_constant', 'single_string'
}
```

The default generated file names are `BuildConstants.java` and `build_constants.xml`.
You can change them like this:

```groovy
buildConstants {
  javaFileName 'SampleBuildConstants'
  xmlFileName 'sample_build_constants'
}
```

Example
-------
Check out the [sample project](https://github.com/jenzz/gradle-android-buildconstants-plugin/tree/master/sample) for an example implementation.

Download
--------
Build script snippet for use in all Gradle versions:

```groovy
buildscript {
  repositories {
    maven {
      url 'https://plugins.gradle.org/m2/'
    }
  }
  dependencies {
    classpath 'gradle.plugin.com.jenzz:buildconstants:1.1.0'
  }
}

apply plugin: 'com.jenzz.buildconstants'
```

Build script snippet for new, incubating, plugin mechanism introduced in Gradle 2.1:

```groovy
plugins {
  id 'com.jenzz.buildconstants' version '1.1.0'
}
```

License
-------
This project is licensed under the [MIT License](https://raw.githubusercontent.com/jenzz/gradle-android-buildconstants-plugin/master/LICENSE).
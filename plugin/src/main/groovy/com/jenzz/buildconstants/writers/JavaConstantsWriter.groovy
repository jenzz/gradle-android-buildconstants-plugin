package com.jenzz.buildconstants.writers

import com.android.annotations.NonNull

import static java.util.Locale.ENGLISH

public class JavaConstantsWriter extends BaseConstantsWriter {

  @NonNull private final String packageName
  @NonNull private final String className

  JavaConstantsWriter(@NonNull File file,
                      @NonNull String packageName,
                      @NonNull String className) {
    super(file)
    this.packageName = packageName
    this.className = className
  }

  @NonNull
  @Override
  String writeHeader() {
    //@formatter:off
    "/**" + "\n" +
    " * Automatically generated file. DO NOT MODIFY." + "\n" +
    " * Check your build.gradle for buildConstants to edit these values." + "\n" +
    "*/" + "\n" +
    "package $packageName;" + "\n" +
    "\n" +
    "public final class $className {" + "\n" +
    "\n"
    //@formatter:on
  }

  @NonNull
  @Override
  String writeString(@NonNull Map.Entry<String, Object> constant) {
    write "String", constant.key, "\"$constant.value\""
  }

  @NonNull
  @Override
  String writeInteger(@NonNull Map.Entry<String, Object> constant) {
    write "int", constant.key, constant.value.toString()
  }

  @NonNull
  @Override
  String writeBoolean(@NonNull Map.Entry<String, Object> constant) {
    write "boolean", constant.key, constant.value.toString()
  }

  @NonNull
  @Override
  String writeFooter() {
    //@formatter:off
    "\n" +
    "\t" + "private $className() {" + "\n" +
    "\t" + "\t" + "throw new AssertionError(\"No instances.\");" + "\n" +
    "\t" + "}" + "\n" +
    "}"
    //@formatter:on
  }

  @NonNull
  private static String write(@NonNull String dataType, @NonNull String key, @NonNull String value) {
    "public static final $dataType ${key.toUpperCase(ENGLISH)} = $value;"
  }
}

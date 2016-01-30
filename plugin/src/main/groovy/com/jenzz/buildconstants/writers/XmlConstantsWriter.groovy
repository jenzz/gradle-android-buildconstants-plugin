package com.jenzz.buildconstants.writers

import com.android.annotations.NonNull

import static java.util.Locale.ENGLISH

public class XmlConstantsWriter extends BaseConstantsWriter {

  XmlConstantsWriter(@NonNull File file) {
    super(file)
  }

  @Override
  String writeHeader() {
    //@formatter:off
    "<?xml version=\"1.0\" encoding=\"utf-8\"?>" + "\n" +
    "<resources>" + "\n" +
    "\n" +
      "\t" + "<!-- Automatically generated file. DO NOT MODIFY. -->" + "\n" +
      "\t" + "<!-- Check your build.gradle for buildConstants to edit these values. -->" + "\n" +
    "\n"
    //@formatter:on
  }

  @NonNull
  @Override
  String writeString(@NonNull Map.Entry<String, Object> constant) {
    write "string", constant
  }

  @NonNull
  @Override
  String writeInteger(@NonNull Map.Entry<String, Object> constant) {
    write "integer", constant
  }

  @NonNull
  @Override
  String writeBoolean(@NonNull Map.Entry<String, Object> constant) {
    write "bool", constant
  }

  @NonNull
  @Override
  String writeFooter() {
    //@formatter:off
    "\n" +
    "</resources>"
    //@formatter:on
  }

  @NonNull
  private static String write(@NonNull String dataType, @NonNull Map.Entry<String, Object> constant) {
    "<$dataType name=\"${constant.key.toLowerCase(ENGLISH)}\">$constant.value</$dataType>"
  }
}

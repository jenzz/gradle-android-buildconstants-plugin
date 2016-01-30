package com.jenzz.buildconstants.sanitizers

import org.gradle.api.InvalidUserDataException
import org.junit.Test

import static org.assertj.core.api.Assertions.assertThat

class JavaFileNameSanitizerTest {

  private final FileNameSanitizer sanitizer = new JavaFileNameSanitizer()

  @Test(expected = InvalidUserDataException.class)
  void throwsIfNull() {
    sanitizer.sanitize null
  }

  @Test(expected = InvalidUserDataException.class)
  void throwsIfEmpty() {
    sanitizer.sanitize ""
  }

  @Test
  void capitalizes() {
    assertThat sanitizer.sanitize("fileName") isEqualTo "FileName"
  }

  @Test
  void removesSuffix() {
    assertThat sanitizer.sanitize("FileName.java") isEqualTo "FileName"
  }

  @Test
  void returnsCleanFileName() {
    String fileName = "BuildConstants"
    assertThat sanitizer.sanitize(fileName) isEqualTo fileName
  }
}
package com.jenzz.buildconstants.sanitizers

import org.gradle.api.InvalidUserDataException
import org.junit.Test

import static org.assertj.core.api.Assertions.assertThat

class XmlFileNameSanitizerTest {

  private final FileNameSanitizer sanitizer = new XmlFileNameSanitizer()

  @Test(expected = InvalidUserDataException.class)
  void throwsIfNull() {
    sanitizer.sanitize(null)
  }

  @Test(expected = InvalidUserDataException.class)
  void throwsIfEmpty() {
    sanitizer.sanitize("")
  }

  @Test(expected = InvalidUserDataException.class)
  void throwsIfCapitalized() {
    sanitizer.sanitize("Hello")
  }

  @Test(expected = InvalidUserDataException.class)
  void throwsIfContainsUpperCase() {
    sanitizer.sanitize("hEllo")
  }

  @Test(expected = InvalidUserDataException.class)
  void throwsIfNotAlphanumeric() {
    sanitizer.sanitize("hello-")
  }

  @Test
  void removesSuffix() {
    assertThat sanitizer.sanitize("file_name.xml") isEqualTo "file_name"
  }

  @Test
  void returnsCleanFileName() {
    String fileName = "build_constants"
    assertThat sanitizer.sanitize(fileName) isEqualTo fileName
  }
}
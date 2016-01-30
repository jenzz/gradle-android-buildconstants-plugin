package com.jenzz.buildconstants.sanitizers

import com.android.annotations.NonNull
import com.android.annotations.Nullable
import com.jenzz.buildconstants.categories.StringCategory
import org.gradle.api.InvalidUserDataException
import org.slf4j.Logger

import static org.slf4j.LoggerFactory.getLogger

public class JavaFileNameSanitizer implements FileNameSanitizer {

  private static final String SUFFIX = ".java"
  private static final Logger log = getLogger(JavaFileNameSanitizer.class)

  @NonNull
  @Override
  public String sanitize(@Nullable String fileName) {
    if (fileName == null) {
      throw new InvalidUserDataException("Java file name must not be null.")
    }

    if (fileName.isEmpty()) {
      throw new InvalidUserDataException("Java file name must not be empty.")
    }

    if (fileName.endsWith(SUFFIX)) { // remove potential suffix
      fileName = fileName.substring(0, fileName.length() - SUFFIX.length())
      log.warn("Java file name extension not required. Ignoring " + SUFFIX + " suffix.")
    }

    use(StringCategory) {
      if (fileName.isNotCapitalized()) {
        fileName = fileName.capitalize()
        log.warn("Java file name must be capitalized. Renaming it to \"" + fileName + "\".")
      }
    }

    fileName
  }
}
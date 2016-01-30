package com.jenzz.buildconstants.sanitizers

import com.android.annotations.NonNull
import com.android.annotations.Nullable
import org.gradle.api.InvalidUserDataException
import org.slf4j.Logger

import static org.slf4j.LoggerFactory.getLogger

public class XmlFileNameSanitizer implements FileNameSanitizer {

  private static final String SUFFIX = ".xml"
  private static final Logger log = getLogger(XmlFileNameSanitizer.class)

  @NonNull
  @Override
  public String sanitize(@Nullable String fileName) {
    if (fileName == null) {
      throw new InvalidUserDataException("XML file name must not be null.")
    }

    if (fileName.isEmpty()) {
      throw new InvalidUserDataException("XML file name must not be empty.")
    }

    if (fileName.endsWith(SUFFIX)) { // remove potential suffix
      fileName = fileName.substring 0, fileName.length() - SUFFIX.length()
      log.warn("XML file name extension not required. Ignoring " + SUFFIX + " suffix.")
    }

    if (!fileName.matches("^[a-z0-9_]+\$")) {
      throw new InvalidUserDataException("XML file name must contain only lowercase a-z, 0-9, or _.")
    }

    fileName
  }
}

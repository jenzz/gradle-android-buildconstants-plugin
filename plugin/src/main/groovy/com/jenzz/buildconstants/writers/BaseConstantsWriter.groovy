package com.jenzz.buildconstants.writers

import com.android.annotations.NonNull
import org.gradle.api.InvalidUserDataException;

public abstract class BaseConstantsWriter implements ConstantsWriter {

  @NonNull private final File file

  BaseConstantsWriter(@NonNull File file) {
    this.file = file
  }

  @Override
  void write(@NonNull Map<String, Object> constants) {
    file.withWriter { writer ->
      writer.append writeHeader()
      constants.each {
        String constant;
        switch (it.value.class) {
          case String:
            constant = writeString it
            break
          case Integer:
            constant = writeInteger it
            break
          case Boolean:
            constant = writeBoolean it
            break
        }
        if (constant == null) {
          throw new InvalidUserDataException(
              "Data type is not supported. Only String, Integer and Boolean are allowed.");
        }
        writer.append "\t" + constant + "\n"
      }
      writer.append writeFooter()
    }
  }

  @NonNull
  abstract String writeHeader()

  @NonNull
  abstract String writeString(@NonNull Map.Entry<String, Object> constant)

  @NonNull
  abstract String writeInteger(@NonNull Map.Entry<String, Object> constant)

  @NonNull
  abstract String writeBoolean(@NonNull Map.Entry<String, Object> constant)

  @NonNull
  abstract String writeFooter()
}

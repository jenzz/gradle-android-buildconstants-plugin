package com.jenzz.buildconstants.factories

import com.android.annotations.NonNull

public class XmlFileFactory implements FileFactory {

  @NonNull private final String buildDir
  @NonNull private final String buildTypeName

  XmlFileFactory(@NonNull String buildDir,
                 @NonNull String buildTypeName) {
    this.buildDir = buildDir
    this.buildTypeName = buildTypeName
  }

  @NonNull
  @Override
  File create(@NonNull String fileName) {
    new File("${buildDir}/intermediates/res/merged/${buildTypeName}/values/${fileName}.xml")
  }
}

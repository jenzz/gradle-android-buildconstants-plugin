package com.jenzz.buildconstants.factories

import com.android.annotations.NonNull

public class XmlFileFactory implements FileFactory {

  @NonNull private final String buildDir
  @NonNull private final String variantDir

  XmlFileFactory(@NonNull String buildDir,
                 @NonNull String variantDir) {
    this.buildDir = buildDir
    this.variantDir = variantDir
  }

  @NonNull
  @Override
  File create(@NonNull String fileName) {
    new File("${buildDir}/intermediates/res/merged/${variantDir}/values/${fileName}.xml")
  }
}

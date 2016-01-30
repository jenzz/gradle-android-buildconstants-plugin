package com.jenzz.buildconstants.factories

import com.android.annotations.NonNull
import com.jenzz.buildconstants.mappers.Mapper

public class JavaFileFactory implements FileFactory {

  @NonNull private final String appId
  @NonNull private final String buildDir
  @NonNull private final String buildTypeName
  @NonNull private final Mapper<String, String> appIdToPathMapper

  JavaFileFactory(@NonNull String appId,
                  @NonNull String buildDir,
                  @NonNull String buildTypeName,
                  @NonNull Mapper<String, String> appIdToPathMapper) {
    this.appId = appId
    this.buildDir = buildDir
    this.buildTypeName = buildTypeName
    this.appIdToPathMapper = appIdToPathMapper
  }

  @NonNull
  @Override
  File create(@NonNull String fileName) {
    String appPath = appIdToPathMapper.map appId
    new File("${buildDir}/generated/source/buildConfig/${buildTypeName}/${appPath}/${fileName}.java")
  }
}

package com.jenzz.buildconstants.mappers;

import com.android.annotations.Nullable;

public class AppIdToPathMapper implements Mapper<String, String> {

  @Nullable
  @Override
  public String map(@Nullable String appId) {
    appId?.replaceAll '\\.', '/'
  }
}

package com.jenzz.buildconstants.mappers

import com.android.annotations.Nullable

public interface Mapper<FROM, TO> {

  @Nullable
  TO map(@Nullable FROM from);
}
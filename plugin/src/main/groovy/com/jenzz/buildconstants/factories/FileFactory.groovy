package com.jenzz.buildconstants.factories

import com.android.annotations.NonNull

public interface FileFactory {

  @NonNull
  File create(@NonNull String fileName)
}

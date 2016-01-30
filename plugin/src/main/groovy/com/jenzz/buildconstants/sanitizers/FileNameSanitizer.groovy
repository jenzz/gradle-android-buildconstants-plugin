package com.jenzz.buildconstants.sanitizers

import com.android.annotations.NonNull
import com.android.annotations.Nullable

public interface FileNameSanitizer {

  @NonNull
  String sanitize(@Nullable String fileName);
}
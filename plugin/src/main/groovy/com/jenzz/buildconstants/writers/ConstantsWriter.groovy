package com.jenzz.buildconstants.writers

import com.android.annotations.NonNull

interface ConstantsWriter {

  void write(@NonNull Map<String, Object> constants)
}
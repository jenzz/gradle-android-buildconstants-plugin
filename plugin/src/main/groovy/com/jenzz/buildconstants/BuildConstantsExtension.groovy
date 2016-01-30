package com.jenzz.buildconstants

import com.android.annotations.NonNull

import static groovy.lang.Closure.DELEGATE_FIRST

@SuppressWarnings("GroovyUnusedDeclaration")
public class BuildConstantsExtension {

  String javaFileName = "BuildConstants"
  String xmlFileName = "build_constants"

  Map<String, Object> constants = new LinkedHashMap<>()

  public constants(@NonNull Closure closure) {
    Map<String, Object> tmpConstants = new LinkedHashMap<>()
    closure.delegate = tmpConstants
    closure.resolveStrategy = DELEGATE_FIRST
    closure()
    constants.clear()
    constants.putAll tmpConstants
  }

  public constant(@NonNull String key, @NonNull Object value) {
    constants.put(key, value)
  }
}
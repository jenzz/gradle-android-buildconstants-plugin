package com.jenzz.buildconstants.categories

import static java.lang.Character.isUpperCase

@Category(String)
class StringCategory {

  boolean isCapitalized() {
    isUpperCase charAt(0)
  }

  boolean isNotCapitalized() {
    !isCapitalized()
  }
}

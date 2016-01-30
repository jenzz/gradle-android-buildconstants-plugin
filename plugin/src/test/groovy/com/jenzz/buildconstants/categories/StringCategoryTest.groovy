package com.jenzz.buildconstants.categories

import org.junit.Test

import static org.assertj.core.api.Assertions.assertThat

class StringCategoryTest {

  @Test
  void isCapitalized() {
    use(StringCategory) {
      assertThat "Hello".isCapitalized() isTrue()
      assertThat "hello".isCapitalized() isFalse()
    }
  }

  @Test
  void isNotCapitalized() {
    use(StringCategory) {
      assertThat "Hello".isNotCapitalized() isFalse()
      assertThat "hello".isNotCapitalized() isTrue()
    }
  }
}

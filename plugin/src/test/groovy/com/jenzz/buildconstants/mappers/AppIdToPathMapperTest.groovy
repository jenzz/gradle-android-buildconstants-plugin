package com.jenzz.buildconstants.mappers

import org.junit.Test

import static org.assertj.core.api.Assertions.assertThat;

class AppIdToPathMapperTest {

  private final Mapper<String, String> mapper = new AppIdToPathMapper()

  @Test
  void handlesNull() {
    assertThat mapper.map(null) isNull()
  }

  @Test
  void replacesDotsWithSlashes() {
    assertThat mapper.map("com.jenzz.buildconstants") isEqualTo "com/jenzz/buildconstants"
  }
}
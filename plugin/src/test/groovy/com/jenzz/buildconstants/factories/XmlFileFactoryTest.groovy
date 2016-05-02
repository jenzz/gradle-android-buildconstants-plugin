package com.jenzz.buildconstants.factories

import org.junit.Test

import static org.assertj.core.api.Assertions.assertThat

class XmlFileFactoryTest {

  @Test
  void createsCorrectPath() {
    String buildDir = "buildDir"
    String variantDir = "variantDir"
    String fileName = "fileName"
    FileFactory fileFactory = new XmlFileFactory(buildDir, variantDir)

    File actual = fileFactory.create fileName
    File expected = new File("${buildDir}/intermediates/res/merged/${variantDir}/values/${fileName}.xml")

    assertThat actual.path isEqualTo expected.path
  }
}
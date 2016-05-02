package com.jenzz.buildconstants.factories

import com.jenzz.buildconstants.mappers.Mapper
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

import static org.assertj.core.api.Assertions.assertThat
import static org.mockito.Matchers.anyString
import static org.mockito.Mockito.verify
import static org.mockito.Mockito.when
import static org.mockito.MockitoAnnotations.initMocks

class JavaFileFactoryTest {

  private final appId = "appId"
  private final buildDir = "buildDir"
  private final variantDir = "variantDir"
  private final fileName = "fileName"

  @Mock private Mapper<String, String> mockAppIdToPathMapper

  private fileFactory

  @Before
  void setUp() {
    initMocks this

    fileFactory = new JavaFileFactory(appId, buildDir, variantDir, mockAppIdToPathMapper)
  }

  @Test
  void usesAppIdToPathMapper() {
    fileFactory.create fileName

    verify(mockAppIdToPathMapper).map appId
  }

  @Test
  void createsCorrectPath() {
    String appPath = "appPath"
    when(mockAppIdToPathMapper.map(anyString())).thenReturn appPath

    File actual = fileFactory.create fileName
    File expected = new File("${buildDir}/generated/source/buildConfig/${variantDir}/${appPath}/${fileName}.java")

    assertThat actual.path isEqualTo expected.path
  }
}
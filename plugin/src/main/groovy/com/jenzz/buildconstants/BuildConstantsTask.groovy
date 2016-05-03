package com.jenzz.buildconstants

import com.android.annotations.NonNull
import com.jenzz.buildconstants.factories.JavaFileFactory
import com.jenzz.buildconstants.factories.XmlFileFactory
import com.jenzz.buildconstants.mappers.AppIdToPathMapper
import com.jenzz.buildconstants.sanitizers.JavaFileNameSanitizer
import com.jenzz.buildconstants.sanitizers.XmlFileNameSanitizer
import com.jenzz.buildconstants.writers.JavaConstantsWriter
import com.jenzz.buildconstants.writers.XmlConstantsWriter
import org.gradle.api.DefaultTask
import org.gradle.api.Task
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction

class BuildConstantsTask extends DefaultTask {

  @Input String appId
  @Input String variantDir
  @Input Map<String, Object> constants

  @OutputFile File javaFile
  @OutputFile File xmlFile

  private String javaFileName;

  @Override
  Task configure(Closure closure) {
    Task configure = super.configure(closure)
    javaFile = createJavaFile()
    xmlFile = createXmlFile()
    configure
  }

  @SuppressWarnings("GroovyUnusedDeclaration")
  @TaskAction
  void run() {
    brewJava();
    brewXml();
  }

  @NonNull
  private File createJavaFile() {
    String fileNameInput = project.buildConstants.javaFileName
    String fileName = javaFileName = new JavaFileNameSanitizer().sanitize fileNameInput
    new JavaFileFactory(appId, project.buildDir.path, variantDir, new AppIdToPathMapper()).create fileName
  }

  @NonNull
  private File createXmlFile() {
    String fileNameInput = project.buildConstants.xmlFileName
    String fileName = new XmlFileNameSanitizer().sanitize fileNameInput
    new XmlFileFactory(project.buildDir.path, variantDir).create fileName
  }

  private void brewJava() {
    new JavaConstantsWriter(javaFile, appId, javaFileName).write constants
  }

  private void brewXml() {
    new XmlConstantsWriter(xmlFile).write constants
  }
}
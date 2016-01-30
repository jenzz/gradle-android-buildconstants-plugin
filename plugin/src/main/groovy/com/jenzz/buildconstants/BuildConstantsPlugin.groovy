package com.jenzz.buildconstants

import com.android.build.gradle.AppPlugin
import com.android.build.gradle.LibraryPlugin
import org.gradle.api.GradleException
import org.gradle.api.Plugin
import org.gradle.api.Project

class BuildConstantsPlugin implements Plugin<Project> {

  @Override
  void apply(Project project) {
    if (!project.plugins.findPlugin(AppPlugin) && !project.plugins.findPlugin(LibraryPlugin)) {
      throw new GradleException(
          'You must apply the Android plugin or the Android library plugin before using the \'com.jenzz.buildconstants\' plugin')
    }

    project.extensions.add 'buildConstants', BuildConstantsExtension

    project.afterEvaluate {
      project.android.buildTypes.each { buildType ->

        def generateConstantsTask = project.tasks.create([name       : "generate${buildType.name.capitalize()}BuildConstants",
                                                          description: "Generates both Java and XML build constants",
                                                          type       : BuildConstantsTask], {
          buildTypeName = buildType.name
          constants = project.buildConstants.constants
        })

        def processResourcesTask = project.tasks.find {
          def pattern = ~/process${buildType.name.capitalize()}Resources$/
          pattern.matcher(it.name).matches()
        }

        processResourcesTask.dependsOn generateConstantsTask
      }
    }
  }
}
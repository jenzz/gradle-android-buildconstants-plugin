package com.jenzz.buildconstants

import com.android.build.gradle.AppPlugin
import org.gradle.api.GradleException
import org.gradle.api.Plugin
import org.gradle.api.Project

class BuildConstantsPlugin implements Plugin<Project> {

  @Override
  void apply(Project project) {
    if (!project.plugins.findPlugin(AppPlugin)) {
      throw new GradleException(
          'You must apply the Android plugin before using the \'com.jenzz.buildconstants\' plugin')
    }

    project.extensions.add 'buildConstants', BuildConstantsExtension

    project.afterEvaluate {

      project.android.applicationVariants.all { variant ->

        def variantName = variant.name.capitalize()
        def generateConstantsTask = project.tasks.create([name       : "generate${variantName}BuildConstants",
                                                          description: "Generates both Java and XML build constants",
                                                          type       : BuildConstantsTask], {
          appId = variant.applicationId
          variantDir = variant.dirName
          constants = project.buildConstants.constants
        })

        def processResourcesTask = project.tasks.find {
          def pattern = ~/process${variantName}Resources$/
          pattern.matcher(it.name).matches()
        }

        processResourcesTask.dependsOn generateConstantsTask
      }
    }
  }
}
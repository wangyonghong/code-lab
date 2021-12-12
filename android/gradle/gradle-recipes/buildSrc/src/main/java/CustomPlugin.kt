import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.register

class CustomPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        project.tasks.register<HelloTask>("customHello") {
            dependsOn("build")
        }
    }
}
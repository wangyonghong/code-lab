import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

abstract class HelloTask : DefaultTask() {

    @TaskAction
    fun taskAction() {
        println("Custom Hello from " + project.parent?.name)
    }
}
package zhx.lifecycle.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project


public class LifecyclePlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        print("LifecyclePlugin of zhx ===== ")
    }
}
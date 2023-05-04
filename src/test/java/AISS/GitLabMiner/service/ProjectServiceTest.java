package AISS.GitLabMiner.service;

import AISS.GitLabMiner.model.Project;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProjectServiceTest {

    @Autowired
    ProjectService service;

    @Test
    @DisplayName("Get all projects")
    void findAllProjects(){
        List<Project> projects = service.findAllProjects();
        assertTrue(!projects.isEmpty(), "The list of projects is empty");
        System.out.println(projects);
    }
}
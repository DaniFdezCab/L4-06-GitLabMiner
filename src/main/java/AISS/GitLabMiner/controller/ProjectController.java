package AISS.GitLabMiner.controller;

import AISS.GitLabMiner.model.Project;
import AISS.GitLabMiner.service.CommitService;
import AISS.GitLabMiner.service.IssueService;
import AISS.GitLabMiner.service.ProjectService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    private final ProjectService project;
    private final CommitService commits;
    private final IssueService issues;
    public ProjectController(ProjectService project, CommitService commits, IssueService issues){
        this.project = project;
        this.commits = commits;
        this.issues = issues;
    }
    @GetMapping("/{id}")
    public Project findOne(@PathVariable String id) {
        Project project = this.project.findProject(id);
        project.setCommits(commits.findCommits(id));
        project.setIssue(issues.findIssues(id));

        return project;
    }

}

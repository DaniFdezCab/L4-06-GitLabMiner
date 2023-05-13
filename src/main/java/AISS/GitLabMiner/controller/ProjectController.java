package AISS.GitLabMiner.controller;

import AISS.GitLabMiner.model.Project;
import AISS.GitLabMiner.service.CommitService;
import AISS.GitLabMiner.service.IssueService;
import AISS.GitLabMiner.service.ProjectService;
import org.springframework.web.bind.annotation.*;


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
    public Project findAll(@PathVariable String id,
                           @RequestParam(required = false, name = "sinceCommits") Integer sinceCommits,
                           @RequestParam(required = false, name = "sinceIssues") Integer sinceIssues,
                           @RequestParam(required = false, name = "maxPages") Integer maxPages) {
        Project project = this.project.findProject(id);
        project.setCommits(commits.getAllCommits(id,sinceCommits, maxPages));
        project.setIssue(issues.getAllIssues(id, sinceIssues, maxPages));

        return project;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Project postGitMiner(@PathVariable String id,
                                @RequestParam(required = false, name = "sinceCommits") Integer sinceCommits,
                                @RequestParam(required = false, name = "sinceIssues") Integer sinceIssues,
                                @RequestParam(required = false, name = "maxPages") Integer maxPages) {
        Project proyecto = this.project.findProject(id);
        proyecto.setCommits(commits.getAllCommits(id,sinceCommits, maxPages));
        proyecto.setIssue(issues.getAllIssues(id, sinceIssues, maxPages));

        Project sentProject = restTemplate.postForObject("http://localhost:8080/api/project", proyecto, Project.class);

        return sentProject;
    }
}

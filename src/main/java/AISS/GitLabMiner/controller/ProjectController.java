package AISS.GitLabMiner.controller;

import AISS.GitLabMiner.model.Project;
import AISS.GitLabMiner.service.CommitService;
import AISS.GitLabMiner.service.IssueService;
import AISS.GitLabMiner.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    private RestTemplate restTemplate;
    private final ProjectService project;
    private final CommitService commits;
    private final IssueService issues;
    public ProjectController(ProjectService project, CommitService commits, IssueService issues){
        this.project = project;
        this.commits = commits;
        this.issues = issues;
    }
    @GetMapping("/prueba/{id}")
    public Project findAll(@PathVariable String id,
                           @RequestParam(required = false, name = "sinceCommits") Integer sinceCommits,
                           @RequestParam(required = false, name = "sinceIssues") Integer sinceIssues,
                           @RequestParam(required = false, name = "maxPages") Integer maxPages) {
        if(sinceCommits==null){
            sinceCommits=2;
        }if(sinceIssues==null) {
            sinceIssues=20;
        }if(maxPages==null){
            maxPages=2;
        }
        Project project = this.project.findProject(id);
        project.setCommits(commits.getAllCommits(id,sinceCommits, maxPages));
        project.setIssue(issues.getSimpleIssues(id));

        return project;
    }
    @GetMapping("/{id}")
    public Project sendProject(@PathVariable String id,
                               @RequestParam(required = false, name = "sinceCommits") Integer sinceCommits,
                               @RequestParam(required = false, name = "sinceIssues") Integer sinceIssues,
                               @RequestParam(required = false, name = "maxPages") Integer maxPages) {
        if(sinceCommits==null){
            sinceCommits=2;
        }if(sinceIssues==null) {
            sinceIssues=20;
        }if(maxPages==null){
            maxPages=2;
        }
        Project project = this.project.findProject(id);
        project.setCommits(commits.getAllCommits(id,sinceCommits, maxPages));
        project.setIssue(issues.getSimpleIssues(id));

        /*
       restTemplate.postForObject("http://localhost:8081/api/project/" + id
                + "?sinceCommits=" + sinceCommits + "&sinceIssues="
                + sinceIssues +"&maxPages=" + maxPages, project, Project.class);
*/
        return project;
    }


}


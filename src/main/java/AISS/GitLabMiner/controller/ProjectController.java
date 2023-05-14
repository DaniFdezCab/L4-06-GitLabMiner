package AISS.GitLabMiner.controller;

import AISS.GitLabMiner.etl.Transformation;
import AISS.GitLabMiner.model.GMProject;
import AISS.GitLabMiner.model.Project;
import AISS.GitLabMiner.service.CommitService;
import AISS.GitLabMiner.service.IssueService;
import AISS.GitLabMiner.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    private RestTemplate restTemplate;
    private final ProjectService project;
    private final CommitService commits;
    private final IssueService issues;
    private final Transformation transformation;
    public ProjectController(ProjectService project, CommitService commits, IssueService issues, Transformation transformation){
        this.project = project;
        this.commits = commits;
        this.issues = issues;
        this.transformation = transformation;
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
        project.setIssue(issues.getAllIssues(id, sinceCommits,  maxPages));

        return project;
    }
    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public GMProject sendProject(@PathVariable String id,
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
        project.setIssue(issues.getAllIssues(id, sinceCommits,  maxPages));

        GMProject p = restTemplate.postForObject("http://localhost:8080/gitminer/projects", transformation.parseProject(project), GMProject.class);


        return p;
    }


}


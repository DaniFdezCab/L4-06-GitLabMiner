package AISS.GitLabMiner.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Project {
    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("web_url")
    private String web_url;
    @JsonProperty("commits")
    private List<Commit> commits;
    @JsonProperty("commits")
    private List<Issue> issues;

    public Project(){
    }
    public Project(String id, String name, String web_url, List<Commit> commits, List<Issue> issues){
        this.id = id;
        this.name = name;
        this.web_url = web_url;
        this.commits = commits;
        this.issues = issues;
    }
    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }
    @JsonProperty("web_url")
    public String getWeb_url() {
        return web_url;
    }
    @JsonProperty("web_url")
    public void setWeb_url(String web_url) {
        this.web_url = web_url;
    }

    public List<Commit> getCommits() {
        return commits;
    }

    public void setCommits(List<Commit> commits) {
        this.commits = commits;
    }

    public List<Issue> getIssue() {
        return issues;
    }

    public void setIssue(List<Issue> issues) {
        this.issues = issues;
    }
}

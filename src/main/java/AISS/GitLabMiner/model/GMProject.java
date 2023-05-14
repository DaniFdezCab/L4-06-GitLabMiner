package AISS.GitLabMiner.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GMProject {
    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("web_url")
    private String webUrl;
    @JsonProperty("commits")
    private List<Commit> commits;
    @JsonProperty("issues")
    private List<GMIssue> issues;

    public GMProject(){
    }

    public GMProject(String id, String name, String webUrl, List<Commit> commits, List<GMIssue> issues){
        this.id = id;
        this.name = name;
        this.webUrl = webUrl;
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
        return webUrl;
    }
    @JsonProperty("web_url")
    public void setWeb_url(String web_url) {
        this.webUrl = web_url;
    }
    @JsonProperty("commits")
    public List<Commit> getCommits() {
        return commits;
    }
    @JsonProperty("commits")
    public void setCommits(List<Commit> commits) {
        this.commits = commits;
    }
    @JsonProperty("issues")
    public List<GMIssue> getIssue() {
        return issues;
    }
    @JsonProperty("issues")
    public void setIssue(List<GMIssue> issues) {
        this.issues = issues;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", web_url='" + webUrl + '\'' +
                ", commits=" + commits +
                ", issues=" + issues +
                '}';
    }

}

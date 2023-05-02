package AISS.GitLabMiner.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Issue {

    @JsonProperty("id")
    private String id;
    @JsonProperty("ref_id")
    private String ref_id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("description")
    private String description;

    @JsonProperty("state")
    private String state;

    @JsonProperty("created_at")
    private String created_at;

    @JsonProperty("updated_at")
    private String updated_at;

    @JsonProperty("closed_at")
    private String closed_at;

    @JsonProperty("labels")
    private List<String> labels;

    @JsonProperty("upVotes")
    private Integer upVotes;

    @JsonProperty("downVotes")
    private Integer downVotes;

    public Issue(){
    }

    public Issue(String id, String ref_id, String title, String description, String state,
                  String created_at, String updated_at, List<String> labels, Integer upVotes,
                  Integer downVotes){

        this.id = id;
        this.ref_id = ref_id;
        this.title = title;
        this.description = description;
        this.state = state;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.labels = labels;
        this.upVotes = upVotes;
        this.downVotes = downVotes;

    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getRef_id() { return ref_id; }

    public void setRef_id(String ref_id) { this.ref_id = ref_id; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public String getState() { return state; }

    public void setState(String state) { this.state = state; }

    public String getCreated_at() { return created_at; }

    public void setCreated_at(String created_at) { this.created_at = created_at; }

    public String getUpdated_at() { return updated_at; }

    public void setUpdated_at(String updated_at) { this.updated_at = updated_at; }

    public String getClosed_at() { return closed_at; }

    public void setClosed_at(String closed_at) { this.closed_at = closed_at; }

    public List<String> getLabels() { return labels; }

    public void setLabels(List<String> labels) { this.labels = labels; }

    public Integer getUpVotes() { return upVotes; }

    public void setUpVotes(Integer upVotes) { this.upVotes = upVotes; }

    public Integer getDownVotes() { return downVotes; }

    public void setDownVotes(Integer downVotes) { this.downVotes = downVotes; }

    @Override
    public String toString() {
        return "Issue{" +
                "id='" + id + '\'' +
                ", ref_id='" + ref_id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", state='" + state + '\'' +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", closed_at='" + closed_at + '\'' +
                ", labels=" + labels +
                ", upVotes=" + upVotes +
                ", downVotes=" + downVotes +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Issue issue = (Issue) o;
        return Objects.equals(id, issue.id) && Objects.equals(ref_id, issue.ref_id) && Objects.equals(title, issue.title) && Objects.equals(description, issue.description) && Objects.equals(state, issue.state) && Objects.equals(created_at, issue.created_at) && Objects.equals(updated_at, issue.updated_at) && Objects.equals(closed_at, issue.closed_at) && Objects.equals(labels, issue.labels) && Objects.equals(upVotes, issue.upVotes) && Objects.equals(downVotes, issue.downVotes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ref_id, title, description, state, created_at, updated_at, closed_at, labels, upVotes, downVotes);
    }
}

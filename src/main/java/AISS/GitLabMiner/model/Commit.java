package AISS.GitLabMiner.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Commit {

    @JsonProperty("id")
    private String id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("message")
    private String message;
    @JsonProperty("author_name")
    private String author_name;

    @JsonProperty("author_email")
    private String author_email;

    @JsonProperty("authored_date")
    private String authored_date;

    @JsonProperty("committer_name")
    private String committer_name;

    @JsonProperty("committer_email")
    private String committer_email;

    @JsonProperty("committed_date")
    private String committed_date;

    @JsonProperty("web_url")
    private String web_url;

    public Commit(){
    }
    public Commit(String id, String title, String message, String author_name, String author_email,
                  String authored_date, String committer_name, String committer_email, String committed_date,
                  String web_url){

        this.id = id;
        this.title = title;
        this.message = message;
        this.author_name = author_name;
        this.author_email = author_email;
        this.authored_date = authored_date;
        this.committer_name = committer_name;
        this.committer_email = committer_email;
        this.committed_date = committed_date;
        this.web_url = web_url;

    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getMessage() { return message; }

    public void setMessage(String message) { this.message = message; }

    public String getAuthor_name() { return author_name; }

    public void setAuthor_name(String author_name) { this.author_name = author_name; }

    public String getAuthor_email() { return author_email; }

    public void setAuthor_email(String author_email) { this.author_email = author_email; }

    public String getAuthored_date() { return authored_date; }

    public void setAuthored_date(String authored_date) { this.authored_date = authored_date; }

    public String getCommitter_name() { return committer_name; }

    public void setCommitter_name(String committer_name) { this.committer_name = committer_name; }

    public String getCommitter_email() { return committer_email; }

    public void setCommitter_email(String committer_email) { this.committer_email = committer_email; }

    public String getCommitted_date() { return committed_date; }

    public void setCommitted_date(String committed_date) { this.committed_date = committed_date; }

    public String getWeb_url() { return web_url; }

    public void setWeb_url(String web_url) { this.web_url = web_url; }

    @Override
    public String toString() {
        return "Commit{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", message='" + message + '\'' +
                ", author_name='" + author_name + '\'' +
                ", author_email='" + author_email + '\'' +
                ", authored_date='" + authored_date + '\'' +
                ", committer_name='" + committer_name + '\'' +
                ", committer_email='" + committer_email + '\'' +
                ", committed_date='" + committed_date + '\'' +
                ", web_url='" + web_url + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Commit commit = (Commit) o;
        return Objects.equals(id, commit.id) && Objects.equals(title, commit.title) && Objects.equals(message, commit.message) && Objects.equals(author_name, commit.author_name) && Objects.equals(author_email, commit.author_email) && Objects.equals(authored_date, commit.authored_date) && Objects.equals(committer_name, commit.committer_name) && Objects.equals(committer_email, commit.committer_email) && Objects.equals(committed_date, commit.committed_date) && Objects.equals(web_url, commit.web_url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, message, author_name, author_email, authored_date, committer_name, committer_email, committed_date, web_url);
    }
}
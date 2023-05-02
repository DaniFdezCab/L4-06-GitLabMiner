package AISS.GitLabMiner.model;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ProjectSearch {

    private List<Project> data;

    public ProjectSearch(List<Project> data){
        this.data = data;
    }
    @JsonProperty("data")
    public List<Project> getData() {
        return data;
    }
}

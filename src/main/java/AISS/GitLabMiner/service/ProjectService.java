package AISS.GitLabMiner.service;

import AISS.GitLabMiner.model.Project;
import AISS.GitLabMiner.model.ProjectSearch;
import AISS.GitLabMiner.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class ProjectService {

    @Autowired
    RestTemplate restTemplate;
    public Project findProject(){
        String uri = "https://gitlab.com/api/v4/projects/45647652?private_token=glpat-2_yFGw7WLXHPBHEZHbG5";

        Project project = restTemplate.getForObject(uri, Project.class);

        return project;

    }


}

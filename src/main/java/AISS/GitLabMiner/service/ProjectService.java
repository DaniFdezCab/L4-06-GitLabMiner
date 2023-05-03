package AISS.GitLabMiner.service;

import AISS.GitLabMiner.model.Project;
import AISS.GitLabMiner.model.ProjectSearch;
import AISS.GitLabMiner.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
@Service
public class ProjectService {

    @Autowired
    RestTemplate restTemplate;
    public Project findProject(String id){
        String uri = "https://gitlab.com/api/v4/projects/" + id + "?private_token=glpat-2_yFGw7WLXHPBHEZHbG5";

        Project project = restTemplate.getForObject(uri, Project.class);

        return project;

    }


}

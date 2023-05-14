package AISS.GitLabMiner.service;

import AISS.GitLabMiner.model.Commit;
import AISS.GitLabMiner.model.Project;
import AISS.GitLabMiner.model.ProjectSearch;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import utils.Utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
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

    public List<Project> findAllProjects(){
        List<Project> projects = null;
        String uri= "https://gitlab.com/api/v4/projects/";

        ProjectSearch projectSearch= restTemplate.getForObject(uri, ProjectSearch.class);
        projects = projectSearch.getData();

        return projects;
    }
    private Logger logger;
    public ResponseEntity<Project[]> getProjects(String uri) {

        //Request
        HttpHeaders headers = new HttpHeaders();

        HttpEntity<Project[]> request = new HttpEntity<>(null, headers);

        //send
        ResponseEntity<Project[]> response = restTemplate.exchange(uri, HttpMethod.GET, request, Project[].class);

        return response;
    }

    public List<Project> getAllProjects(String id, Integer sinceDays, Integer maxPages){

        List<Project> projects = new ArrayList<>();
        LocalDateTime since = LocalDateTime.now().minusDays(sinceDays);

        // FIRST PAGE

        String uri = "https://gitlab.com/api/v4/projects/" + id + "?since=" + since.format(DateTimeFormatter.ofPattern("YYYY-MM-DDTHH:MM:SSZ")) + "?private_token=glpat-2_yFGw7WLXHPBHEZHbG5";

        logger.debug("retrieving project from page 1" + uri);

        ResponseEntity<Project[]> response = getProjects(uri);
        List<Project> pageProjects = Arrays.stream(response.getBody()).toList();
        logger.debug(pageProjects.size() + "Projects retrieved.");

        projects.addAll(pageProjects);

        // 2..N PAGE

        String nextPageUrl = Utils.getNextPageUrl(response.getHeaders());
        int page = 2;
        while (nextPageUrl != null && page <= maxPages){
            logger.debug("Retrieving projects from page" + page + ": " + nextPageUrl);
            response = getProjects(nextPageUrl);
            pageProjects = Arrays.stream(response.getBody()).toList();
            logger.debug(pageProjects.size() + "Projects retrieved.");
            nextPageUrl = Utils.getNextPageUrl(response.getHeaders());
            page++;
            projects.addAll(pageProjects);
        }
        return projects;

    }

    public Project getSimpleProjects(String id) {

        String uri = "https://gitlab.com/api/v4/projects/" + id + "?private_token=glpat-2_yFGw7WLXHPBHEZHbG5";

        Project response = restTemplate.getForObject(uri, Project.class);

        return response;
    }


}

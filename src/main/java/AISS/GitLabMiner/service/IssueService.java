package AISS.GitLabMiner.service;

import AISS.GitLabMiner.model.Commit;
import AISS.GitLabMiner.model.Issue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
@Service
public class IssueService {
    @Autowired
    RestTemplate restTemplate;
    public List<Issue> findIssues(String id){

        String uri = "https://gitlab.com/api/v4/projects/" + id + "/issues?private_token=glpat-2_yFGw7WLXHPBHEZHbG5";

        Issue[] issues = restTemplate.getForObject(uri, Issue[].class);

        return Arrays.stream(issues).toList();

    }
}

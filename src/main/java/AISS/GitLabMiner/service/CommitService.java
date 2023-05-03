package AISS.GitLabMiner.service;

import AISS.GitLabMiner.model.Commit;
import AISS.GitLabMiner.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
@Service
public class CommitService {

    @Autowired
    RestTemplate restTemplate;
    public List<Commit> findCommits(String id){

        String uri = "https://gitlab.com/api/v4/projects/" + id + "/repository/commits?private_token=glpat-2_yFGw7WLXHPBHEZHbG5";

        Commit[] commits = restTemplate.getForObject(uri, Commit[].class);

        return Arrays.stream(commits).toList();

    }
}

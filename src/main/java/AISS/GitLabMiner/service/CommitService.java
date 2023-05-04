package AISS.GitLabMiner.service;

import AISS.GitLabMiner.model.Commit;
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
public class CommitService {
    @Autowired
    RestTemplate restTemplate;

    private Logger logger;
    public ResponseEntity<Commit[]> getCommits(String uri) {

        //Request
        HttpHeaders headers = new HttpHeaders();

        HttpEntity<Commit[]> request = new HttpEntity<>(null, headers);

        //send
        ResponseEntity<Commit[]> response = restTemplate.exchange(uri, HttpMethod.GET, request, Commit[].class);

        return response;
    }

    public List<Commit> getAllCommits(String id, Integer sinceDays, Integer maxPages){

        List<Commit> commits = new ArrayList<>();
        LocalDateTime since = LocalDateTime.now().minusDays(sinceDays);

        // FIRST PAGE
        // YYYY-MM-DDTHH:MM:SSZ

        String uri = "https://gitlab.com/api/v4/projects/" + id + "/repository/commits?since=" + since.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")).toString() + "?private_token=glpat-2_yFGw7WLXHPBHEZHbG5";

        System.out.println(uri);
        // logger.debug("retrieving commit from page 1" + uri);

        ResponseEntity<Commit[]> response = getCommits(uri);
        List<Commit> pageCommits = Arrays.stream(response.getBody()).toList();
        // logger.debug(pageCommits.size() + "Commits retrieved.");

        commits.addAll(pageCommits);

        // 2..N PAGE

        String nextPageUrl = Utils.getNextPageUrl(response.getHeaders());
        int page = 2;
        while (nextPageUrl != null && page <= maxPages){
            // logger.debug("Retrieving commits from page" + page + ": " + nextPageUrl);
            response = getCommits(nextPageUrl);
            pageCommits = Arrays.stream(response.getBody()).toList();
            // logger.debug(pageCommits.size() + "Commits retrieved.");
            nextPageUrl = Utils.getNextPageUrl(response.getHeaders());
            page++;

        }
        return commits;

    }

    public List<Commit> getSimpleCommits(String id) {

        String uri = "https://gitlab.com/api/v4/projects/" + id + "/repository/commits?private_token=glpat-2_yFGw7WLXHPBHEZHbG5";

        Commit[] response = restTemplate.getForObject(uri, Commit[].class);

        return Arrays.stream(response).toList();
    }

}

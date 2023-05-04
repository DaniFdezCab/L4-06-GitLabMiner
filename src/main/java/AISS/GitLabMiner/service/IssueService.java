package AISS.GitLabMiner.service;

import AISS.GitLabMiner.model.*;
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
public class IssueService {
    @Autowired
    RestTemplate restTemplate;
    public List<Issue> findIssues(String id){

        String uri = "https://gitlab.com/api/v4/projects/" + id + "/issues?private_token=glpat-2_yFGw7WLXHPBHEZHbG5";

        Issue[] issues = restTemplate.getForObject(uri, Issue[].class);

        return Arrays.stream(issues).toList();

    }
    private Logger logger;
    public ResponseEntity<Issue[]> getIssues(String uri) {

        //Request
        HttpHeaders headers = new HttpHeaders();

        HttpEntity<Issue[]> request = new HttpEntity<>(null, headers);

        //send
        ResponseEntity<Issue[]> response = restTemplate.exchange(uri, HttpMethod.GET, request, Issue[].class);

        return response;
    }

    public List<Issue> getAllIssues(String id, Integer sinceDays, Integer maxPages){

        List<Issue> issues = new ArrayList<>();
        LocalDateTime since = LocalDateTime.now().minusDays(sinceDays);

        // FIRST PAGE

        String uri = "https://gitlab.com/api/v4/projects/" + id + "/issues?since=" + since.format(DateTimeFormatter.ofPattern("YYYY-MM-DDTHH:MM:SSZ")) + "?private_token=glpat-2_yFGw7WLXHPBHEZHbG5";

        logger.debug("retrieving issue from page 1" + uri);

        ResponseEntity<Issue[]> response = getIssues(uri);
        List<Issue> pageIssues = Arrays.stream(response.getBody()).toList();
        logger.debug(pageIssues.size() + "Issues retrieved.");

        issues.addAll(pageIssues);

        // 2..N PAGE

        String nextPageUrl = Utils.getNextPageUrl(response.getHeaders());
        int page = 2;
        while (nextPageUrl != null && page <= maxPages){
            logger.debug("Retrieving issues from page" + page + ": " + nextPageUrl);
            response = getIssues(nextPageUrl);
            pageIssues = Arrays.stream(response.getBody()).toList();
            logger.debug(pageIssues.size() + "issues retrieved.");
            nextPageUrl = Utils.getNextPageUrl(response.getHeaders());
            page++;
            issues.addAll(pageIssues);
        }
        return issues;

    }

    public List<Issue> getSimpleIssues(String id) {

        String uri = "https://gitlab.com/api/v4/projects/" + id + "/issues?private_token=glpat-2_yFGw7WLXHPBHEZHbG5";

        Issue[] response = restTemplate.getForObject(uri, Issue[].class);

        return Arrays.stream(response).toList();
    }
}

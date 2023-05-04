package AISS.GitLabMiner.service;

import AISS.GitLabMiner.model.*;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import utils.Utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommentService {

    @Autowired
    RestTemplate restTemplate;
    public List<Comment> findComments(String id){

        String uri = "https://gitlab.com/api/v4/projects/" + id + "/comments?private_token=glpat-2_yFGw7WLXHPBHEZHbG5";

        Comment[] comments = restTemplate.getForObject(uri, Comment[].class);

        return Arrays.stream(comments).toList();

    }
    private Logger logger;
    public ResponseEntity<Comment[]> getComments(String uri) {

        //Request
        HttpHeaders headers = new HttpHeaders();

        HttpEntity<Comment[]> request = new HttpEntity<>(null, headers);

        //send
        ResponseEntity<Comment[]> response = restTemplate.exchange(uri, HttpMethod.GET, request, Comment[].class);

        return response;
    }

    public List<Comment> getAllComments(String id, Integer sinceDays, Integer maxPages){

        List<Comment> comments = new ArrayList<>();
        LocalDateTime since = LocalDateTime.now().minusDays(sinceDays);

        // FIRST PAGE

        String uri = "https://gitlab.com/api/v4/projects/" + id + "/comments?since=" + since.format(DateTimeFormatter.ofPattern("YYYY-MM-DDTHH:MM:SSZ")) + "?private_token=glpat-2_yFGw7WLXHPBHEZHbG5";

        logger.debug("retrieving comment from page 1" + uri);

        ResponseEntity<Comment[]> response = getComments(uri);
        List<Comment> pageComments = Arrays.stream(response.getBody()).toList();
        logger.debug(pageComments.size() + "Comments retrieved.");

        comments.addAll(pageComments);

        // 2..N PAGE

        String nextPageUrl = Utils.getNextPageUrl(response.getHeaders());
        int page = 2;
        while (nextPageUrl != null && page <= maxPages){
            logger.debug("Retrieving comments from page" + page + ": " + nextPageUrl);
            response = getComments(nextPageUrl);
            pageComments = Arrays.stream(response.getBody()).toList();
            logger.debug(pageComments.size() + "Comments retrieved.");
            nextPageUrl = Utils.getNextPageUrl(response.getHeaders());
            page++;
            comments.addAll(pageComments);
        }
        return comments;

    }

    public List<Comment> getSimpleComments(String id) {

        String uri = "https://gitlab.com/api/v4/projects/" + id + "/comments?private_token=glpat-2_yFGw7WLXHPBHEZHbG5";

        Comment[] response = restTemplate.getForObject(uri, Comment[].class);

        return Arrays.stream(response).toList();
    }

}

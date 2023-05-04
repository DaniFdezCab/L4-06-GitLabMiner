package AISS.GitLabMiner.service;

import AISS.GitLabMiner.model.Commit;
import AISS.GitLabMiner.model.User;
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

public class UserService {

    @Autowired
    RestTemplate restTemplate;

    private Logger logger;

    public ResponseEntity<User[]> getUsers(String uri) {

        //Request
        HttpHeaders headers = new HttpHeaders();

        HttpEntity<User[]> request = new HttpEntity<>(null, headers);

        //send
        ResponseEntity<User[]> response = restTemplate.exchange(uri, HttpMethod.GET, request, User[].class);

        return response;
    }

    public List<User> getAllUsers(String id, Integer sinceDays, Integer maxPages) {

        List<User> users = new ArrayList<>();
        LocalDateTime since = LocalDateTime.now().minusDays(sinceDays);

        // FIRST PAGE

        String uri = "https://gitlab.com/api/v4/users/" + id + "/?since=" + since.format(DateTimeFormatter.ofPattern("YYYY-MM-DDTHH:MM:SSZ")) + "?private_token=glpat-2_yFGw7WLXHPBHEZHbG5";

        logger.debug("retrieving user from page 1" + uri);

        ResponseEntity<User[]> response = getUsers(uri);
        List<User> pageUsers = Arrays.stream(response.getBody()).toList();
        logger.debug(pageUsers.size() + "Users retrieved.");

        users.addAll(pageUsers);

        // 2..N PAGE

        String nextPageUrl = Utils.getNextPageUrl(response.getHeaders());
        int page = 2;
        while (nextPageUrl != null && page <= maxPages) {
            logger.debug("Retrieving users from page" + page + ": " + nextPageUrl);
            response = getUsers(nextPageUrl);
            pageUsers = Arrays.stream(response.getBody()).toList();
            logger.debug(pageUsers.size() + "Users retrieved.");
            nextPageUrl = Utils.getNextPageUrl(response.getHeaders());
            page++;
            users.addAll(pageUsers);
        }
        return users;

    }

    public List<User> getSimpleUsers(String id) {

        String uri = "https://gitlab.com/api/v4/users/" + id + "/?private_token=glpat-2_yFGw7WLXHPBHEZHbG5";

        User[] response = restTemplate.getForObject(uri, User[].class);

        return Arrays.stream(response).toList();
    }
}

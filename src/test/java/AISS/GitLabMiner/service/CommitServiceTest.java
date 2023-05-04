package AISS.GitLabMiner.service;

import AISS.GitLabMiner.model.Commit;
import AISS.GitLabMiner.model.Project;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CommitServiceTest {

    @Autowired
    CommitService service;

    @Test
    @DisplayName("Get all Commits")
    void findAllCommits(){
        List<Commit>  commits = service.getAllCommits("45726601",20,2);
        assertTrue(!commits.isEmpty(), "The list of projects is empty");
        System.out.println(commits);
    }

}
package AISS.GitLabMiner.service;

import AISS.GitLabMiner.model.Issue;
import AISS.GitLabMiner.model.Project;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IssueServiceTest {

    @Autowired
    IssueService service;

    @Test
    @DisplayName("Get all issues")
    void findAllIssues(){
        List<Issue> issues = service.findIssues("45726601");
        assertTrue(!issues.isEmpty(), "The list of issues is empty");
        System.out.println(issues);
    }

}
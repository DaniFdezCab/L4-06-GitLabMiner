package aiss.gitlabminer.service;

import AISS.GitLabMiner.model.Comment;
import AISS.GitLabMiner.model.Commit;
import AISS.GitLabMiner.model.Issue;
import AISS.GitLabMiner.model.Project;
import AISS.GitLabMiner.service.CommentService;
import AISS.GitLabMiner.service.CommitService;
import AISS.GitLabMiner.service.IssueService;
import AISS.GitLabMiner.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class GitLabServiceTest {

	@Autowired
	ProjectService projectService;
	@Autowired
	CommitService commitService;
	@Autowired
	IssueService issueService;


	@Test
	void getProjectById() {
		Project project = projectService.getSimpleProjects("8819931");
		assertTrue(project != null, "the project is empty");
		System.out.println(project);
	}

	@Test
	void getAllCommits() {
		List<Commit> commits = commitService.getSimpleCommits("8819931");
		assertTrue(commits != null, "no commits have been found");
		System.out.println(commits);
	}

	@Test
	void getAllIssues() {
		List<Issue> issues = issueService.getSimpleIssues("8819931");
		assertTrue(issues != null, "no issues have been found");
		System.out.println(issues);
	}

}
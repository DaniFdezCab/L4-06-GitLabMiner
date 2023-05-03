package AISS.GitLabMiner.etl;

import AISS.GitLabMiner.model.Commit;
import AISS.GitLabMiner.model.Issue;
import AISS.GitLabMiner.model.Project;

import java.util.List;

public class Transformation {


    public static Project getGMProject(Project p, List<Commit> c, List<Issue> i){
        Project project = p;
        project.setCommits(c);
        project.setIssue(i);
        return project;
    }
}

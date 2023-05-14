package AISS.GitLabMiner.etl;

import AISS.GitLabMiner.model.*;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class Transformation {


    public static GMProject parseProject(Project p){
        GMProject gMProject = new GMProject();
        gMProject.setId(p.getId());
        gMProject.setName(p.getName());
        gMProject.setWeb_url(p.getWeb_url());
        gMProject.setCommits(p.getCommits());
        gMProject.setIssue(p.getIssue().stream().map(i -> parseIssue(i)).toList());

        return gMProject;
    }

    private static GMIssue parseIssue(Issue issue) {
        GMIssue gmIssue = new GMIssue(issue.getId(), issue.getRef_id().toString(), issue.getTitle(), issue.getDescription(),
                issue.getState(), issue.getCreated_at(), issue.getUpdated_at(), issue.getClosed_at(),
                issue.getLabels(), issue.getAuthorUser(), issue.getAsigneeUser(), issue.getUpVotes(), issue.getDownVotes(),
                null);
        return gmIssue;
    }
}

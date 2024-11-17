package ru.stqa.mantis.manager;

import ru.stqa.mantis.manager.developermail.client.ApiClient;
import ru.stqa.mantis.manager.developermail.client.ApiException;
import ru.stqa.mantis.manager.developermail.client.Configuration;
import ru.stqa.mantis.manager.developermail.client.api.IssuesApi;
import ru.stqa.mantis.manager.developermail.client.api.UserApi;
import ru.stqa.mantis.manager.developermail.client.auth.ApiKeyAuth;
import ru.stqa.mantis.manager.developermail.client.model.Identifier;
import ru.stqa.mantis.manager.developermail.client.model.Issue;
import ru.stqa.mantis.manager.developermail.client.model.User;
import ru.stqa.mantis.manager.developermail.client.model.UserAddResponse;
import ru.stqa.mantis.model.IssueData;
import ru.stqa.mantis.model.UserData;

public class RestApiHelper extends HelperBase {
    public RestApiHelper(ApplicationManager manager) {
        super(manager);
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        ApiKeyAuth Authorization = (ApiKeyAuth) defaultClient.getAuthentication("Authorization");
        Authorization.setApiKey(manager.property("apiKey"));
    }

    public void createIssue(IssueData issueData) {

        Issue issue = new Issue();
        issue.setSummary(issueData.summary());
        issue.setDescription(issueData.description());
        var projectId = new Identifier();
        projectId.setId(issueData.project());
        issue.setProject(projectId);
        var categoryId = new Identifier();
        categoryId.setId(issueData.category());
        issue.setCategory(categoryId);

        IssuesApi apiInstance = new IssuesApi();
        try {
            apiInstance.issueAdd(issue);
        } catch (ApiException e) {
          new RuntimeException(e);
        }

    }

    public void createUser(UserData userData) {

        User user = new User();
        user.setUsername(userData.username());
        user.setEmail(userData.email());

        UserApi apiInstance = new UserApi();
        try {
            UserAddResponse result = apiInstance.userAdd(user);
            System.out.println(result);
        } catch (ApiException e) {
           new RuntimeException(e);
        }
    }
}

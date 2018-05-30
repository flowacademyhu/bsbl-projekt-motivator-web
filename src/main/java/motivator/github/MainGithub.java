package motivator.github;

import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.RepositoryCommit;
import org.eclipse.egit.github.core.RepositoryId;
import org.eclipse.egit.github.core.service.CommitService;
import org.eclipse.egit.github.core.service.RepositoryService;
import org.kohsuke.github.*;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.*;

public class MainGithub {
    private static GitHub gitHub;
    private static final String FULL_REPOSITORY = "laszlobalint/java";
    private static final String OWNER = "laszlobalint";
    private static final String REPOSITORY = "java";
    private static List<String> shals = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        MainGithub mainGithub = new MainGithub();
        mainGithub.getAllRepositories();
        mainGithub.getUserRepositories();
        mainGithub.getRepositoryBranchesNames();
        mainGithub.getPageCommits();
        mainGithub.getFileContents();
    }

    // GET TREE STRUCTURE OF GIT REPOSITORY (COMMIT TEXTS AND COMMIT SHALS): git log --graph --oneline --all

    // LISTS OUT ALL THE REPOSIOTRY NAMES THAT USER HAS ACCESS TO
    private GHMyself getAllRepositories() throws IOException {
        try {
            gitHub = GitHub.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        GHMyself user = gitHub.getMyself();
        Map<String, GHRepository> repos = user.getAllRepositories();
        for (String repoName : repos.keySet()) {
            System.out.println(repoName);
        }
        return user;
    }

    // LISTS OUT ALL OUT BRANCHES TO THE SPECIFIC REPOSITORY
    private Map<String, GHBranch> getRepositoryBranchesNames() throws IOException {
        try {
            gitHub = GitHub.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String, GHBranch> branches = gitHub.getRepository(FULL_REPOSITORY).getBranches();
        for (GHBranch branch : branches.values()) {
            System.out.println(branch.getName());
        }
        return branches;
    }

    // LIST ALL OWN REPOSITORIES OF THE OWNER
    private void getUserRepositories() {
        final String user = "laszlobalint";
        final String format = "{0}) {1}- created on {2}";
        int count = 1;
        RepositoryService service = new RepositoryService();
        try {
            for (Repository repo : service.getRepositories(user))
                System.out.println(MessageFormat.format(format, count++, repo.getName(), repo.getCreatedAt()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // LAST 20 COMMITS OF THE REPOSITORY (message, shalId, author, date)
    private void getPageCommits() {
        final int size = 20;
        final RepositoryId repo = new RepositoryId(OWNER, REPOSITORY);
        final String message = "   {0} by {1} on {2}";
        final CommitService service = new CommitService();
        int pages = 1;
        for (Collection<RepositoryCommit> commits : service.pageCommits(repo,
                size)) {
            System.out.println("Commit Page " + pages++);
            for (RepositoryCommit commit : commits) {
                String sha = commit.getSha().substring(0, 7);
                shals.add(sha);
                String author = commit.getCommit().getAuthor().getName();
                Date date = commit.getCommit().getAuthor().getDate();
                String commitMessage = commit.getCommit().getMessage();
                System.out.print("MESSAGE: " + commitMessage + " - ");
                System.out.println(MessageFormat.format(message, sha, author, date));
            }
        }
    }

    // LIST THE CHANGES OF THE FILES INT THE COMMIT:
    private void getFileContents() throws IOException {
        try {
            gitHub = GitHub.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String shal1 : shals) {
            List<GHCommit.File> files = gitHub.getRepository(FULL_REPOSITORY).getCommit(shal1).getFiles();
            for (GHCommit.File file : files) {
                System.out.println(file.getPatch());
            }
        }
    }
}
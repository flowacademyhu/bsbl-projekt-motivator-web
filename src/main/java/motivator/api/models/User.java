package motivator.api.models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String password;
    private String email;
    private String gitHubProfile;
    private String trelloProfile;
    private String slackProfile;
    private Long currentScore;
    private String activeGroup;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false)
    private Date created;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = false)
    private Date updated;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGitHubProfile() {
        return gitHubProfile;
    }

    public void setGitHubProfile(String gitHubProfile) {
        this.gitHubProfile = gitHubProfile;
    }

    public String getTrelloProfile() {
        return trelloProfile;
    }

    public void setTrelloProfile(String trelloProfile) {
        this.trelloProfile = trelloProfile;
    }

    public String getSlackProfile() {
        return slackProfile;
    }

    public void setSlackProfile(String slackProfile) {
        this.slackProfile = slackProfile;
    }

    public Long getCurrentScore() {
        return currentScore;
    }

    public void setCurrentScore(Long currentScore) {
        this.currentScore = currentScore;
    }

    public String getActiveGroup() {
        return activeGroup;
    }

    public void setActiveGroup(String activeGroup) {
        this.activeGroup = activeGroup;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", gitHubProfile='" + gitHubProfile + '\'' +
                ", trelloProfile='" + trelloProfile + '\'' +
                ", slackProfile='" + slackProfile + '\'' +
                ", currentScore=" + currentScore +
                ", activeGroup='" + activeGroup + '\'' +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}

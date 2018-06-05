package motivator.api.models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "groups")
public class Group {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String name;
    private String gitHubGroupRep;
    private String trelloGroup;
    private String slackGroupHook;

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

    public String getGitHubGrupRep() {
        return gitHubGroupRep;
    }

    public void setGitHubGrupRep(String gitHubGrupRep) {
        this.gitHubGroupRep = gitHubGrupRep;
    }

    public String getTrelloGroup() {
        return trelloGroup;
    }

    public void setTrelloGroup(String trelloGroup) {
        this.trelloGroup = trelloGroup;
    }

    public String getSlackGroupHook() {
        return slackGroupHook;
    }

    public void setSlackGroupHook(String slackGroupHook) {
        this.slackGroupHook = slackGroupHook;
    }
}
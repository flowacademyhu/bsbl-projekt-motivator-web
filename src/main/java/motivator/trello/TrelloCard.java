package motivator.trello;

public class TrelloCard {
    private String id;
    private String cardName;
    private boolean hasDue;
    private java.util.Date dueDate;
    private boolean dueComp;
    private java.util.Date lastActivity;

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    private String listName;

    public boolean isHasDue() {
        return hasDue;
    }

    public void setHasDue(boolean hasDue) {
        this.hasDue = hasDue;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public java.util.Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(java.util.Date dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isDueComp() {
        return dueComp;
    }

    public void setDueComp(boolean dueComp) {
        this.dueComp = dueComp;
    }

    public java.util.Date getLastActivity() {
        return lastActivity;
    }

    public void setLastActivity(java.util.Date lastActivity) {
        this.lastActivity = lastActivity;
    }
}

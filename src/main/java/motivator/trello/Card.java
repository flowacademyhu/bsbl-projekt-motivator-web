package motivator.trello;

import java.sql.DatabaseMetaData;
import java.sql.Date;

public class Card {
    private String id;
    private String cardName;
    private Date dueDate;
    private boolean dueComp;
    private Date lastActivity;

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

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isDueComp() {
        return dueComp;
    }

    public void setDueComp(boolean dueComp) {
        this.dueComp = dueComp;
    }

    public Date getLastActivity() {
        return lastActivity;
    }

    public void setLastActivity(Date lastActivity) {
        this.lastActivity = lastActivity;
    }
}
/*figy az kéne, hogy csinálni egy Card classt amiben a különöbző metódusok eredményeit eltároljuk
 (egy nagy kártya osztály annyi féle attribútummal amennyi adatot kinyerünk róla)
és azok a metódusok úgy kéne h működjenek, hogy paraméternek kapnak egy kártyát
és annak beállítják a set-terrel a megfelelő értékét
ugye ezt beszéltük j a tábláról nem kell külön object
de a kártyáktól kapunk annyi adatot, hogy egyszerűbb objectként tárolni
mint mindig Stringet parseolni*/
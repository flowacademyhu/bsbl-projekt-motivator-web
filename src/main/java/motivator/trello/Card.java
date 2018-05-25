package motivator.trello;

import java.sql.DatabaseMetaData;
import java.sql.Date;

public class Card {
    String id;
    String cardName;
    Date dueDate;
    boolean dueComp;
    Date lastActivity;
}
/*figy az kéne, hogy csinálni egy Card classt amiben a különöbző metódusok eredményeit eltároljuk
 (egy nagy kártya osztály annyi féle attribútummal amennyi adatot kinyerünk róla)
és azok a metódusok úgy kéne h működjenek, hogy paraméternek kapnak egy kártyát
és annak beállítják a set-terrel a megfelelő értékét
ugye ezt beszéltük j a tábláról nem kell külön object
de a kártyáktól kapunk annyi adatot, hogy egyszerűbb objectként tárolni
mint mindig Stringet parseolni*/
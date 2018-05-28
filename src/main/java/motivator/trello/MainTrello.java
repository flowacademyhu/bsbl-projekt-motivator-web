package motivator.trello;

import java.io.IOException;
import java.text.ParseException;

public class MainTrello {
    public static void main(String[] args) throws IOException, ParseException {
        Api api = new Api();
        Card card = new Card();
        api.authBuilder("e8c4fc3da567b572e098c47cf43b1064", "d9b14b0aaed586ba684d85db86b47435a2fb551332945c1408146ba7887e7f9e");

        api.cardName("Fcyq2AIW", api.getTokenKey(), card);
        //System.out.println(card.getCardName());
        //api.cardDue("Fcyq2AIW", api.getTokenKey(), card);
        //api.cardDueComp("Fcyq2AIW", api.getTokenKey(), card);
        //api.listName("Fcyq2AIW", api.getTokenKey(), card);
        //api.getDateLastAct("Fcyq2AIW", api.getTokenKey(), card);
        //System.out.println(api.cardN);
        //System.out.println(api.dueComp);
        api.cardId("Fcyq2AIW", api.getTokenKey(), card);
    }
}

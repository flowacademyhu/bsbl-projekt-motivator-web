package motivator.trello;

import java.io.IOException;

public class MainTrello {
    public static void main(String[] args) throws IOException {
        Api api = new Api();
        api.authBuilder("e8c4fc3da567b572e098c47cf43b1064", "d9b14b0aaed586ba684d85db86b47435a2fb551332945c1408146ba7887e7f9e");

        api.cardName("Fcyq2AIW", api.getTokenKey());
        api.cardDue("Fcyq2AIW", api.getTokenKey());
        api.cardDueComp("Fcyq2AIW", api.getTokenKey());
        api.idList("Fcyq2AIW", api.getTokenKey());
        api.listName("Fcyq2AIW", api.getTokenKey());
        api.getDateLastAct("Fcyq2AIW", api.getTokenKey());

    }
}

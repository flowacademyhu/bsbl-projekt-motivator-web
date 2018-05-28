package motivator.trello;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class ApiTest {
    Api api = new Api();
    Card card = new Card();

    public ApiTest() throws IOException {
    }

    @Test
    public void cardName() throws IOException {
        api.authBuilder("e8c4fc3da567b572e098c47cf43b1064", "d9b14b0aaed586ba684d85db86b47435a2fb551332945c1408146ba7887e7f9e");
        String beforeName = card.getCardName();
        api.cardName("Fcyq2AIW", api.getTokenKey(), card);
        String afterName = card.getCardName();
        assertNotEquals(beforeName, afterName);
    }

    @Test
    public void cardDueComp() throws IOException {
        api.authBuilder("e8c4fc3da567b572e098c47cf43b1064", "d9b14b0aaed586ba684d85db86b47435a2fb551332945c1408146ba7887e7f9e");
        boolean before = card.isDueComp();
        api.cardDueComp("Fcyq2AIW", api.getTokenKey(), card);
        boolean after = card.isDueComp();
        assertEquals(before, after);
        }
    }

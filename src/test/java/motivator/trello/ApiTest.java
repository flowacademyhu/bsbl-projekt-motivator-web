package motivator.trello;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class ApiTest {
    TrelloApi trelloApi = new TrelloApi();
    TrelloCard trelloCard = new TrelloCard();

    public ApiTest() throws IOException {
    }

    @Test
    public void cardName() throws IOException {
        trelloApi.authBuilder("e8c4fc3da567b572e098c47cf43b1064", "d9b14b0aaed586ba684d85db86b47435a2fb551332945c1408146ba7887e7f9e");
        String beforeName = trelloCard.getCardName();
        trelloApi.cardName("Fcyq2AIW", trelloApi.getTokenKey(), trelloCard);
        String afterName = trelloCard.getCardName();
        assertNotEquals(beforeName, afterName);
    }

    @Test
    public void cardDueComp() throws IOException {
        trelloApi.authBuilder("e8c4fc3da567b572e098c47cf43b1064", "d9b14b0aaed586ba684d85db86b47435a2fb551332945c1408146ba7887e7f9e");
        boolean before = trelloCard.isDueComp();
        trelloApi.cardDueComp("Fcyq2AIW", trelloApi.getTokenKey(), trelloCard);
        boolean after = trelloCard.isDueComp();
        assertEquals(before, after);
        }
    }

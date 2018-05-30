package motivator.trello;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TrelloApi {
    public TrelloApi() {
    }

    public String getTokenKey() {
        return tokenKey;
    }

    public void setTokenKey(String tokenKey) {
        this.tokenKey = tokenKey;
    }

    private String tokenKey = "";
    public String authBuilder(String key, String token) {
        tokenKey = "&key=" + key + "&token=" + token;
        return tokenKey;
    }

    // Returns the Trello Card name
    public void cardName(String cardId, String tokenKey, TrelloCard trelloCard) throws IOException {
        String url = "https://api.trello.com/1/cards/" + cardId + "?fields=name" + tokenKey;
        URLConnection connection = new URL(url).openConnection();
        InputStream response = connection.getInputStream();
        String responseBody = "";
        try (Scanner scanner = new Scanner(response)) {
            responseBody = scanner.useDelimiter("\\A").next();
        }
        String repBody = responseBody.substring(41).replace("\"}", "");
        trelloCard.setCardName(repBody);
    }

    // Passes Id to the cardId
    public void cardId(String cardId, String tokenKey, TrelloCard trelloCard) throws IOException {
        String url = "https://api.trello.com/1/cards/" + cardId + "?fields=name" + tokenKey;
        URLConnection connection = new URL(url).openConnection();
        InputStream response = connection.getInputStream();
        String responseBody = "";
        try (Scanner scanner = new Scanner(response)) {
            responseBody = scanner.useDelimiter("\\A").next();
        }
        String repBody = responseBody.substring(7, 31).replace("\"}", "");
        trelloCard.setId(repBody);
    }

    // Returns if there is an issue date
    public void cardDue(String cardId, String tokenKey, TrelloCard trelloCard) throws IOException, ParseException {
        String url = "https://api.trello.com/1/cards/" + cardId + "/due?" + tokenKey;
        URLConnection connection = new URL(url).openConnection();
        InputStream response = connection.getInputStream();
        String responseBody = "";
        try (Scanner scanner = new Scanner(response)) {
            responseBody = scanner.useDelimiter("\\A").next();
        }
        String repBody = responseBody.substring(11).replace("}", "").replace("T", "").replace("Z", "");
        if (repBody.equals("null\n")) {
            trelloCard.setHasDue(false);
        } else {
            trelloCard.setHasDue(true);
            DateFormat df = new SimpleDateFormat("yyyy-MM-ddhh:mm", Locale.ENGLISH);
            Date result = df.parse(repBody);
            trelloCard.setDueDate(result);
        }
    }

    // Returns if the task was completed until due date
    public void cardDueComp(String cardId, String tokenKey, TrelloCard trelloCard) throws IOException {
        String url = "https://api.trello.com/1/cards/" + cardId + "/dueComplete?" + tokenKey;
        URLConnection connection = new URL(url).openConnection();
        InputStream response = connection.getInputStream();
        String responseBody = "";
        try (Scanner scanner = new Scanner(response)) {
            responseBody = scanner.useDelimiter("\\A").next();
        }
        String repBody = responseBody.substring(10).replace("}", "");
        Boolean bool = Boolean.parseBoolean(repBody);
        trelloCard.setDueComp(bool);
    }

    // Returns the Id and the username
    public void listName(String cardId, String tokenKey, TrelloCard trelloCard) throws IOException {
        String url = "https://api.trello.com/1/cards/" + cardId + "/list?fields=name" + tokenKey;
        URLConnection connection = new URL(url).openConnection();
        InputStream response = connection.getInputStream();
        String responseBody = "";
        try (Scanner scanner = new Scanner(response)) {
            responseBody = scanner.useDelimiter("\\A").next();
        }
        String repBody = responseBody.substring(41).replace("\"}", "");
        trelloCard.setListName(repBody);
    }

    // Returns the Id and the last activity
    public void getDateLastAct(String cardId, String tokenKey, TrelloCard trelloCard) throws IOException, ParseException {
        String url = "https://api.trello.com/1/cards/" + cardId + "?fields=dateLastActivity" + tokenKey;
        URLConnection connection = new URL(url).openConnection();
        InputStream response = connection.getInputStream();
        String responseBody = "";
        try (Scanner scanner = new Scanner(response)) {
            responseBody = scanner.useDelimiter("\\A").next();
        }
        String repBody = responseBody.substring(53).replace("T", "").replace("\"}", "");
        DateFormat df = new SimpleDateFormat("yyyy-MM-ddhh:mm", Locale.ENGLISH);
        Date result =  df.parse(repBody);
        trelloCard.setLastActivity(result);
    }

    // User relevant: Returns all user's card Id
    public String[] userAllCard(String username, String tokenKey) throws IOException { // username+id j?n vissza
        String url = "https://api.trello.com/1/members/" + username + "/cards?fields=id" + tokenKey;
        URLConnection connection = new URL(url).openConnection();
        InputStream response = connection.getInputStream();
        String responseBody = "";
        try (Scanner scanner = new Scanner(response)) {
            responseBody = scanner.useDelimiter("\\A").next();
        }
        String[] userAllCarsrep= responseBody.replace("[", "").replace("]", "").split(",");
        return userAllCarsrep;
    }

    // Returns all Id-s which are represented on the board
    public String[] boardAllCards(String boardId, String tokenKey) throws IOException { // username+id j?n vissza
        String url = "https://api.trello.com/1/boards/" + boardId + "/cards?fields=id" + tokenKey;
        URLConnection connection = new URL(url).openConnection();
        InputStream response = connection.getInputStream();
        String responseBody = "";
        try (Scanner scanner = new Scanner(response)) {
            responseBody = scanner.useDelimiter("\\A").next();
        }
        String[] boardAllCardsrep= responseBody.replace("[", "").replace("]", "").split(",");
        return boardAllCardsrep;
    }

    // Returns the card Ids where the user and board are sharing them
    ArrayList<String> ar = new ArrayList<>();
    public ArrayList boardUserCards(String[] boardCards, String[] userCards) {
        for(int i = 0; i < boardCards.length; i++) {
            if(Arrays.asList(userCards).contains(boardCards[i]))
                ar.add(boardCards[i]);
        }
        System.out.println(ar);
        return ar;
    }
}

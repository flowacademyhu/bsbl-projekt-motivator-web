package motivator.trello;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Api {
    public Api() throws IOException {
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

    // visszaadja a card name-et.
    public void cardName(String cardId, String tokenKey, Card card) throws IOException {
        String url = "https://api.trello.com/1/cards/" + cardId + "?fields=name" + tokenKey;
        URLConnection connection = new URL(url).openConnection();
        InputStream response = connection.getInputStream();
        String responseBody = "";
        try (Scanner scanner = new Scanner(response)) {
            responseBody = scanner.useDelimiter("\\A").next();
        }
        String repBody = responseBody.substring(41).replace("\"}", "");
        card.setCardName(repBody);
    }

    //csak az ID-t adja át a cardId-nak
    public void cardId(String cardId, String tokenKey, Card card) throws IOException {
        String url = "https://api.trello.com/1/cards/" + cardId + "?fields=name" + tokenKey;
        URLConnection connection = new URL(url).openConnection();
        InputStream response = connection.getInputStream();
        String responseBody = "";
        try (Scanner scanner = new Scanner(response)) {
            responseBody = scanner.useDelimiter("\\A").next();
        }
        String repBody = responseBody.substring(7, 31).replace("\"}", "");
        card.setId(repBody);
    }

    //visszaadja van-e hat?rid?
    public void cardDue(String cardId, String tokenKey, Card card) throws IOException, ParseException {
        String url = "https://api.trello.com/1/cards/" + cardId + "/due?" + tokenKey;
        URLConnection connection = new URL(url).openConnection();
        InputStream response = connection.getInputStream();
        String responseBody = "";
        try (Scanner scanner = new Scanner(response)) {
            responseBody = scanner.useDelimiter("\\A").next();
        }
        String repBody = responseBody.substring(11).replace("}", "").replace("T", "").replace("Z", "");
        if (repBody.equals("null\n")) {
            card.setHasDue(false);
        } else {
            card.setHasDue(true);
            DateFormat df = new SimpleDateFormat("yyyy-MM-ddhh:mm", Locale.ENGLISH);
            Date result = df.parse(repBody);
            card.setDueDate(result);
        }
    }

    //visszaadja siker?lt-e teljes?teni hat?rid?re
    public void cardDueComp(String cardId, String tokenKey, Card card) throws IOException {
        String url = "https://api.trello.com/1/cards/" + cardId + "/dueComplete?" + tokenKey;
        URLConnection connection = new URL(url).openConnection();
        InputStream response = connection.getInputStream();
        String responseBody = "";
        try (Scanner scanner = new Scanner(response)) {
            responseBody = scanner.useDelimiter("\\A").next();
        }
        String repBody = responseBody.substring(10).replace("}", "");
        Boolean bool = Boolean.parseBoolean(repBody);
        card.setDueComp(bool);
    }

    //visszadja az ID-t ?s a name-et
    public void listName(String cardId, String tokenKey, Card card) throws IOException {
        String url = "https://api.trello.com/1/cards/" + cardId + "/list?fields=name" + tokenKey;
        URLConnection connection = new URL(url).openConnection();
        InputStream response = connection.getInputStream();
        String responseBody = "";
        try (Scanner scanner = new Scanner(response)) {
            responseBody = scanner.useDelimiter("\\A").next();
        }
        String repBody = responseBody.substring(41).replace("\"}", "");
        card.setListName(repBody);
    }

    //visszadja az ID-t ?s az utols? activityt
    public void getDateLastAct(String cardId, String tokenKey, Card card) throws IOException, ParseException {
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
        card.setLastActivity(result);
    }

    // user relevant.
    // visszaadja a user ?sszes cardj?nak ID-j?t.
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

    //visszaadja a board-on szerepl? cardok ?sszes ID-j?t
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

    //visszaadja a user ?s a board k?z?s cardjainak ID-j?t
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
package motivator.trello;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

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
            System.out.println(responseBody);
        }
        card.setCardName(responseBody);
    }

    //visszaadja van-e hat?rid?
    public String cardDue(String cardId, String tokenKey) throws IOException {
        String url = "https://api.trello.com/1/cards/" + cardId + "/due?" + tokenKey;
        URLConnection connection = new URL(url).openConnection();
        InputStream response = connection.getInputStream();
        String responseBody = "";
        try (Scanner scanner = new Scanner(response)) {
            responseBody = scanner.useDelimiter("\\A").next();
            System.out.println(responseBody);
        }
        return responseBody;
    }

    //visszaadja siker?lt-e teljes?teni hat?rid?re
    String dueComp;
    public String cardDueComp(String cardId, String tokenKey) throws IOException {
        String url = "https://api.trello.com/1/cards/" + cardId + "/dueComplete?" + tokenKey;
        URLConnection connection = new URL(url).openConnection();
        InputStream response = connection.getInputStream();
        String responseBody = "";
        try (Scanner scanner = new Scanner(response)) {
            responseBody = scanner.useDelimiter("\\A").next();
            //System.out.println(responseBody);
        }
        dueComp = responseBody;
        return dueComp;
    }


    //visszaadja a list ID-t
    public void idList(String cardId, String tokenKey, Card card) throws IOException {
        String url = "https://api.trello.com/1/cards/" + cardId + "/list?fields=id" + tokenKey;
        URLConnection connection = new URL(url).openConnection();
        InputStream response = connection.getInputStream();
        String responseBody = "";
        try (Scanner scanner = new Scanner(response)) {
            responseBody = scanner.useDelimiter("\\A").next();
            System.out.println(responseBody);
        }
        card.setCardName(responseBody);
    }

    //visszadja az ID-t ?s a name-et
    public String listName(String cardId, String tokenKey) throws IOException {
        String url = "https://api.trello.com/1/cards/" + cardId + "/list?fields=name" + tokenKey;
        URLConnection connection = new URL(url).openConnection();
        InputStream response = connection.getInputStream();
        String responseBody = "";
        try (Scanner scanner = new Scanner(response)) {
            responseBody = scanner.useDelimiter("\\A").next();
            System.out.println(responseBody);
        }
        return responseBody;
    }

    //visszadja az ID-t ?s az utols? activityt
    public String getDateLastAct(String cardId, String tokenKey) throws IOException {
        String url = "https://api.trello.com/1/cards/" + cardId + "?fields=dateLastActivity" + tokenKey;
        URLConnection connection = new URL(url).openConnection();
        InputStream response = connection.getInputStream();
        String responseBody = "";
        try (Scanner scanner = new Scanner(response)) {
            responseBody = scanner.useDelimiter("\\A").next();
            System.out.println(responseBody);
        }
        return responseBody;
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
            //System.out.println(responseBody.replace("[", "").replace("]", ""));
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
            //System.out.println(responseBody.replace("[", "").replace("]", ""));
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
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

    // visszaadja a card name-et.
    public String cardName(String cardId, String key, String token) throws IOException {
        String url = "https://api.trello.com/1/cards/" + cardId + "?fields=name&key=" + key + "&token=" + token;
        URLConnection connection = new URL(url).openConnection();
        InputStream response = connection.getInputStream();
        String responseBody = "";
        try (Scanner scanner = new Scanner(response)) {
            responseBody = scanner.useDelimiter("\\A").next();
            System.out.println(responseBody);
        }
        return responseBody;
    }

    //visszaadja van-e hat?rid?
    public String cardDue(String cardId, String key, String token) throws IOException {
        String url = "https://api.trello.com/1/cards/" + cardId + "/due?&key=" + key + "&token=" + token;
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
    public String cardDueComp(String cardId, String key, String token) throws IOException {
        String url = "https://api.trello.com/1/cards/" + cardId + "/dueComplete?&key=" + key + "&token=" + token;
        URLConnection connection = new URL(url).openConnection();
        InputStream response = connection.getInputStream();
        String responseBody = "";
        try (Scanner scanner = new Scanner(response)) {
            responseBody = scanner.useDelimiter("\\A").next();
            System.out.println(responseBody);
        }
        return responseBody;
    }

    //visszaadja a list ID-t
    public String idList(String cardId, String key, String token) throws IOException {
        String url = "https://api.trello.com/1/cards/" + cardId + "/list?fields=id&key=" + key + "&token=" + token;
        URLConnection connection = new URL(url).openConnection();
        InputStream response = connection.getInputStream();
        String responseBody = "";
        try (Scanner scanner = new Scanner(response)) {
            responseBody = scanner.useDelimiter("\\A").next();
            System.out.println(responseBody);
        }
        return responseBody;
    }

    //visszadja az ID-t ?s a name-et
    public String listName(String cardId, String key, String token) throws IOException {
        String url = "https://api.trello.com/1/cards/" + cardId + "/list?fields=name&key=" + key + "&token=" + token;
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
    public String getDateLastAct(String cardId, String key, String token) throws IOException {
        String url = "https://api.trello.com/1/cards/" + cardId + "?fields=dateLastActivity&key=" + key + "&token=" + token;
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
    public String[] userAllCard(String username, String key, String token) throws IOException { // username+id j?n vissza
        String url = "https://api.trello.com/1/members/" + username + "/cards?fields=id&key=" + key + "&token=" + token;
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
    public String[] boardAllCards(String boardId, String key, String token) throws IOException { // username+id j?n vissza
        String url = "https://api.trello.com/1/boards/" + boardId + "/cards?fields=id&key=" + key + "&token=" + token;
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
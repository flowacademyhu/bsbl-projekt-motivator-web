package java.motivator.trello;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class Api {

    public String cardName(String cardId, String key, String token) throws IOException {
        String url = "https://api.trello.com/1/cards/" + cardId + "/name?&key=" + key + "&token=" + token;
        URLConnection connection = new URL(url + key + token).openConnection();
        InputStream response = connection.getInputStream();

        String responseBody = "";
        try (Scanner scanner = new Scanner(response)) {
            responseBody = scanner.useDelimiter("\\A").next();
            System.out.println(responseBody);
        }
        return responseBody;
    }

    public String cardDue(String cardId, String key, String token) throws IOException {
        String url = "https://api.trello.com/1/cards/" + cardId + "/due?&key=" + key + "&token=" + token;
        URLConnection connection = new URL(url + key + token).openConnection();
        InputStream response = connection.getInputStream();

        String responseBody = "";
        try (Scanner scanner = new Scanner(response)) {
            responseBody = scanner.useDelimiter("\\A").next();
            System.out.println(responseBody);
        }
        return responseBody;
    }

    public String cardDueComp(String cardId, String key, String token) throws IOException {
        String url = "https://api.trello.com/1/cards/" + cardId + "/dueComplete?&key=" + key + "&token=" + token;
        URLConnection connection = new URL(url + key + token).openConnection();
        InputStream response = connection.getInputStream();

        String responseBody = "";
        try (Scanner scanner = new Scanner(response)) {
            responseBody = scanner.useDelimiter("\\A").next();
            System.out.println(responseBody);
        }
        return responseBody;
    }

    public String idList(String cardId, String key, String token) throws IOException {
        String url = "https://api.trello.com/1/cards/" + cardId + "/list?fields=id&key=" + key + "&token=" + token;
        URLConnection connection = new URL(url + key + token).openConnection();
        InputStream response = connection.getInputStream();

        String responseBody = "";
        try (Scanner scanner = new Scanner(response)) {
            responseBody = scanner.useDelimiter("\\A").next();
            System.out.println(responseBody);
        }
        return responseBody;
    }

    public String listName(String cardId, String key, String token) throws IOException {
        String url = "https://api.trello.com/1/cards/" + cardId + "/list?fields=name&key=" + key + "&token=" + token;
        URLConnection connection = new URL(url + key + token).openConnection();
        InputStream response = connection.getInputStream();

        String responseBody = "";
        try (Scanner scanner = new Scanner(response)) {
            responseBody = scanner.useDelimiter("\\A").next();
            System.out.println(responseBody);
        }
        return responseBody;
    }

    public String getDateLastAct(String cardId, String key, String token) throws IOException {
        String url = "https://api.trello.com/1/cards/" + cardId + "?fields=dateLastActiviy&key=" + key + "&token=" + token;
        URLConnection connection = new URL(url + key + token).openConnection();
        InputStream response = connection.getInputStream();

        String responseBody = "";
        try (Scanner scanner = new Scanner(response)) {
            responseBody = scanner.useDelimiter("\\A").next();
            System.out.println(responseBody);
        }
        return responseBody;
    }

    // user relevant
    public String userAllCard(String username, String key, String token) throws IOException { // username+id jön vissza
        String url = "https://api.trello.com/1/members/" + username + "/cards?fields=name&key=" + key + "&token=" + token;
        URLConnection connection = new URL(url + key + token).openConnection();
        InputStream response = connection.getInputStream();

        String responseBody = "";
        try (Scanner scanner = new Scanner(response)) {
            responseBody = scanner.useDelimiter("\\A").next();
            System.out.println(responseBody);
        }
        return responseBody;
    }

    public String boardAllCards(String boardId, String key, String token) throws IOException { // username+id jön vissza
        String url = "https://api.trello.com/1/boards/" + boardId + "/cards?fields=name&key=" + key + "&token=" + token;
        URLConnection connection = new URL(url + key + token).openConnection();
        InputStream response = connection.getInputStream();

        String responseBody = "";
        try (Scanner scanner = new Scanner(response)) {
            responseBody = scanner.useDelimiter("\\A").next();
            System.out.println(responseBody);
        }
        return responseBody;
    }
}

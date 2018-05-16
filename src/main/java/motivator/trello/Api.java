package motivator.trello;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class Api {

    public String getCardName(int id, String key, String token) throws IOException {
        String url = "https://api.trello.com/1/cards/" + id + "/name?" + key + "=e8c4fc3da567b572e098c47cf43b1064&" + token + "=d9b14b0aaed586ba684d85db86b47435a2fb551332945c1408146ba7887e7f9e";
        URLConnection connection = new URL(url + key + token).openConnection();
        InputStream response = connection.getInputStream();

        String responseBody = "";
        try (Scanner scanner = new Scanner(response)) {
            responseBody = scanner.useDelimiter("\\A").next();
            System.out.println(responseBody);
        }
        return responseBody;
    }

    public String getDateLastAct(int id, String key, String token) throws IOException {
        String url = "https://api.trello.com/1/cards/" + id + "/dateLastActivity?" + key + "=e8c4fc3da567b572e098c47cf43b1064&" + token + "=d9b14b0aaed586ba684d85db86b47435a2fb551332945c1408146ba7887e7f9e";
        URLConnection connection = new URL(url + key + token).openConnection();
        InputStream response = connection.getInputStream();

        String responseBody = "";
        try (Scanner scanner = new Scanner(response)) {
            responseBody = scanner.useDelimiter("\\A").next();
            System.out.println(responseBody);
        }
        return responseBody;
    }

    public String getDue(int id, String key, String token) throws IOException {
        String url = "https://api.trello.com/1/cards/" + id + "/due?" + key + "=e8c4fc3da567b572e098c47cf43b1064&" + token + "=d9b14b0aaed586ba684d85db86b47435a2fb551332945c1408146ba7887e7f9e";
        URLConnection connection = new URL(url + key + token).openConnection();
        InputStream response = connection.getInputStream();

        String responseBody = "";
        try (Scanner scanner = new Scanner(response)) {
            responseBody = scanner.useDelimiter("\\A").next();
            System.out.println(responseBody);
        }
        return responseBody;
    }

    public String getDueComp(int id, String key, String token) throws IOException {
        String url = "https://api.trello.com/1/cards/" + id + "/dueComplete?" + key + "=e8c4fc3da567b572e098c47cf43b1064&" + token + "=d9b14b0aaed586ba684d85db86b47435a2fb551332945c1408146ba7887e7f9e";
        URLConnection connection = new URL(url + key + token).openConnection();
        InputStream response = connection.getInputStream();

        String responseBody = "";
        try (Scanner scanner = new Scanner(response)) {
            responseBody = scanner.useDelimiter("\\A").next();
            System.out.println(responseBody);
        }
        return responseBody;
    }

    public String getIdList(int id, String key, String token) throws IOException {
        String url = "https://api.trello.com/1/cards/" + id + "/idList?" + key + "=e8c4fc3da567b572e098c47cf43b1064&" + token + "=d9b14b0aaed586ba684d85db86b47435a2fb551332945c1408146ba7887e7f9e";
        URLConnection connection = new URL(url + key + token).openConnection();
        InputStream response = connection.getInputStream();

        String responseBody = "";
        try (Scanner scanner = new Scanner(response)) {
            responseBody = scanner.useDelimiter("\\A").next();
            System.out.println(responseBody);
        }
        return responseBody;
    }

    public String getList(int id, String key, String token) throws IOException {
        String url = "https://api.trello.com/1/cards/" + id + "/list?" + key + "=e8c4fc3da567b572e098c47cf43b1064&" + token + "=d9b14b0aaed586ba684d85db86b47435a2fb551332945c1408146ba7887e7f9e";
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

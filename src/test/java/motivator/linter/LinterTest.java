package motivator.linter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinterTest {
    private String testText = "package motivator.trello;\n" +
            "import java.io.IOException;\n" +
            "import java.io.InputStream;\n" +
            "import java.net.URL;\n" +
            "import java.net.URLConnection;\n" +
            "import java.util.ArrayList;\n" +
            "import java.util.Arrays;\n" +
            "import java.util.Scanner;\n" +
            "\n" +
            "public class Api {\n" +
            "    public Api() throws IOException {\n" +
            "    }\n" +
            "\n" +
            "    public String getTokenKey() {\n" +
            "        return tokenKey;\n" +
            "    }\n" +
            "\n" +
            "    public void setTokenKey(String tokenKey) {\n" +
            "        this.tokenKey = tokenKey;\n" +
            "    }\n" +
            "\n" +
            "    private String tokenKey = \"\";\n" +
            "    public String authBuilder(String key, String token) {\n" +
            "        tokenKey = \"&key=\" + key + \"&token=\" + token;\n" +
            "        return tokenKey;\n" +
            "    }\n" +
            "\n" +
            "    // visszaadja a card name-et.\n" +
            "    public void cardName(String cardId, String tokenKey, Card card) throws IOException {\n" +
            "        String url = \"https://api.trello.com/1/cards/\" + cardId + \"?fields=name\" + tokenKey;\n" +
            "        URLConnection connection = new URL(url).openConnection();\n" +
            "        InputStream response = connection.getInputStream();\n" +
            "        String responseBody = \"\";\n" +
            "        try (Scanner scanner = new Scanner(response)) {\n" +
            "            responseBody = scanner.useDelimiter(\"\\\\A\").next();\n" +
            "            System.out.println(responseBody);\n" +
            "        }\n" +
            "        card.setCardName(responseBody);\n" +
            "    }\n" +
            "\n" +
            "    //visszaadja van-e hat?rid?\n" +
            "    public String cardDue(String cardId, String tokenKey) throws IOException {\n" +
            "        String url = \"https://api.trello.com/1/cards/\" + cardId + \"/due?\" + tokenKey;\n" +
            "        URLConnection connection = new URL(url).openConnection();\n" +
            "        InputStream response = connection.getInputStream();\n" +
            "        String responseBody = \"\";\n" +
            "        try (Scanner scanner = new Scanner(response)) {\n" +
            "            responseBody = scanner.useDelimiter(\"\\\\A\").next();\n" +
            "            System.out.println(responseBody);\n" +
            "        }\n" +
            "        return responseBody;\n" +
            "    }\n" +
            "\n" +
            "    //visszaadja siker?lt-e teljes?teni hat?rid?re\n" +
            "    String dueComp;\n" +
            "    public String cardDueComp(String cardId, String tokenKey) throws IOException {\n" +
            "        String url = \"https://api.trello.com/1/cards/\" + cardId + \"/dueComplete?\" + tokenKey;\n" +
            "        URLConnection connection = new URL(url).openConnection();\n" +
            "        InputStream response = connection.getInputStream();\n" +
            "        String responseBody = \"\";\n" +
            "        try (Scanner scanner = new Scanner(response)) {\n" +
            "            responseBody = scanner.useDelimiter(\"\\\\A\").next();\n" +
            "            //System.out.println(responseBody);\n" +
            "        }\n" +
            "        dueComp = responseBody;\n" +
            "        return dueComp;\n" +
            "    }\n" +
            "\n" +
            "\n" +
            "    //visszaadja a list ID-t\n" +
            "    public void idList(String cardId, String tokenKey, Card card) throws IOException {\n" +
            "        String url = \"https://api.trello.com/1/cards/\" + cardId + \"/list?fields=id\" + tokenKey;\n" +
            "        URLConnection connection = new URL(url).openConnection();\n" +
            "        InputStream response = connection.getInputStream();\n" +
            "        String responseBody = \"\";\n" +
            "        try (Scanner scanner = new Scanner(response)) {\n" +
            "            responseBody = scanner.useDelimiter(\"\\\\A\").next();\n" +
            "            System.out.println(responseBody);\n" +
            "        }\n" +
            "        card.setCardName(responseBody);\n" +
            "    }\n" +
            "\n" +
            "    //visszadja az ID-t ?s a name-et\n" +
            "    public String listName(String cardId, String tokenKey) throws IOException {\n" +
            "        String url = \"https://api.trello.com/1/cards/\" + cardId + \"/list?fields=name\" + tokenKey;\n" +
            "        URLConnection connection = new URL(url).openConnection();\n" +
            "        InputStream response = connection.getInputStream();\n" +
            "        String responseBody = \"\";\n" +
            "        try (Scanner scanner = new Scanner(response)) {\n" +
            "            responseBody = scanner.useDelimiter(\"\\\\A\").next();\n" +
            "            System.out.println(responseBody);\n" +
            "        }\n" +
            "        return responseBody;\n" +
            "    }\n" +
            "\n" +
            "    //visszadja az ID-t ?s az utols? activityt\n" +
            "    public String getDateLastAct(String cardId, String tokenKey) throws IOException {\n" +
            "        String url = \"https://api.trello.com/1/cards/\" + cardId + \"?fields=dateLastActivity\" + tokenKey;\n" +
            "        URLConnection connection = new URL(url).openConnection();\n" +
            "        InputStream response = connection.getInputStream();\n" +
            "        String responseBody = \"\";\n" +
            "        try (Scanner scanner = new Scanner(response)) {\n" +
            "            responseBody = scanner.useDelimiter(\"\\\\A\").next();\n" +
            "            System.out.println(responseBody);\n" +
            "        }\n" +
            "        return responseBody;\n" +
            "    }\n" +
            "\n" +
            "    // user relevant.\n" +
            "    // visszaadja a user ?sszes cardj?nak ID-j?t.\n" +
            "    public String[] userAllCard(String username, String tokenKey) throws IOException { // username+id j?n vissza\n" +
            "        String url = \"https://api.trello.com/1/members/\" + username + \"/cards?fields=id\" + tokenKey;\n" +
            "        URLConnection connection = new URL(url).openConnection();\n" +
            "        InputStream response = connection.getInputStream();\n" +
            "        String responseBody = \"\";\n" +
            "        try (Scanner scanner = new Scanner(response)) {\n" +
            "            responseBody = scanner.useDelimiter(\"\\\\A\").next();\n" +
            "            //System.out.println(responseBody.replace(\"[\", \"\").replace(\"]\", \"\"));\n" +
            "        }\n" +
            "        String[] userAllCarsrep= responseBody.replace(\"[\", \"\").replace(\"]\", \"\").split(\",\");\n" +
            "        return userAllCarsrep;\n" +
            "    }\n" +
            "\n" +
            "    //visszaadja a board-on szerepl? cardok ?sszes ID-j?t\n" +
            "    public String[] boardAllCards(String boardId, String tokenKey) throws IOException { // username+id j?n vissza\n" +
            "        String url = \"https://api.trello.com/1/boards/\" + boardId + \"/cards?fields=id\" + tokenKey;\n" +
            "        URLConnection connection = new URL(url).openConnection();\n" +
            "        InputStream response = connection.getInputStream();\n" +
            "        String responseBody = \"\";\n" +
            "        try (Scanner scanner = new Scanner(response)) {\n" +
            "            responseBody = scanner.useDelimiter(\"\\\\A\").next();\n" +
            "            //System.out.println(responseBody.replace(\"[\", \"\").replace(\"]\", \"\"));\n" +
            "        }\n" +
            "        String[] boardAllCardsrep= responseBody.replace(\"[\", \"\").replace(\"]\", \"\").split(\",\");\n" +
            "        return boardAllCardsrep;\n" +
            "    }\n" +
            "\n" +
            "    //visszaadja a user ?s a board k?z?s cardjainak ID-j?t\n" +
            "    ArrayList<String> ar = new ArrayList<>();\n" +
            "    public ArrayList boardUserCards(String[] boardCards, String[] userCards) {\n" +
            "        for(int i = 0; i < boardCards.length; i++) {\n" +
            "            if(Arrays.asList(userCards).contains(boardCards[i]))\n" +
            "                ar.add(boardCards[i]);\n" +
            "        }\n" +
            "        System.out.println(ar);\n" +
            "        return ar;\n" +
            "    }\n" +
            "}";
    private Linter linter = new Linter();

    private String[] testRows = linter.rowSplitter(testText);
    private char[] testChars = linter.charSplitter(testText);

    /*
    @Test
    void rowCounter() {
        boolean test = linter.rowCounter(testRows);
        assertFalse(test);
    }
    */

    @Test
    void fileLength() {
        boolean test = linter.fileLength(testRows);
        assertFalse(test);
    }

    @Test
    void depthChecker() {
        boolean test = linter.depthChecker(testChars);
        assertTrue(test);
    }
}
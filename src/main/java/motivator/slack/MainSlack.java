package motivator.slack;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

public class MainSlack {

    public static void main(String[] args){
        Slack slack = new Slack();
        String hook = "https://hooks.slack.com/services/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";
        String text = "hello";
        try {
            slack.sendSlackMessage(slack.textToJson(text), hook);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}

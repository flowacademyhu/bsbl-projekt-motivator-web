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

public class Slack {
    private String text;
    private String url;
    private Long date;
    private Long time;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    String textToJson(){
        String messageParams = "{\"text\" : \"" + text + "\"}";
        return messageParams;
    }

    public void sendSlackMessage() throws IOException {

        HttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("Content-type", "application/json");
        StringEntity params1 = new StringEntity(textToJson(),"UTF-8");
        params1.setContentType("application/json");
        httpPost.setEntity(params1);
        httpclient.execute(httpPost);
    }

    public void scheduledSlackMessagePush() throws IOException {

        HttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("https://hooks.slack.com/services/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");
        httpPost.addHeader("Content-type", "application/json");
        StringEntity params1 = new StringEntity(autoMessagePush(), "UTF-8");
        params1.setContentType("application/json");
        httpPost.setEntity(params1);
        httpclient.execute(httpPost);
    }

    String autoMessagePush(){
        String messageParams = "{\"text\" : \"Don't forget to push.\"}";
        return messageParams;
    }

    String autoMessagePull(){
        String messageParams = "{\"text\" : \"Don't forget to pull.\"}";
        return messageParams;
    }

    String name(String uname){
        String userName = "#"+uname;
        return userName;
    }

    public void automatedSlackMessage(int hour, int minute, int second){

        Timer timer = new Timer();
        Calendar startingTime = Calendar.getInstance(TimeZone.getDefault());
        startingTime.set(Calendar.HOUR_OF_DAY, hour);
        startingTime.set(Calendar.MINUTE, minute);
        startingTime.set(Calendar.SECOND, second);

        timer.schedule(new TimerTask() {
            public void run() {
                try {
                    sendSlackMessage();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }, startingTime.getTime(), 1000 * 60 * 60 * 24);
    }
}

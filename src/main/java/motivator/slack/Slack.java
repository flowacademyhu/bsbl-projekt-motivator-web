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

    String textToJson(String text){
        String messageParams = "{\"text\" : \"" + text + "\"}";
        return messageParams;
    }

    public void sendSlackMessage(String params, String url) throws IOException {

        HttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("Content-type", "application/json");
        StringEntity params1 = new StringEntity(params,"UTF-8");
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

}

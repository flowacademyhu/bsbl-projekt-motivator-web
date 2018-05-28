package motivator.api.controllers;

import motivator.slack.Slack;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Controller
public class SlackController {

    @GetMapping(value="/channel/messages")
    public String greetingForm(Model model) {
        model.addAttribute("slack", new Slack());
        return "greeting";
    }

    @PostMapping(value="channel/messages")
    public String greetingSubmit(@ModelAttribute Slack slack) {
        try {
            slack.sendSlackMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "result";
    }

}

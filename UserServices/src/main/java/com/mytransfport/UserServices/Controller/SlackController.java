package com.mytransfport.UserServices.Controller;

import com.mytransfport.UserServices.Service.SlackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("SlackController")
@RequestMapping("/Slack")
public class SlackController {

    @Autowired
    private SlackService slackService;

    @PostMapping("/{message}")
    public ResponseEntity<String> sendSimpleMessageToSlack(@PathVariable(name="message") String message){
        slackService.sendMessageToSlack(message);
        return ResponseEntity.ok(message);
    }
}

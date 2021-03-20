package com.mytransfport.UserServices.Service;

import com.github.seratch.jslack.Slack;
import com.github.seratch.jslack.api.webhook.Payload;
import com.github.seratch.jslack.api.webhook.WebhookResponse;
import com.google.api.client.util.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SlackService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SlackService.class);
    private static final String NEW_LINE = "\n";

    private String urlSlackWebHook = "https://hooks.slack.com/services/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";

    public void sendMessageToSlack(String message) {
        StringBuilder messageBuider = new StringBuilder();
        messageBuider.append("*BusionAPP: " + message + NEW_LINE);

        process(messageBuider.toString());
    }

    private void process(String message) {
        Payload payload = Payload.builder()
                .channel("#app-alerts")
                .username("Bob Bot")
                .iconEmoji(":rocket:")
                .text(message)
                .build();
        try {
            WebhookResponse webhookResponse = Slack.getInstance().send(
                    urlSlackWebHook, payload);
            LOGGER.info("code -> " + webhookResponse.getCode());
            LOGGER.info("body -> " + webhookResponse.getBody());
        } catch (IOException e) {
            LOGGER.error("Unexpected Error! WebHook:" + urlSlackWebHook);
        }
    }
}

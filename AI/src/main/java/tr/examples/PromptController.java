package tr.examples;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.ChatClientResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PromptController {

     private static final String SYSTEM_PROMPT = "You are a helpful assistant answer only questions about java programming language.";

    private final ChatClient chatClient;

    public PromptController(@Qualifier("ollamaChatClient") ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/prompt")
    public String getPromptResult(@RequestParam String prompt) {
        return askLLM(prompt).chatResponse().getResult().getOutput().getText();
    }

    @GetMapping("/prompt/allFields")
    public ChatClientResponse getPromptResultWithAllFields(@RequestParam String prompt) {
        return askLLM(prompt);
    }

    private ChatClientResponse askLLM(String prompt) {
        return chatClient.prompt()
                .system(SYSTEM_PROMPT)
                .user(prompt)
                .call()
                .chatClientResponse();
    }
}
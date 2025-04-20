package com.mbk.learning.spring_ai_demo.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ollama/search")
public class OllamaController {

    private ChatClient _chatClientForOllama;

    private OllamaController(OllamaChatModel ollamaChatModel){
        this._chatClientForOllama = ChatClient.create(ollamaChatModel);
    }

    @GetMapping("/{promptMessage}")
    public ResponseEntity<String> ollamaSearchEngine(@PathVariable String promptMessage){
        ChatResponse response = _chatClientForOllama.prompt(promptMessage)
                .call()
                .chatResponse();
        return ResponseEntity.ok(response.getResult().getOutput().getText());
    }
}

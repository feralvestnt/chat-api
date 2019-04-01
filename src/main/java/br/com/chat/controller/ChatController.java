package br.com.chat.controller;

import br.com.chat.model.Chat;
import br.com.chat.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @PostMapping
    public Chat save(@RequestBody Chat cartao) {
        return chatService.save(cartao);
    }

    @GetMapping
    public List<Chat> getAll() {
        return chatService.getAll();
    }
}

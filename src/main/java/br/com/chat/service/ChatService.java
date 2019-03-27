package br.com.chat.service;

import br.com.chat.model.Chat;
import br.com.chat.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    @Autowired
    private ChatRepository chatRepository;

    public void save(Chat chat) {
        chatRepository.save(chat);
    }

}

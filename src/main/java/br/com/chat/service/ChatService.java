package br.com.chat.service;

import br.com.chat.model.Chat;
import br.com.chat.repository.ChatRepository;
import br.com.chat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class ChatService {

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityManager entityManager;

    public Chat save(Chat chat) {
        return chatRepository.save(chat);
    }

    public List<Chat> getAll() {
        return chatRepository.getAll();
    }
}

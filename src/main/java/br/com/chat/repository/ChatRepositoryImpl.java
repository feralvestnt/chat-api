package br.com.chat.repository;

import br.com.chat.model.Chat;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.List;

import static br.com.chat.model.QChat.chat;
import static br.com.chat.model.QUser.user;

public class ChatRepositoryImpl implements ChatRepositoryCustom {

    @Autowired
    EntityManager entityManager;

    public List<Chat> getAll() {
        return new JPAQueryFactory(entityManager)
                .select(chat)
                .from(chat)
                .join(chat.user, user).fetchJoin()
                .fetch();
    }
}

package com.ll.exam.app_2022_12_19_1_chat;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class ChatMessage {
    private long id;
    private LocalDateTime localDateTime;
    private String authorName;
    private String content;

    public ChatMessage(String authorName, String content){
        this(ChatMessageIdGenerator.genNextId(), LocalDateTime.now(), authorName, content);
    }

}


class ChatMessageIdGenerator {
    private static long id = 0;

    public static long genNextId(){
        return ++id;
    }
}
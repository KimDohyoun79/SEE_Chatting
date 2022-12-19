package com.ll.exam.app_2022_12_19_1_chat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/chat")
public class ChatController {

    private List<ChatMessage> chatMessagesList = new ArrayList<>();

    @AllArgsConstructor
    @Getter
    public static class WriteMessageResponse{
        private final long id;
    }


    @PostMapping("/writeMassage")
    @ResponseBody
    public RsData<WriteMessageResponse> writeMessage(){

        ChatMessage message = new ChatMessage("홍길동", "안녕하세요");

        chatMessagesList.add(message);

        return new RsData(
                "S-1",
                "메세지가 작성되었습니다.",
                new WriteMessageResponse(message.getId())
        );
    }

}

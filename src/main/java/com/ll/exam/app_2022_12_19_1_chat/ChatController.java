package com.ll.exam.app_2022_12_19_1_chat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/chat")
@Slf4j
public class ChatController {

    private List<ChatMessage> chatMessagesList = new ArrayList<>();

    @AllArgsConstructor
    @Getter
    public static class WriteMessageResponse{
        private final long id;
    }

//    @PostMapping("/writeMassage")
//    @ResponseBody
//    public RsData<WriteMessageResponse> writeMessage(){
//
//        ChatMessage message = new ChatMessage("홍길동", "안녕하세요");
//
//        chatMessagesList.add(message);
//
//        return new RsData<>(
//                "S-1",
//                "메세지가 작성되었습니다.",
//                new WriteMessageResponse(message.getId())
//        );
//    }


    // 7강
    @GetMapping("/message")
    @ResponseBody
    public RsData<List<ChatMessage>> message(){
        return new RsData<>(
                "S-1",
                "메세지 불러오기 성공",
                chatMessagesList
        );
    }





    @AllArgsConstructor
    @Getter
    public static class WriteMessageRequest{
        private final String authorName;
        private final String content;

    }

    // 8강 JSON형태로 전달
    @PostMapping("/writeMassage")
    @ResponseBody
    public RsData<WriteMessageResponse> writeMessage(@RequestBody WriteMessageRequest req){

        ChatMessage message = new ChatMessage(req.authorName, req.content);

        chatMessagesList.add(message);

        return new RsData<>(
                "S-1",
                "메세지가 작성되었습니다.",
                new WriteMessageResponse(message.getId())
        );
    }



    @AllArgsConstructor
    @Getter
    public static class MessagesResponse{
        private final List<ChatMessage> messages;
        private final long count;
    }

    @AllArgsConstructor
    @Getter
    public static class MessagesRequest{
          private final Long fromId;
    }

    //  9강, MessagesResponse 클래스
    @GetMapping("/message")
    @ResponseBody
    public RsData<MessagesResponse> message(MessagesRequest req){

        List<ChatMessage> messages = chatMessagesList;

        if (req.fromId != null) {
            // 해당 번호의 채팅메세지가 전체 리스트에서의 배열인덱스 번호를 구한다.
            // 없다면 -1
            int index = IntStream.range(0, messages.size())
                    .filter(i -> chatMessagesList.get(i).getId() == req.fromId)
                    .findFirst()
                    .orElse(-1);

            if (index != -1) {
                // 만약에 index가 있다면, 0번 부터 index 번 까지 제거한 리스트를 만든다.
                messages = messages.subList(index + 1, messages.size());
            }
        }

        return new RsData<>(
                "S-1",
                "메세지 불러오기 성공",
                new MessagesResponse(chatMessagesList, chatMessagesList.size())
        );
    }

}

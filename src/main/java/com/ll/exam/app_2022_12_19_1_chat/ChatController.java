package com.ll.exam.app_2022_12_19_1_chat;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/chat")
public class ChatController {

    @GetMapping("/writeMassage")
    @ResponseBody
    public RsData writeMessage(){
        return new RsData(
                "S-1",
                "메세지가 작성되었습니다.",
                null
        );
    }
}

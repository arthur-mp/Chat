package br.com.java.Spring.controller;

import br.com.java.Spring.model.Message;
import br.com.java.Spring.view.Window;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat_spring")
@RequiredArgsConstructor
public class ChatController {

    private  final Window window;

    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    public void receiveMessage(@RequestBody Message message){
        window.createMessage(message);
    }
}

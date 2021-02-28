package com.testboot.test2.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.testboot.test2.domain.Message;
import com.testboot.test2.domain.Views;
import com.testboot.test2.exceptions.NotFoundException;
import com.testboot.test2.repo.MessageRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("message")
public class MessageController {

    private final MessageRepo messageRepo;

    @Autowired
    public MessageController(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    public List<Map<String,String>> messages= new ArrayList<Map<String, String>>() {
        {
            add(new HashMap<String, String>() {{
                put("id", "1");
                put("text", "first message");
            }});
            add(new HashMap<String, String>() {{
                put("id", "2");
                put("text", "second message");
            }});
            add(new HashMap<String, String>() {{
                put("id", "3");
                put("text", "third message");
            }});
        }
    };



    @GetMapping
    @JsonView(Views.IdName.class)
    public List<Message> list(){
        return messageRepo.findAll();
    }
    @GetMapping("{id}")
    @JsonView(Views.FullMessage.class)
    public Message getOne
    (
        @PathVariable("id") Message message
    )
    {
        return message;
    }

    private Map<String, String> getMessage(String id) {
        return messages.stream()
                .filter(message -> message.get("id").equals(id))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }

    private int counter=4;
    @PostMapping
    public Message create(@RequestBody Message message){
        message.setCreationDate(LocalDateTime.now());
        return messageRepo.save(message);
    }
    @PutMapping("{id}")
    public Message update(
        @PathVariable("id") Message messageFromDb,
        @RequestBody Message message
    )
    {
        BeanUtils.copyProperties(message,messageFromDb,"id");
        
        return messageRepo.save(messageFromDb);
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Message message)
    {
        messageRepo.delete(message);
    }
}

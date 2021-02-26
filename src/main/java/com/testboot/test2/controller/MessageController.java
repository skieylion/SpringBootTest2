package com.testboot.test2.controller;

import com.sun.javafx.collections.MappingChange;
import com.testboot.test2.exceptions.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("message")
public class MessageController {
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
    public List<Map<String,String>> list(){
        return messages;
    }
    @GetMapping("{id}")
    public Map<String,String> getOne(@PathVariable String id){
        return getMessage(id);
    }

    private Map<String, String> getMessage(String id) {
        return messages.stream()
                .filter(message -> message.get("id").equals(id))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }

    private int counter=4;
    @PostMapping
    public Map<String,String> create(@RequestBody Map<String,String> message){
        System.out.println("TEST_REQ");
        message.put("id",String.valueOf(counter++));
        System.out.println(message.get("id"));
        System.out.println(message.get("text"));
        messages.add(message);
        System.out.println("TEST_REQ_3");
        return message;
    }
    @PutMapping("{id}")
    public Map<String,String> update(
            @PathVariable String id,
            @RequestBody Map<String,String> message)
    {
        Map<String,String> messageFromDb=getMessage(id);
        messageFromDb.putAll(message);
        messageFromDb.put("id",id);
        
        return message;
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable String id)
    {
        Map<String,String> message=getMessage(id);
        messages.remove(message);
    }
}

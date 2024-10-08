package com.example.springbootkafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//데이터를 리턴하는 컨트롤러: REST API를 만들기 위한 어노테이션
@RestController
@RequestMapping(value = "/kafka")
@Slf4j
@RequiredArgsConstructor
public class KafkaController {

    @Autowired
    private KafkaProducer producer;

    // POST 방식으로 요청이 오면 처리
    @PostMapping
    @ResponseBody
    public String sendMessage(@RequestParam("name") String name, @RequestParam("age") int age) {
        this.producer.sendMessage(name, age);
        return "success";
    }

}

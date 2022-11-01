package com.springboot.hello.controller;

import com.springboot.hello.domain.User;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/put-api")
public class PutController {
    @PutMapping("/member")
    public String putMember(@RequestBody Map<String, Object> putData){
        StringBuilder sb = new StringBuilder();

        putData.entrySet().forEach(map -> {
                sb.append(map.getKey() + " : " + map.getValue()+ "\n");}
        );
        return sb.toString();
    }

    @PutMapping(value = "member2")
    public String putDto (@RequestBody User memberDto){
        return memberDto.toString();
    }

}

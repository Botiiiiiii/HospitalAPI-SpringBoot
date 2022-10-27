package com.example.hello.controller;

import com.example.hello.dto.MemberDto;
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
    public String putDto (@RequestBody MemberDto memberDto){
        return memberDto.toString();
    }

}

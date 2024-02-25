package com.hazelcast.poc.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.HazelcastJsonValue;
import com.hazelcast.map.IMap;
import com.hazelcast.poc.dto.CacheDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/hz")
public class HazelcastCommandController {

    @Autowired
    private HazelcastInstance hazelcastInstance;

    @Autowired
    ObjectMapper objectMapper;
    static final String USERS_MAP="USER_MAP";

    @GetMapping(value = "/get")
    ResponseEntity<String> fetch(){
        return ResponseEntity.ok("ok");
    }
    @PostMapping(value = "/insert")
    ResponseEntity<String> insert(@RequestBody CacheDto dto) throws JsonProcessingException {

        IMap<String, HazelcastJsonValue> userMap= hazelcastInstance.getMap(USERS_MAP);
        HazelcastJsonValue jsonValue= new HazelcastJsonValue(objectMapper.writeValueAsString(dto));

        userMap.put(dto.getUsername(), jsonValue);
        return ResponseEntity.ok(userMap.get(dto.getUsername()).getValue());
    }
}

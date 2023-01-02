package com.nas.circuit.example.controller;


import com.nas.circuit.example.service.ExampleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ExampleController {

    private final ExampleService exampleService;

    @GetMapping("/albums")
    public String albums() {
        return exampleService.getAlbumList();
    }
}

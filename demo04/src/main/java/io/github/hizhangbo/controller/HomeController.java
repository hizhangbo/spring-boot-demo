package io.github.hizhangbo.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String index(@RequestBody(required = false) String id) {
        return "index";
    }

    @GetMapping("index")
    public String index2(@RequestParam(required = false) String id) {
        return "index";
    }

    @GetMapping("/e")
    public String error() {
        int i = 0;
        int j = 1 / i;

        return "error";
    }

    @GetMapping("/e2")
    public String error2() throws Exception {
        throw new Exception("123");
    }
}

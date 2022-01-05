package ru.ifmo.sproing.short_link.controller.v1;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ifmo.sproing.short_link.entity.LinkEntity;
import ru.ifmo.sproing.short_link.model.Link;
import ru.ifmo.sproing.short_link.service.LinkService;

import org.springframework.http.HttpHeaders;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("v1/")
@RequiredArgsConstructor
@Slf4j

public class V1LinkController {

    private final LinkService linkService;



    @ResponseBody
    @PostMapping("/create")
    public String createShortLink(@RequestBody Link link) {
        LinkEntity entity = linkService.findLong(link.getLongLink());

        if (entity != null) {
            return "http://localhost:8080/v1/s=" + entity.getShortLink();
        }
        System.out.println(link);
        link.setShortLink(genShortLink());
        linkService.createShortLink(link);

        return "http://localhost:8080/v1/s=" + link.getShortLink();
    }

    @ResponseBody
    @GetMapping("/testRequest")
    public void testReq() {
        System.out.println("was create test request");

    }
    @ResponseBody
    @GetMapping("/s={shortLink}")
    public ResponseEntity<Void> goToLongLink(@PathVariable(name = "shortLink") String shortLink) {
        LinkEntity entity = linkService.findShort(shortLink);
        final HttpHeaders httpHeaders = new HttpHeaders();
        if(entity == null) {
            return new ResponseEntity<>(httpHeaders, HttpStatus.UNAUTHORIZED);
        }

        httpHeaders.put("Location", List.of(entity.getLongLink()));
        return new ResponseEntity<>(httpHeaders, HttpStatus.MOVED_PERMANENTLY);
    }

    private String genShortLink() {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<20;i++){
            int number=random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

}

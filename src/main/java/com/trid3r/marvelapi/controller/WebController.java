package com.trid3r.marvelapi.controller;

import com.trid3r.marvelapi.domain.ResponseDomain;
import com.trid3r.marvelapi.service.MarvelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@CrossOrigin
@Slf4j
@RestController
@RequestMapping(value = "/characters/")
public class WebController {

    @Autowired private MarvelService marvelService;

    @GetMapping(path = "/findAll")
    public ResponseEntity<?> getFindAll(@RequestParam int limit, @RequestParam int offset) {

        log.info("Starting services - getFindAll");
        try{
            ResponseDomain response = marvelService.getCharacters(limit, offset);
            if(response.getCode() == 200){
                return ResponseEntity.ok(response.getData().getResults());
            } else {
                return ResponseEntity.noContent().build();
            }
        } catch (Exception e){
            log.error("Exception - getFindAll");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping(path = "/findById")
    public ResponseEntity<?> getFindById(@RequestParam int id) {

        log.info("Starting services - getFindById");
        try{
            ResponseDomain response = marvelService.getCharactersById(id);
            if(response.getCode() == 200){
                return ResponseEntity.ok(response.getData().getResults());
            } else {
                return ResponseEntity.noContent().build();
            }
        } catch (Exception e){
            log.error("Exception - getFindById");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}

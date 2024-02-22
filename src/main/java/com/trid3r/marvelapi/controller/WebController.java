package com.trid3r.marvelapi.controller;

import com.trid3r.marvelapi.domain.LogDomain;
import com.trid3r.marvelapi.domain.ResponseDomain;
import com.trid3r.marvelapi.service.LogService;
import com.trid3r.marvelapi.service.MarvelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@CrossOrigin
@Slf4j
@RestController
@RequestMapping(value = "/")
public class WebController {

    @Autowired private MarvelService marvelService;
    @Autowired private LogService logService;

    @GetMapping(path = "/logs/findAll")
    public ResponseEntity<?> getFindAllLogs() {

        log.info("Starting services - getFindAllLogs");
        try{
            return ResponseEntity.ok(logService.findAllLogs());
        } catch (Exception e){
            log.error("Exception - getFindAllLogs");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping(path = "/characters/findAll")
    public ResponseEntity<?> getFindAllCharacters(@RequestParam int limit, @RequestParam int offset) {

        log.info("Starting services - getFindAllCharacters");
        try{
            logService.addLog("getFindAllCharacters", ""); // Agrega Log por uso de peticion (getFindAllCharacters)

            ResponseDomain response = marvelService.getCharacters(limit, offset);
            if(response.getCode() == 200){
                return ResponseEntity.ok(response.getData());
            } else {
                return ResponseEntity.noContent().build();
            }
        } catch (Exception e){
            log.error("Exception - getFindAllCharacters");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping(path = "/characters/findById")
    public ResponseEntity<?> getFindById(@RequestParam int id) {

        log.info("Starting services - getFindById");
        try{
            logService.addLog("getFindById", String.valueOf(id)); // Agrega Log por uso de peticion (getFindById)

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

package com.trid3r.marvelapi.service;

import com.trid3r.marvelapi.domain.LogDomain;
import com.trid3r.marvelapi.repository.LogRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Service
public class LogServiceImpl implements LogService {

    @Autowired private LogRepository logRepository;

    public List<LogDomain> findAllLogs(){
        return logRepository.findAll();
    }

    public void addLog(String type, String id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); // Obtener los valores del JWT

        logRepository.save(
                LogDomain.builder()
                        .username(authentication.getName())
                        .type_search(type)
                        .search_id(id)
                        .datetime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MMM/yyyy h:mm:ss")))
                        .build());
    }
}

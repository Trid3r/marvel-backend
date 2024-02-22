package com.trid3r.marvelapi.service;

import com.trid3r.marvelapi.domain.LogDomain;

import java.util.List;

public interface LogService {

    List<LogDomain> findAllLogs();

    void addLog(String type, String id);

}

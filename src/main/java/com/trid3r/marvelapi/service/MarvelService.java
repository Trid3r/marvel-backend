package com.trid3r.marvelapi.service;

import com.trid3r.marvelapi.domain.ResponseDomain;
import com.trid3r.marvelapi.util.Helper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.time.Instant;

@Service
public class MarvelService {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${publicKey}")
    private String publicKey;

    @Value("${baseUrl}")
    private String baseUrl;

    public ResponseDomain getCharacters(int limit, int offset) {
        String ts = String.valueOf(Instant.now().toEpochMilli());
        String hash = Helper.generateMarvelAPIHash(ts);

        String url = UriComponentsBuilder.fromHttpUrl(baseUrl + "/v1/public/characters")
                .queryParam("ts", ts)
                .queryParam("apikey", publicKey)
                .queryParam("hash", hash)
                .queryParam("limit", limit)
                .queryParam("offset", offset)
                .queryParam("orderBy", "name")
                .toUriString();

        return restTemplate.getForObject(url, ResponseDomain.class);
    }

    public ResponseDomain getCharactersById(int id) {
        String ts = String.valueOf(Instant.now().toEpochMilli());
        String hash = Helper.generateMarvelAPIHash(ts);

        String url = UriComponentsBuilder.fromHttpUrl(baseUrl + "/v1/public/characters/" + id)
                .queryParam("ts", ts)
                .queryParam("apikey", publicKey)
                .queryParam("hash", hash)
                .toUriString();

        return restTemplate.getForObject(url, ResponseDomain.class);
    }

}

package com.trid3r.marvelapi.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Entity
@Table(name = "LOG")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LogDomain implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String type_search;
    private String search_id;
    private String datetime;

    public LogDomain (String username, String type_search, String search_id, String datetime){
        this.username = username;
        this.type_search = type_search;
        this.search_id = search_id;
        this.datetime = datetime;
    }
}

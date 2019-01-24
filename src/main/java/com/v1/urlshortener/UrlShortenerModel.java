package com.v1.urlshortener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Data
@Table(name="urls")
@Entity
@JsonIgnoreProperties({"randomString"})
public class UrlShortenerModel{

    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "original_link")
    private String originalLink;
    @Column(name = "short_link")
    private String shortLink;
    @Column(name = "random_string")
    private String randomString;
}

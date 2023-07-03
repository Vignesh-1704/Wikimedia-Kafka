package com.m2p.wikimediaconsumer.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "wikimedia_change")
public class WikimediaData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Lob  //Inorder to store large information we use @Lob Annotation
    @Column(length = 20000000)
    private String wikiEventData;
}

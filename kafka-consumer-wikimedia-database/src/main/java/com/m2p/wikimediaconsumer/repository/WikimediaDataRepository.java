package com.m2p.wikimediaconsumer.repository;

import com.m2p.wikimediaconsumer.entity.WikimediaData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WikimediaDataRepository extends JpaRepository<WikimediaData,Long> {
}

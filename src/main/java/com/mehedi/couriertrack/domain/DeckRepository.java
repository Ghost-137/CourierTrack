package com.mehedi.couriertrack.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface DeckRepository extends CrudRepository<Deck, Long> {
    List<Deck> findByAppUserUserName(String userName);
}

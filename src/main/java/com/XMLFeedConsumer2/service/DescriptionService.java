package com.XMLFeedConsumer2.service;

import com.XMLFeedConsumer2.model.Description;

import java.util.List;
import java.util.Optional;

public interface DescriptionService {
    List<Description> findAllDescriptions();
    Optional<Description> findById(Long id);
    Description save(Description description);
    Description update(Description description);
    Description findByDescriptionId(Long id);
}

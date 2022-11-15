package com.XMLFeedConsumer2.service;

import com.XMLFeedConsumer2.model.Description;
import com.XMLFeedConsumer2.repository.DescriptionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DescriptionServiceImpl implements DescriptionService{
    private final DescriptionRepository descriptionRepository;

    public DescriptionServiceImpl(DescriptionRepository descriptionRepository) {
        this.descriptionRepository = descriptionRepository;
    }

    public List<Description> findAllDescriptions() {
        return descriptionRepository.findAll();
    }

    @Override
    public Optional<Description> findById(Long id) {
        return descriptionRepository.findById(id);
    }

    @Override
    public Description save(Description description) {
        return descriptionRepository.save(description);
    }

    @Override
    public Description update(Description description) {
        return descriptionRepository.save(description);
    }

    @Override
    public Description findByDescriptionId(Long id) {
        return null;
    }
}

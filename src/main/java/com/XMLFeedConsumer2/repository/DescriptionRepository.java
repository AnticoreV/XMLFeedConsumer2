package com.XMLFeedConsumer2.repository;

import com.XMLFeedConsumer2.model.Description;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DescriptionRepository extends JpaRepository<Description, Long> {
}

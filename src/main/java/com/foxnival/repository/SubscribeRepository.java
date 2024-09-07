package com.foxnival.repository;

import com.foxnival.entity.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscribeRepository extends JpaRepository<Subscriber, Long> {
}

package com.foxnival.repository;

import com.foxnival.entity.UsernameInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsernameRepository extends JpaRepository<UsernameInfo, Long> {
}

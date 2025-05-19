package com.b2x2.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToyRepository extends JpaRepository<ToyEntity, Long> {
}

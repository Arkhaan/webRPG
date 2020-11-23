package com.greenfox.sideproject.repositories;

import com.greenfox.sideproject.models.Tale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaleRepository extends JpaRepository<Tale, Long> {
}

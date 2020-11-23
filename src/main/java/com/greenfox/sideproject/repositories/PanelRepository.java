package com.greenfox.sideproject.repositories;

import com.greenfox.sideproject.models.Panel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PanelRepository extends JpaRepository<Panel, Long> {

    @Query("select p.id from Panel p")
    List<Long> findAllIds();
}

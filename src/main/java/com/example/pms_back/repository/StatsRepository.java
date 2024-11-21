package com.example.pms_back.repository;

import com.example.pms_back.model.Stats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

@Repository
public interface StatsRepository extends JpaRepository<Stats, String> {

    List<Stats> findAll();

    List<Stats> findByUserId(String id);

    List<Stats> findByUserIdAndDateAfter(String id, ZonedDateTime date);

    @Override
    Stats save(Stats stats);

}
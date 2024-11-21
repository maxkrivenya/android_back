package com.example.pms_back.service;

import com.example.pms_back.model.Stats;

import java.time.ZonedDateTime;
import java.util.List;

public interface StatsService {
    Stats save(Stats stats);

    List<Stats> findByUserId(String id);


    List<Stats> findByUserIdAndDateAfter(String id, ZonedDateTime date);

}

package com.example.pms_back.service.impl;

import com.example.pms_back.model.Stats;
import com.example.pms_back.repository.StatsRepository;
import com.example.pms_back.service.StatsService;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

@Service
public class StatsServiceImpl implements StatsService {

    private final StatsRepository statsRepository;

    public StatsServiceImpl(StatsRepository statsRepository) {
        this.statsRepository = statsRepository;
    }

    public List<Stats> findByUserId(String id){
        return statsRepository.findByUserId(id);
    }

    public Stats save(Stats stats){
        return  statsRepository.save(stats);
    }

    public List<Stats> findByUserIdAndDateAfter(String id, ZonedDateTime date)
    {
        return statsRepository.findByUserIdAndDateAfter(id, date);
    }

}

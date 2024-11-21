package com.example.pms_back.utils;

import com.example.pms_back.model.Stats;
import com.example.pms_back.model.dto.StatsDTO;
import org.springframework.stereotype.Service;

@Service
public class StatsDTOMapper {
    public StatsDTO wrap(Stats stats){
        return new StatsDTO(
                stats.getUserId(),
                stats.getDate(),
                stats.getDuration()
        );
    }

    public Stats unwrap(StatsDTO statsDTO){
        Stats stats = new Stats();
        stats.setUserId(statsDTO.userid());
        stats.setDate(statsDTO.date());
        stats.setDuration(statsDTO.duration());
        return stats;
    }
}

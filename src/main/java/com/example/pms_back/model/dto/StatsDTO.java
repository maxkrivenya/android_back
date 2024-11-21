package com.example.pms_back.model.dto;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public record StatsDTO(
        String userid,
        ZonedDateTime date,
        int duration
)
{
}

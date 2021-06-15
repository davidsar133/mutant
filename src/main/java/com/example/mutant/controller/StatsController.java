package com.example.mutant.controller;

import com.example.mutant.dto.StatsDTO;
import com.example.mutant.service.IStatsService;
import com.example.mutant.util.StatsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatsController {

    IStatsService statsService;

    @Autowired
    public StatsController(IStatsService statsService) {
        this.statsService = statsService;
    }


    @RequestMapping(value = "/stats", method = RequestMethod.GET)
    public ResponseEntity<StatsDTO> getStats(){
        return ResponseEntity.ok(StatsMapper.mapStats(statsService.getStats()));
    }
}

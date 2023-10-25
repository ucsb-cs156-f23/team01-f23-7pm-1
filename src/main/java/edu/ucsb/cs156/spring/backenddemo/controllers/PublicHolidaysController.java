package edu.ucsb.cs156.spring.backenddemo.controllers;

import org.springframework.web.bind.annotation.RestController;

import edu.ucsb.cs156.spring.backenddemo.services.PublicHolidayQueryService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="Extract public holidays")
@RestController
@RequestMapping("/api/publicholidays")
public class PublicHolidaysController {
    
    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    PublicHolidayQueryService publicHolidayQueryService;

    @Operation(summary = "Get the list of public holidays for a historical year for a country ")
    @GetMapping("/get")
    public ResponseEntity<String> getHolidays(@Parameter(name="countryCode", description="country code", example="US") @RequestParam String countryCode,
        @Parameter(name="year", description="string of a specific year you want to query", example="2022") @RequestParam String year
    ) throws JsonProcessingException {
        //log.info("getHolidays: year={} countryCode={}", year, countryCode);
        String result = publicHolidayQueryService.getJSON(year,countryCode);
        return ResponseEntity.ok().body(result);
    }

}
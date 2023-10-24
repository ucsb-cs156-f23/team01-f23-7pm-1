package edu.ucsb.cs156.spring.backenddemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.ucsb.cs156.spring.backenddemo.services.TidesQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="Tides info from https://api.tidesandcurrents.noaa.gov/api/prod/")
@RestController
@RequestMapping("/api/tides")
public class TidesController {
    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    TidesQueryService tidesQueryService;

    @Operation(summary = "Get tides info from https://api.tidesandcurrents.noaa.gov/api/prod/")
    @GetMapping("/get")
    public ResponseEntity<String> getTides(
        @Parameter(name="beginDate", description="Beggining date of tides date range", example="20020117") @RequestParam String beginDate,
        @Parameter(name="endDate", description="End date of tides date range", example="20020125") @RequestParam String endDate,
        @Parameter(name="Station", description="StationId of tides, found at https://tidesandcurrents.noaa.gov", example="9414290") @RequestParam String Station
    ) throws JsonProcessingException {
        String result = tidesQueryService.getJSON(beginDate, endDate, Station);
        return ResponseEntity.ok().body(result);
    }
}

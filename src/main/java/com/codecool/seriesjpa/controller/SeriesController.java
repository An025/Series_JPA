package com.codecool.seriesjpa.controller;

import com.codecool.seriesjpa.model.Series;
import com.codecool.seriesjpa.service.SeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/series")
public class SeriesController {

    private final SeriesService seriesService;

    @Autowired
    public SeriesController(SeriesService seriesService) {
        this.seriesService = seriesService;
    }

    @GetMapping
    public List<Series> getSeries(){
        return seriesService.getSeries();
    }

    @PostMapping
    public void registerNewSeries(@RequestBody Series series){
        seriesService.addNewSeries(series);
    }

    @DeleteMapping(path="{seriesId}")
    public void deleteSeries(@PathVariable("seriesId") Long seriesId){
        seriesService.deleteSeries(seriesId);
    }

    @PutMapping(path= "{seriesId}")
    public void updateSeries(
      @PathVariable("seriesId") Long seriesId,
      @RequestParam(name = "title", required = false) String title,
      @RequestParam(name = "rating", required = false) String rating)
    {
        seriesService.updateSeries(seriesId, title, rating);
    }
}

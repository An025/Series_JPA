package com.codecool.seriesjpa.service;

import com.codecool.seriesjpa.model.Series;
import com.codecool.seriesjpa.repository.SeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SeriesService {

    public final SeriesRepository seriesRepository;

    @Autowired
    public SeriesService(SeriesRepository seriesRepository) {
        this.seriesRepository = seriesRepository;
    }

    public List<Series> getSeries(){
        return seriesRepository.findAll();
    }


    public void addNewSeries(Series series) {
        Optional<Series> seriesOptional = seriesRepository.findSeriesByTitle(series.getTitle());
        if(seriesOptional.isPresent()){
            throw new IllegalStateException("series taken");
        }
        seriesRepository.save(series);
    }

    public void deleteSeries(Long seriesId) {
        boolean exists = seriesRepository.existsById(seriesId);
        if(!exists){
            throw new IllegalStateException("Series with id " + seriesId + "doesn't exists.");
        }
        seriesRepository.deleteById(seriesId);
    }

    @Transactional
    public void updateSeries(Long seriesId, String title, String rating) {
        Series series = seriesRepository.findById(seriesId)
                .orElseThrow(() -> new IllegalStateException(
                        "series with id " + seriesId + " does not exists"
                ));

        if (title != null && title.length() > 0 && !Objects.equals(series.getTitle(), title)
        ) {
            series.setTitle(title);
        }
        if(rating != null && rating.length()>0 && !Objects.equals(series.getRating(), rating)){
            series.setRating(rating);
        }
        seriesRepository.save(series);
        seriesRepository.flush();
    }
}

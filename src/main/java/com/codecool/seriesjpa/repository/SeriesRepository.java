package com.codecool.seriesjpa.repository;

import com.codecool.seriesjpa.model.Series;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface SeriesRepository extends JpaRepository<Series, Long> {
    Optional<Series> findSeriesByTitle(String title);
}

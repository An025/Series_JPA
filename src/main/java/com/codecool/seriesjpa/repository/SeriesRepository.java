package com.codecool.seriesjpa.repository;

import com.codecool.seriesjpa.model.Series;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeriesRepository extends JpaRepository<Series, Long> {
}

package com.codecool.seriesjpa.configuration;

import com.codecool.seriesjpa.model.Series;
import com.codecool.seriesjpa.repository.SeriesRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SeriesConfiguration {

    @Bean
    CommandLineRunner commandLineRunner(SeriesRepository seriesRepository){
        return args ->{
            Series gamesOfThrones = Series.builder()
                    .title("Trónok harca")
                    .rating("9.0")
                    .build();

            Series dexter = Series.builder()
                    .title("Dexter")
                    .rating("9.3")
                    .build();
            seriesRepository.saveAll(List.of(gamesOfThrones, dexter));
        };
    }
}

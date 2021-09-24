package com.codecool.seriesjpa.configuration;

import com.codecool.seriesjpa.model.Season;
import com.codecool.seriesjpa.model.Series;
import com.codecool.seriesjpa.repository.SeasonRepository;
import com.codecool.seriesjpa.repository.SeriesRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Set;

@Configuration
public class SeriesConfiguration {

    @Bean
    CommandLineRunner commandLineRunner(SeriesRepository seriesRepository, SeasonRepository seasonRepository){
        return args ->{
            Series gamesOfThrones = Series.builder()
                    .title("Trónok harca")
                    .rating("9.0")
                    .build();

            Series dexter = Series.builder()
                    .title("Dexter")
                    .rating("9.3")
                    .build();

            Season game1 = Season.builder()
                    .series(gamesOfThrones)
                    .season_number(1)
                    .title("season 1")
                    .build();

            Season game2 = Season.builder()
                    .series(gamesOfThrones)
                    .season_number(2)
                    .title("season 2")
                    .build();

            Season game3 = Season.builder()
                    .series(gamesOfThrones)
                    .season_number(3)
                    .title("season 3")
                    .build();

            Season game4 = Season.builder()
                    .series(gamesOfThrones)
                    .season_number(4)
                    .title("season 4")
                    .build();

            Season game5 = Season.builder()
                    .series(gamesOfThrones)
                    .season_number(5)
                    .title("season 5")
                    .build();

            Season game6 = Season.builder()
                    .series(gamesOfThrones)
                    .season_number(6)
                    .title("season 6")
                    .build();

            Season dexter1 = Season.builder()
                    .series(dexter)
                    .season_number(1)
                    .title("season 1")
                    .build();

            seriesRepository.saveAll(List.of(gamesOfThrones, dexter));
            seasonRepository.saveAll(Set.of(game1, game2, game3, game4, game5, game6, dexter1));
        };
    }
}

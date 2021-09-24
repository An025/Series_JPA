package com.codecool.seriesjpa.repository;

import com.codecool.seriesjpa.model.Season;
import com.codecool.seriesjpa.model.Series;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.checkerframework.checker.units.qual.A;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

@ActiveProfiles("test")
public class SeriesRepositoryTest {

    @Autowired
    private SeriesRepository seriesRepository;

    @Autowired
    private SeasonRepository seasonRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void saveOneSimple(){
        Series gameOfThrones = Series.builder()
                .title("Tórnok harca")
                .rating("9.0")
                .build();

        seriesRepository.save(gameOfThrones);

        List<Series> seriesList = seriesRepository.findAll();
        assertThat(seriesList).hasSize(1);
    }

    @Test( expected = DataIntegrityViolationException.class)
    public void titleShouldBeNotNull(){
        Series gameOfThrones = Series.builder()
                .rating("9.0")
                .build();
        seriesRepository.save(gameOfThrones);

    }

    @Test
    public void seasonsArePersistedAndDeletedWithNewSeries(){
        Set<Season> seasons = IntStream.range(1, 10)
                .boxed()
                .map(integer -> Season.builder().season_number(integer).build())
                .collect(Collectors.toSet());

        Series gameOfThrones = Series.builder()
                .seasons(seasons)
                .title("Game of throns")
                .build();

        seriesRepository.save(gameOfThrones);
        assertThat(seasonRepository.findAll())
                .hasSize(9)
                .anyMatch(season -> season.getSeason_number().equals(9));
        seriesRepository.deleteAll();
        assertThat(seasonRepository.findAll())
                .hasSize(0);
    }
}
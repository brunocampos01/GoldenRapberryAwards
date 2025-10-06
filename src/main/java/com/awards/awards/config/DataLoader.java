package com.awards.awards.config;

import com.awards.awards.model.Movie;
import com.awards.awards.repository.MovieRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Configuration
public class DataLoader {

  @Bean
  CommandLineRunner loadDatabase(MovieRepository movieRepository) {
    return args -> {
      try (BufferedReader reader = new BufferedReader(
          new InputStreamReader(new ClassPathResource("movielist.csv").getInputStream(), StandardCharsets.UTF_8))) {

        String line = reader.readLine();

        while ((line = reader.readLine()) != null) {
          String[] fields = line.split(";", -1); // get empty trailing cell
          if (fields.length < 5) continue;

          Movie movie = new Movie();
          movie.setYear(Integer.parseInt(fields[0].trim()));
          movie.setTitle(fields[1].trim());
          movie.setStudios(fields[2].trim());
          movie.setProducers(fields[3].trim());
          String winnerValue = fields[4].trim();
          if (winnerValue.isEmpty()) {
            movie.setWinner(false); // empty -> no
          } else {
            movie.setWinner("yes".equalsIgnoreCase(winnerValue));
          }
          
          movieRepository.save(movie);
        }

        System.out.println("Data loaded into H2 successfully!");
      } catch (Exception e) {
        System.err.println("Error loading data: " + e.getMessage());
      }
    };
  }
}

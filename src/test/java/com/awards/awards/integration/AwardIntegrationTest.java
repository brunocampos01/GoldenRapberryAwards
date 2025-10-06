package com.awards.awards.integration;

import com.awards.awards.dto.IntervalResponse;
import com.awards.awards.dto.ProducerInterval;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AwardIntegrationTest {

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  void shouldReturnCorrectProducerIntervals() {
    // call
    ResponseEntity<IntervalResponse> response = restTemplate.getForEntity("/awards/intervals", IntervalResponse.class);

    // GET endpoint
    assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();

    // body msg
    IntervalResponse body = response.getBody();
    assertThat(body).isNotNull();
    assertThat(body.getMin()).isNotEmpty();
    assertThat(body.getMax()).isNotEmpty();

    // min interval
    List<ProducerInterval> minList = body.getMin();
    ProducerInterval firstMin = minList.get(0);
    assertThat(firstMin.getInterval()).isGreaterThanOrEqualTo(1);

    // max interval
    List<ProducerInterval> maxList = body.getMax();
    ProducerInterval firstMax = maxList.get(0);
    assertThat(firstMax.getInterval()).isGreaterThanOrEqualTo(firstMin.getInterval());

    System.out.println("Integration test executed successfully!");
  }
}

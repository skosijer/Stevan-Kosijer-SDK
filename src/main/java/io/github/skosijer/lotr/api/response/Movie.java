package io.github.skosijer.lotr.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

    @JsonProperty(value = "_id")
    private String id;

    private String name;

    private double runtimeInMinutes;

    private double budgetInMillions;

    private double boxOfficeRevenueInMillions;

    private double academyAwardNominations;

    private double academyAwardWins;

    private double rottenTomatoesScore;
}

package io.github.skosijer.lotr.api.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MoviePage {

    private List<Movie> docs;

    private int total;

    private int limit;

    private int page;

    private int pages;

    private int offset;
}

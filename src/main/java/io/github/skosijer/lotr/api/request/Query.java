package io.github.skosijer.lotr.api.request;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Query {

    private Pagination pagination;

    private Sort sort;

    private List<Filter> filters;
}

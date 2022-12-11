package io.github.skosijer.lotr.util;

import static java.util.Objects.nonNull;

import io.github.skosijer.lotr.api.request.Pagination;
import io.github.skosijer.lotr.api.request.Query;
import io.github.skosijer.lotr.api.request.Sort;
import io.github.skosijer.lotr.api.request.SortOrder;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class QueryUtil {

    private static final String DEFAULT_PAGE_LIMIT = "10";

    // Builds the query parameters if they are present. 
    // Possible query parameters are Pagination, Sort and Filters.
    public static String buildQueryParams(Query query) {
        var queryParams = new StringBuilder();

        appendPagination(queryParams, query.getPagination());
        appendSort(queryParams, query.getSort());

        return queryParams.toString();
    }

    // Appends the sort parameters if they are present. Default sort type is asc.
    //      e.g. sort=name:asc
    private static void appendSort(StringBuilder queryParams, Sort sort) {
        if (nonNull(sort) && nonNull(sort.getField())) {
            queryParams.append("sort=").append(sort.getField());
            if (nonNull(sort.getSortOrder())) {
                queryParams.append(sort.getSortOrder().getOperation()).append("&");
            } else {
                queryParams.append(SortOrder.ASC).append("&");
            }
        }
    }

    // Appends the pagination parameters if they are present.
    //      e.g. page=1&offset=3&limit=10
    private static void appendPagination(StringBuilder queryParams, Pagination pagination) {
        if (nonNull(pagination)) {
            if (nonNull(pagination.getPage())) {
                queryParams.append("page=").append(pagination.getPage()).append("&");
            }
            if (nonNull(pagination.getOffset())) {
                queryParams.append("offset=").append(pagination.getOffset()).append("&");
            }
            if (nonNull(pagination.getLimit())) {
                queryParams.append("limit=").append(pagination.getLimit()).append("&");
            } else {
                queryParams.append("limit=").append(DEFAULT_PAGE_LIMIT).append("&");
            }
        }
    }
}

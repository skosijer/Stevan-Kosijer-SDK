package io.github.skosijer.lotr.api.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SortType {
    ASC(":asc"),
    DESC(":desc");

    private final String operation;
}

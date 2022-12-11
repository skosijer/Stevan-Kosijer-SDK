package io.github.skosijer.lotr.api.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FilterType {
    MATCH("="),
    NOT_MATCH("!="),
    INCLUDE("="),
    EXCLUDE("!="),
    EXISTS(""),
    NOT_EXISTS("!"),
    MATCH_REGEX("="),
    NOT_MATCH_REGEX("!=");

    private final String operation;
}

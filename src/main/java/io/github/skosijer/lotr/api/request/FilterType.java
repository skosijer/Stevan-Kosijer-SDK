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
    EXIST(""),
    NOT_EXIST("!"),
    MATCH_REGEX("="),
    NOT_MATCH_REGEX("!="),
    GREATER_THAN(">"),
    LESSER_THAN("<"),
    EQUALS("=");

    private final String operation;
}

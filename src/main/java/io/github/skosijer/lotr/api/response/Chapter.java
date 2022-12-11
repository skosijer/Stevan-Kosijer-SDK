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
public class Chapter {

    @JsonProperty(value = "_id")
    private String id;

    private String chapterName;
}

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
public class Character {

    @JsonProperty(value = "_id")
    private String id;

    private String height;

    private String race;

    private String gender;

    private String birth;

    private String spouse;

    private String death;

    private String realm;

    private String hair;

    private String name;

    private String wikiUrl;
}

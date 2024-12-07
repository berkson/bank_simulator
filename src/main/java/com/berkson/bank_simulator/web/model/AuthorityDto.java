package com.berkson.bank_simulator.web.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created By : Berkson Ximenes
 * Date : 06/12/2024
 **/

@Getter
@Setter
@NoArgsConstructor
public class AuthorityDto extends BaseDto {

    @JsonProperty(value = "codigo")
    private String authority;
    @JsonProperty(value = "descricao")
    private String description;

    public AuthorityDto(Long id, String authority, String description) {
        super(id);
        this.authority = authority;
        this.description = description;
    }

    public AuthorityDto(String authority, String description) {
        this.authority = authority;
        this.description = description;
    }
}

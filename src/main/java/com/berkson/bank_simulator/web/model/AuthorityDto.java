package com.berkson.bank_simulator.web.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

/**
 * Created By : Berkson Ximenes
 * Date : 06/12/2024
 **/

@Getter
@Setter
@NoArgsConstructor
public class AuthorityDto extends BaseDto implements GrantedAuthority {

    @JsonProperty(value = "codigo")
    @NotBlank
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

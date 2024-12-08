package com.berkson.bank_simulator.web.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import java.io.Serializable;
import java.text.Collator;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

/**
 * Created By : Berkson Ximenes
 * Date : 06/12/2024
 **/

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto extends AuditableEntityDto implements Serializable, Comparable<UserDto> {

    @CPF
    @NotBlank(message = "{cpf.NotBlank.message}")
    @JsonProperty(value = "cpf")
    private String username;

    @JsonProperty(value = "idade")
    private int age;

    @NotBlank(message = "{nome.NotBlank.message}")
    @Length(min = 3, max = 255, message = "{nome.Length.message}")
    @JsonProperty(value = "nome")
    private String name;

    @NotBlank(message = "{field.senha.validation.constraints.NotBlank.message}")
    @Pattern(regexp = "^((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*\\W).{6,8})$", message = "{senha.Pattern.message}")
    @JsonProperty(value = "senha", access = JsonProperty.Access.WRITE_ONLY)
    @Getter(value = AccessLevel.NONE)
    private String password;

    @Email
    private String email;

    @JsonProperty(value = "conta")
    @NotNull
    private AccountDto account;

    @JsonProperty(value = "permissoes")
    @Size(min = 1)
    private List<AuthorityDto> authorities = new ArrayList<>();

    @Override
    public int compareTo(@NonNull UserDto user) {
        Collator collator = Collator.getInstance(Locale.getDefault());
        collator.setStrength(Collator.PRIMARY);
        try {
            return collator.compare(this.name, user.name);
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId(), username);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (!(obj instanceof UserDto user))
            return false;

        return Objects.equals(this.getId(), user.getId()) && Objects.equals(this.username, user.username);
    }
}

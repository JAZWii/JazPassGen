package com.jazwii.jazpassgen.Classes.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jazwii.jazpassgen.Singleton.DatabaseConstants;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = DatabaseConstants.TABLE_ROLE)
public class Role implements GrantedAuthority {
    public enum ROLE_TYPE {
        @JsonProperty("ROLE_AUTHENTICATED")
        AUTHENTICATED("ROLE_AUTHENTICATED"),
        @JsonProperty("ROLE_ADMINISTRATOR")
        ADMINISTRATOR("ROLE_ADMINISTRATOR");

        private final String value;

        ROLE_TYPE(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String code;

    @NotNull
    private String label;

    public Role() {

    }

    public Role(String code, String label) {
        this.code = code;
        this.label = label;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String getAuthority() {
        return getCode();
    }
}

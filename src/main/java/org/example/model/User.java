package org.example.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class User {
        private Integer id;
        private String login;
        private String password;

}

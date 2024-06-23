package com.scaler.productservicemorningbatch.inheritancerepresentation.tableperclass;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Entity(name = "tpc_users")
public class User {
    @Id
    private Long id;
    private String name;
    private String email;
    private String password;
}

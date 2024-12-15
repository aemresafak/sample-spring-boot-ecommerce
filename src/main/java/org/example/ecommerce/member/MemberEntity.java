package org.example.ecommerce.member;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.example.ecommerce.common.BaseEntity;

@Getter
@Setter
@Entity
@Table(name = "member")
public class MemberEntity extends BaseEntity {
    private String email;
    private String password;
}

package org.example.ecommerce.member;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.ecommerce.common.BaseEntity;
import org.example.ecommerce.customer.CustomerEntity;
import org.example.ecommerce.role.RoleEntity;
import org.springframework.lang.Nullable;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "member")
public class MemberEntity extends BaseEntity {
    private String email;
    private String password;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "member_role",
            joinColumns = @JoinColumn(name = "member_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<RoleEntity> roles;

    @OneToOne
    @JoinColumn(name = "customer_id")
    @Nullable
    private CustomerEntity customerEntity;
}

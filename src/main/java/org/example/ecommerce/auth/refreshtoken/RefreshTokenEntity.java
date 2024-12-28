package org.example.ecommerce.auth.refreshtoken;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.example.ecommerce.common.BaseEntity;
import org.example.ecommerce.member.MemberEntity;

import java.time.Instant;

@Entity
@Table(name = "refresh_token")
@Getter
@Setter
public class RefreshTokenEntity extends BaseEntity {
    @ManyToOne(optional = false)
    @JoinColumn(
            name = "member_id",
            referencedColumnName = "id"
    )
    private MemberEntity member;
    private Instant expiresAt;
    private String token;
}

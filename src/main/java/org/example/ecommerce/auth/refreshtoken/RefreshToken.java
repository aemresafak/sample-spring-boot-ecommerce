package org.example.ecommerce.auth.refreshtoken;

import org.example.ecommerce.member.Member;

import java.time.Instant;
import java.util.UUID;

public record RefreshToken(UUID id, String token, Instant expiresAt, Member member, boolean deleted) {
    public static RefreshToken from(RefreshTokenEntity entity) {
        var memberEntity = entity.getMember();
        var member = Member.from(memberEntity);
        return new RefreshToken(entity.getId(), entity.getToken(), entity.getExpiresAt(), member, entity.getDeleted());
    }
}

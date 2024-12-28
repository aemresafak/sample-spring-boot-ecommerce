package org.example.ecommerce.auth.refreshtoken;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.ecommerce.common.NotFoundException;
import org.example.ecommerce.member.Member;
import org.example.ecommerce.member.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import static net.logstash.logback.argument.StructuredArguments.kv;

@Slf4j
@RequiredArgsConstructor
@Service
public class RefreshTokenJpaService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final MemberRepository memberRepository;
    private final RefreshTokenProperties refreshTokenProperties;
    private final PasswordEncoder passwordEncoder;

    public RefreshToken createRefreshToken(UUID memberId) {
        var refreshTokenEntity = new RefreshTokenEntity();
        var refreshToken = RefreshTokenUtils.generateRefreshToken();
        var encodedRefreshToken = passwordEncoder.encode(refreshToken);
        refreshTokenEntity.setToken(encodedRefreshToken);

        var memberEntity = memberRepository.findById(memberId).orElseThrow(NotFoundException::new);
        refreshTokenEntity.setMember(memberEntity);

        refreshTokenEntity.setExpiresAt(Instant.now().plus(refreshTokenProperties.getValidity()));

        var savedEntity = refreshTokenRepository.save(refreshTokenEntity);
        var member = Member.from(memberEntity);
        return new RefreshToken(savedEntity.getId(), refreshToken, savedEntity.getExpiresAt(), member, savedEntity.getDeleted());
    }

    public Optional<RefreshToken> fetchRefreshToken(UUID id) {
        log.info("Fetching refresh token {}", kv("token", id));
        return refreshTokenRepository.findById(id).map(RefreshToken::from);
    }

    public void deleteRefreshToken(UUID id) {
        log.info("Deleting refresh token {}", kv("tokenId", id));
        int affectedRows = refreshTokenRepository.markAsDeleted(id);
        if (affectedRows != 1) {
            log.error("Something went wrong while deleting refresh token {}", kv("refreshTokenId", id));
        }
    }
}

package uttugseuja.lucklotteryserver.domain.credential.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import uttugseuja.lucklotteryserver.domain.credential.exception.NoSuchPublicKeyException;
import uttugseuja.lucklotteryserver.global.api.dto.OIDCKeyDto;
import uttugseuja.lucklotteryserver.global.api.dto.OIDCKeysResponse;
import uttugseuja.lucklotteryserver.global.security.JwtOIDCProvider;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class OauthOIDCProvider {

    private final JwtOIDCProvider jwtOIDCProvider;

    private String getKidFromParsedJwtIdToken(String token, String iss, String aud) {
        log.info(iss, aud);
        return jwtOIDCProvider.getKidFromParsedJwtHeader(token, iss, aud);
    }

    public OIDCDecodePayload getPayloadFromIdToken(
            String token, String iss, String aud, OIDCKeysResponse oidcPublicKeysResponse) {
        String kid = getKidFromParsedJwtIdToken(token, iss, aud);

        Optional<OIDCKeyDto> matchedKeyOpt = oidcPublicKeysResponse.getKeys().stream()
                .filter(o -> o.getKid().equals(kid))
                .findFirst();

        if (!matchedKeyOpt.isPresent()) {
            throw NoSuchPublicKeyException.EXCEPTION;
        }

        OIDCKeyDto matchedKey = matchedKeyOpt.get();
        return (OIDCDecodePayload)
                jwtOIDCProvider.getOIDCTokenBody(token, matchedKey.getN(), matchedKey.getE());
    }


}
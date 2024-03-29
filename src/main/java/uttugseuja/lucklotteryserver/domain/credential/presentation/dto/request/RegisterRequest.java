package uttugseuja.lucklotteryserver.domain.credential.presentation.dto.request;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RegisterRequest {

    @Size(max = 10)
    private String nickname;

    private String profilePath;

}

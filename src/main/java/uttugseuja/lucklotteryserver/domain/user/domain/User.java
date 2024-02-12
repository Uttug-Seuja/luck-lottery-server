package uttugseuja.lucklotteryserver.domain.user.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;
import uttugseuja.lucklotteryserver.domain.user.domain.vo.UserInfoVO;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String nickname;

    private String oauthProvider;

    private String oauthId;

    private String email;

    @Nullable
    private String profilePath;

    @Enumerated(EnumType.STRING)
    private AccountRole accountRole = AccountRole.USER;

    @Builder
    public User(
            Long id,
            String nickname,
            String oauthProvider,
            String oauthId,
            String email,
            String profilePath) {
        this.id = id;
        this.nickname = nickname;
        this.oauthProvider = oauthProvider;
        this.oauthId = oauthId;
        this.email = email;
        this.profilePath = profilePath;
    }

    public UserInfoVO getUserInfo() {
        return UserInfoVO.builder()
                .userId(id)
                .nickname(nickname)
                .email(email)
                .profilePath(profilePath)
                .build();
    }

    public void updateProfilePath(String profilePath){
        this.profilePath = profilePath;
    }

}

package uttugseuja.lucklotteryserver.domain.user.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import uttugseuja.lucklotteryserver.domain.user.domain.vo.UserInfoVO;

@Getter
@AllArgsConstructor
public class UserInfoResponse {

    private Long id;
    private String nickname;
    private String oauthProvider;
    private String email;
    private String profilePath;
    private Boolean lotteryNotificationStatus;
    private Boolean pensionLotteryNotificationStatus;

    public UserInfoResponse(UserInfoVO userInfoVO){
        this.id = userInfoVO.getUserId();
        this.nickname = userInfoVO.getNickname();
        this.oauthProvider = userInfoVO.getOauthProvider();
        this.email = userInfoVO.getEmail();
        this.profilePath = userInfoVO.getProfilePath();
        this.lotteryNotificationStatus = userInfoVO.getLotteryNotificationStatus();
        this.pensionLotteryNotificationStatus = userInfoVO.getPensionLotteryNotificationStatus();
    }
}

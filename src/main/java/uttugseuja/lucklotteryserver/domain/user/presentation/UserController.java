package uttugseuja.lucklotteryserver.domain.user.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uttugseuja.lucklotteryserver.domain.user.presentation.dto.request.ChangeNicknameRequest;
import uttugseuja.lucklotteryserver.domain.user.presentation.dto.request.ChangeNotificationStatusRequest;
import uttugseuja.lucklotteryserver.domain.user.presentation.dto.request.ChangeProfileRequest;
import uttugseuja.lucklotteryserver.domain.user.presentation.dto.request.ChangeUserInfoRequest;
import uttugseuja.lucklotteryserver.domain.user.presentation.dto.response.UserInfoResponse;
import uttugseuja.lucklotteryserver.domain.user.presentation.dto.response.UserProfileResponse;
import uttugseuja.lucklotteryserver.domain.user.service.UserService;


@Tag(name = "유저", description = "유저 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @Operation(summary = "유저 프로필 이미지 변경하기")
    @PatchMapping("/profile")
    public UserProfileResponse changeProfilePath(@Valid @RequestBody ChangeProfileRequest changeProfileRequest){
        return userService.changeProfilePath(changeProfileRequest);
    }

    @Operation(summary = "유저 닉네임 변경하기")
    @PatchMapping("/nickname")
    public void changeNickname(@Valid @RequestBody ChangeNicknameRequest changeNicknameRequest){
        userService.changeNickname(changeNicknameRequest);
    }

    @Operation(summary = "유저 로또 알림 on/off 변경하기")
    @PatchMapping("/lottery/notification")
    public void changeLotteryNotificationStatus(
            @Valid @RequestBody ChangeNotificationStatusRequest changeNotificationStatusRequest) {
        userService.changeLotteryNotificationStatus(changeNotificationStatusRequest);
    }

    @Operation(summary = "유저 연금복권 알림 on/off 변경하기")
    @PatchMapping("/pension/lottery/notification")
    public void changePensionLotteryNotificationStatus(
            @Valid @RequestBody ChangeNotificationStatusRequest changeNotificationStatusRequest) {
        userService.changePensionLotteryNotificationStatus(changeNotificationStatusRequest);
    }

    @Operation(summary = "유저 장보 가져오기")
    @GetMapping("/my/info")
    public UserInfoResponse getUserInfo(){
        return userService.getUserInfo();
    }

    @Operation(summary = "유저 정보 변경")
    @PatchMapping("/update/my/info")
    public UserInfoResponse changeUserInfo(@Valid @RequestBody ChangeUserInfoRequest changeUserInfoRequest){
        return userService.changeUserInfo(changeUserInfoRequest);
    }
 }

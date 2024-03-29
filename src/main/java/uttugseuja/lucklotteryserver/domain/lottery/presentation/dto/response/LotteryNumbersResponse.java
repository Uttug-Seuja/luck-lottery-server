package uttugseuja.lucklotteryserver.domain.lottery.presentation.dto.response;

import lombok.Getter;
import uttugseuja.lucklotteryserver.domain.lottery.domain.vo.LotteryBaseInfoVo;
import uttugseuja.lucklotteryserver.global.common.Rank;

import java.util.List;

@Getter
public class LotteryNumbersResponse {

    private Integer firstNum;

    private Integer secondNum;

    private Integer thirdNum;

    private Integer fourthNum;

    private Integer fifthNum;

    private Integer sixthNum;

    private Rank rank;

    private List<Boolean> correctNumbers;

    public LotteryNumbersResponse(LotteryBaseInfoVo lotteryBaseInfoVo, List<Boolean> lotteryResult) {
        this.firstNum = lotteryBaseInfoVo.getFirstNum();
        this.secondNum = lotteryBaseInfoVo.getSecondNum();
        this.thirdNum = lotteryBaseInfoVo.getThirdNum();
        this.fourthNum = lotteryBaseInfoVo.getFourthNum();
        this.fifthNum = lotteryBaseInfoVo.getFifthNum();
        this.sixthNum = lotteryBaseInfoVo.getSixthNum();
        this.rank = lotteryBaseInfoVo.getRank();
        this.correctNumbers = lotteryResult;
    }
}

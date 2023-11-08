package lotto.view;

import camp.nextstep.edu.missionutils.test.NsTest;
import lotto.Application;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomUniqueNumbersInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

class ViewTest extends NsTest {

    @DisplayName("구입금액에 숫자가 아닌 문자를 입력하면 예외가 발생한다.")
    @Test
    void inputNonNumericCost() {
        assertSimpleTest(() -> {
            runException("abcdef");
            assertThat(output()).contains("[ERROR] 20억 이하의 숫자를 입력해주세요.");
        });
    }

    @DisplayName("구입금액에 int 범위 이상의 숫자를 입력하면 예외가 발생한다.")
    @Test
    void inputOverIntRangeCost() {
        assertSimpleTest(() -> {
            runException("2200000000");
            assertThat(output()).contains("[ERROR] 20억 이하의 숫자를 입력해주세요.");
        });
    }

    @DisplayName("구입금액에 공백만 입력하면 예외가 발생한다.")
    @Test
    void inputCostOnlyBlank() {
        assertSimpleTest(() -> {
            runException("   ");
            assertThat(output()).contains("[ERROR] 20억 이하의 숫자를 입력해주세요.");
        });
    }

    @DisplayName("구입금액에 숫자와 공백을 입력하면 공백을 무시한다.")
    @Test
    void inputCostWithBlank() {
        assertRandomUniqueNumbersInRangeTest(
                () -> {
                    run("2 00 0", "1,2,3,4,5,6", "7");
                    assertThat(output()).contains(
                            "2개를 구매했습니다.",
                            "[8, 21, 23, 41, 42, 43]",
                            "[3, 5, 11, 16, 32, 38]",
                            "3개 일치 (5,000원) - 0개",
                            "4개 일치 (50,000원) - 0개",
                            "5개 일치 (1,500,000원) - 0개",
                            "5개 일치, 보너스 볼 일치 (30,000,000원) - 0개",
                            "6개 일치 (2,000,000,000원) - 0개",
                            "총 수익률은 0.0%입니다."
                    );
                },
                List.of(8, 21, 23, 41, 42, 43),
                List.of(3, 5, 11, 16, 32, 38)
        );
    }

    @DisplayName("당첨 로또에 숫자가 아닌 문자를 입력하면 예외가 발생한다.")
    @Test
    void inputNonNumericWinningLotto() {
        assertSimpleTest(() -> {
            runException("1000", "a,b,c,d,e,f");
            assertThat(output()).contains("[ERROR] 로또 번호는 쉼표로 구분되는 1에서 45 사이의 숫자여야 합니다.");
        });
    }

    @DisplayName("당첨 로또에 int 범위 이상의 숫자를 입력하면 예외가 발생한다.")
    @Test
    void inputOverIntRangeLotto() {
        assertSimpleTest(() -> {
            runException("1000", "2200000000,1,2,3,4,5");
            assertThat(output()).contains("[ERROR] 로또 번호는 쉼표로 구분되는 1에서 45 사이의 숫자여야 합니다.");
        });
    }

    @DisplayName("당첨 로또에 숫자와 공백을 입력하면 공백을 무시한다.")
    @Test
    void inputWinningLottoWithBlank() {
        assertRandomUniqueNumbersInRangeTest(
                () -> {
                    run("1000", "1,2 , 3, 4 , 5 ,2 0", "7");
                    assertThat(output()).contains(
                            "1개를 구매했습니다.",
                            "[8, 21, 23, 41, 42, 43]",
                            "3개 일치 (5,000원) - 0개",
                            "4개 일치 (50,000원) - 0개",
                            "5개 일치 (1,500,000원) - 0개",
                            "5개 일치, 보너스 볼 일치 (30,000,000원) - 0개",
                            "6개 일치 (2,000,000,000원) - 0개",
                            "총 수익률은 0.0%입니다."
                    );
                },
                List.of(8, 21, 23, 41, 42, 43)
        );
    }

    @DisplayName("보너스 번호에 숫자가 아닌 문자를 입력하면 예외가 발생한다.")
    @Test
    void inputNonNumericBonus() {
        assertSimpleTest(() -> {
            runException("1000", "1,2,3,4,5,6", "!");
            assertThat(output()).contains("[ERROR] 보너스 번호는 1에서 45 사이인 하나의 숫자여야 합니다.");
        });
    }

    @DisplayName("당첨 로또에 int 범위 이상의 숫자를 입력하면 예외가 발생한다.")
    @Test
    void inputOverIntRangeBonus() {
        assertSimpleTest(() -> {
            runException("1000", "1,2,3,4,5,6", "2200000000");
            assertThat(output()).contains("[ERROR] 보너스 번호는 1에서 45 사이인 하나의 숫자여야 합니다.");
        });
    }


    @DisplayName("당첨 로또에 숫자와 공백을 입력하면 공백을 무시한다.")
    @Test
    void inputBonusWithBlank() {
        assertRandomUniqueNumbersInRangeTest(
                () -> {
                    run("1000", "1,2,3,4,5,6", "  7  ");
                    assertThat(output()).contains(
                            "1개를 구매했습니다.",
                            "[8, 21, 23, 41, 42, 43]",
                            "3개 일치 (5,000원) - 0개",
                            "4개 일치 (50,000원) - 0개",
                            "5개 일치 (1,500,000원) - 0개",
                            "5개 일치, 보너스 볼 일치 (30,000,000원) - 0개",
                            "6개 일치 (2,000,000,000원) - 0개",
                            "총 수익률은 0.0%입니다."
                    );
                },
                List.of(8, 21, 23, 41, 42, 43)
        );
    }

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}
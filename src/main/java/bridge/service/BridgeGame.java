package bridge.service;

import bridge.BridgeNumberGenerator;
import bridge.BridgeRandomNumberGenerator;
import bridge.constant.BridgeSymbol;
import bridge.BridgeMaker;
import bridge.constant.Direction;
import bridge.domain.GameResult;
import bridge.domain.Map;

import java.util.List;

/**
 * 다리 건너기 게임을 관리하는 클래스
 */
public class BridgeGame {

    private final Map map;
    private final GameResult result;

    public BridgeGame() {
        map = new Map();
        result = new GameResult();
    }

    public List<String> setBridge(int size) {
        BridgeNumberGenerator bridgeNumberGenerator = new BridgeRandomNumberGenerator();
        BridgeMaker bridgeMaker = new BridgeMaker(bridgeNumberGenerator);
        return bridgeMaker.makeBridge(size);
    }

    public void setRound() {
        result.changeSuccess();
        result.addAttemptNumber();
        map.resetMap();
    }

    public boolean isClear() {
        return result.getSuccess().equals(BridgeSymbol.SUCCESS.getState());
    }
    /**
     * 사용자가 칸을 이동할 때 사용하는 메서드
     * <p>
     * 이동을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public List<String> move(String user, String answer) {
        String result = compareUserAndAnswer(user, answer);
        if (user.equals(Direction.UP.getDirection())) {
            map.makeUpMap(result);
        } else if (user.equals(Direction.DOWN.getDirection())) {
            map.makeDownMap(result);
        }
        return map.getAllMap();
    }

    private String compareUserAndAnswer(String user, String answer) {
        if (user.equals(answer)) {
            return BridgeSymbol.O.getState();
        }
        return BridgeSymbol.X.getState();
    }

    /**
     * 사용자가 게임을 다시 시도할 때 사용하는 메서드
     * <p>
     * 재시작을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public boolean retry(String command) {
        if (command.equals(BridgeSymbol.RETRY.getState())) {
            return true;
        }
        return false;
    }

    public void changeToFail() {
        result.changeFail();
    }

    public List<String> getMap() {
        return map.getAllMap();
    }

    public List<String> getResult() {
        return List.of(result.getSuccess(), result.getAttemptNumber());
    }
}

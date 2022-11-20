package bridge.domain;

import bridge.BridgeNumberGenerator;
import bridge.BridgeRandomNumberGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * 다리 건너기 게임을 관리하는 클래스
 */
public class BridgeGame {

    private final List<String> upBridge;
    private final List<String> downBridge;

    public BridgeGame() {
        upBridge = new ArrayList<>();
        downBridge = new ArrayList<>();
    }

    public List<String> setBridge(int size) {
        BridgeNumberGenerator bridgeNumberGenerator = new BridgeRandomNumberGenerator();
        BridgeMaker bridgeMaker = new BridgeMaker(bridgeNumberGenerator);
        return bridgeMaker.makeBridge(size);
    }

    /**
     * 사용자가 칸을 이동할 때 사용하는 메서드
     * <p>
     * 이동을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public List<List<String>> move(String user, String answer) {
        String result = compareUserAndAnswer(user, answer);
        if (user.equals("U")) {
            upBridge.add(result);
            downBridge.add(Bridge.BLANK.getLocation());
        } else if (user.equals("D")) {
            upBridge.add(Bridge.BLANK.getLocation());
            downBridge.add(result);
        }
        return List.of(upBridge, downBridge);
    }

    private String compareUserAndAnswer(String user, String answer) {
        if (user.equals(answer)) {
            return Bridge.O.getLocation();
        }
        return Bridge.X.getLocation();
    }

    /**
     * 사용자가 게임을 다시 시도할 때 사용하는 메서드
     * <p>
     * 재시작을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void retry() {
    }
}

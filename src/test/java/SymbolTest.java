import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class SymbolTest {

    @DisplayName("하트, 스페이드, 다이아몬드, 클로버가 존재한다.")
    @Test
    void 하트_스페이드_다이아몬드_클로버_존재(){
        String[] names = {"하트", "스페이드", "다이아몬드", "클로버"};
        List<String> namesInSymbol = Arrays.stream(Symbol.values())
                .map(Symbol::getName)
                .collect(Collectors.toList());
        assertThat(namesInSymbol).containsExactlyInAnyOrder(names);
    }
}
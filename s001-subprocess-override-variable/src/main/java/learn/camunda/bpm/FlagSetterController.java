package learn.camunda.bpm;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.MessageCorrelationResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class FlagSetterController {

    public static final String FLAG = "flag";

    private final RuntimeService runtimeService;

    @GetMapping("/set")
    void setVar() {
        List<MessageCorrelationResult> messageCorrelationBuilder = runtimeService
                .createMessageCorrelation("Message_SetFlag")
                .setVariable(FLAG, true)
                .correlateAllWithResult();

        log.debug("correlationResults: {}", messageCorrelationBuilder);
    }

}

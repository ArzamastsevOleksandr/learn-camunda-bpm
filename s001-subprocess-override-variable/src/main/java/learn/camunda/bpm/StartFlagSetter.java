package learn.camunda.bpm;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import static learn.camunda.bpm.FlagSetterController.FLAG;

@Slf4j
@Component
public class StartFlagSetter implements JavaDelegate {

    private static final boolean FLAG_START_VALUE = false;

    @Override
    public void execute(DelegateExecution execution) {
        execution.setVariable(FLAG, FLAG_START_VALUE);
        log.error("set {}={}", FLAG, FLAG_START_VALUE);
    }

}

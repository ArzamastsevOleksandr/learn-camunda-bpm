package learn.camunda.bpm;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SubprocessLogger implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) {
        log.error("Terminate process with BK: {}", execution.getProcessBusinessKey());
    }

}

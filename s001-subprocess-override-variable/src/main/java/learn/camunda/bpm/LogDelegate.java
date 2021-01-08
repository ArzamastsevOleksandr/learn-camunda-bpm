package learn.camunda.bpm;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LogDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) {
        var delegateExecutionData = DelegateExecutionData.builder()
                .currentActivityId(execution.getCurrentActivityId())
                .currentActivityName(execution.getCurrentActivityName())
                .processBusinessKey(execution.getProcessBusinessKey())
                .processDefinitionId(execution.getProcessDefinitionId())
                .processInstanceId(execution.getProcessInstanceId())
                .variableMap(execution.getVariables())
                .build();
        log.error("{}", ReflectionToStringBuilder.toString(delegateExecutionData, ToStringStyle.MULTI_LINE_STYLE));
    }

}

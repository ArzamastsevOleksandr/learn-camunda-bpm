package learn.camunda.bpm;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DelegateExecutionData {
    String currentActivityId;
    String currentActivityName;
    String processBusinessKey;
    String processDefinitionId;
    String processInstanceId;
    Map<String, Object> variableMap;
}

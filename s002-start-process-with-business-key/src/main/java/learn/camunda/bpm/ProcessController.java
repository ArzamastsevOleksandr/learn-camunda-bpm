package learn.camunda.bpm;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ProcessController {

    private static final String PROCESS_DEFINITION_KEY = "s002-start-process-with-business-key";
    private static final String NOT_FOUND = "NOT_FOUND";

    private final ProcessEngine processEngine;
    private final RuntimeService runtimeService;

    @GetMapping("/start/{bk}")
    String startProcessByBusinessKey(@PathVariable("bk") String businessKey) {
        RuntimeService runtimeService = processEngine.getRuntimeService();
        Map<String, Object> variables = Map.of(
                "randomInt", ThreadLocalRandom.current().nextInt(100)
        );
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(
                PROCESS_DEFINITION_KEY, businessKey, variables
        );
        return processInstance.getBusinessKey();
    }

    @GetMapping("/list")
    List<String> listActiveProcesses() {
        return runtimeService.createProcessInstanceQuery()
                .processDefinitionKey(PROCESS_DEFINITION_KEY)
                .active()
                .list()
                .stream()
                .map(ProcessInstance::getBusinessKey)
                .collect(toList());
    }

    @GetMapping("/{bk}")
    String findByBusinessKey(@PathVariable("bk") String businessKey) {
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                .processDefinitionKey(PROCESS_DEFINITION_KEY)
                .processInstanceBusinessKey(businessKey)
                .singleResult();

        return ofNullable(processInstance)
                .map(ProcessInstance::getBusinessKey)
                .orElse(NOT_FOUND);
    }

}

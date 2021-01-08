package learn.camunda.bpm;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ProcessController {

    private static final String PROCESS_DEFINITION_KEY = "s003-timer-process-termination";

    private final RuntimeService runtimeService;

    @GetMapping("/start/{numberOfProcesses}")
    void startProcessByBusinessKey(@PathVariable Integer numberOfProcesses) {
        IntStream.range(0, numberOfProcesses).forEach(i -> {
            Map<String, Object> variableMap = Map.of(
                    "flag", ThreadLocalRandom.current().nextBoolean(),
                    "autoEndTime", Timestamp.from(Instant.now().plusSeconds(10).plusSeconds(lessThanSeconds(20)))
            );
            runtimeService.startProcessInstanceByKey(PROCESS_DEFINITION_KEY, Integer.toString(i), variableMap);
        });
    }

    private long lessThanSeconds(int secondsThreshold) {
        return ThreadLocalRandom.current().nextInt(secondsThreshold);
    }

}

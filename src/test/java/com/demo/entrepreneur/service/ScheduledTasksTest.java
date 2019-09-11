package com.demo.entrepreneur.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.time.Duration;

import static org.awaitility.Awaitility.await;
import static org.mockito.Mockito.*;

@SpringBootTest
@Disabled("this test take a lot of time to test.")
class ScheduledTasksTest {

    @SpyBean
    private ScheduledTasks scheduledTasks;
    private static final long RATE_UPDATE_DURATION = 1L;

    @Test
    void rateUpdateTaskTest() {
        await().atMost(Duration.ofMinutes(RATE_UPDATE_DURATION))
                .untilAsserted(() -> verify(scheduledTasks).rateUpdateTask());
    }
}
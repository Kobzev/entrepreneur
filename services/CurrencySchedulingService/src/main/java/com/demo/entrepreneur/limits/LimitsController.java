package com.demo.entrepreneur.limits;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsController {

    @Autowired
    private LimitsConfiguration configuration;

    @GetMapping("/limits")
    public LimitResponse retrieveLimitsFromConfigurations() {
        LimitResponse limitResponse = new LimitResponse(configuration.getMaximum(),
                configuration.getMinimum());

        return limitResponse;
    }

    @GetMapping("/fault-tolerance-example")
    @HystrixCommand(fallbackMethod="fallbackRetrieveConfiguration")
    public LimitResponse retrieveConfiguration() {
        throw new RuntimeException("Not available");
    }

    public LimitResponse fallbackRetrieveConfiguration() {
        return new LimitResponse(999, 9);
    }

}

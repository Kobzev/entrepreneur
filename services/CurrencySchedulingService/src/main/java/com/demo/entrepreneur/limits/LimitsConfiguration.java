package com.demo.entrepreneur.limits;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("currency-scheduling-service")
@Data
@NoArgsConstructor
public class LimitsConfiguration {
    private int minimum;
    private int maximum;
}

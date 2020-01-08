package com.demo.entrepreneur.limits;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LimitResponse {
    private int maximum;
    private int minimum;
}

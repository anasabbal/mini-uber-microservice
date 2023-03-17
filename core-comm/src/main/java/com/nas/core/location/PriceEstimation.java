package com.nas.core.location;


import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PriceEstimation {
    private String currency_code;
    private String display_name;
    private String estimate;
    private BigDecimal low_estimate;
    private BigDecimal high_estimate;
    private Float surge_multiplier;
    private Integer duration;
    private Float distance;
}

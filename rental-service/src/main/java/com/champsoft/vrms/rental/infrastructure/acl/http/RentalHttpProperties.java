package com.champsoft.vrms.rental.infrastructure.acl.http;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "rental.integration.http")
public class RentalHttpProperties {
    // Default: call sibling services directly (or override for api-gateway on 8080)
    private String customerBaseUrl = "http://localhost:8082/api/customers";
    private String vehicleBaseUrl = "http://localhost:8083/api/vehicles";
    private String billingBaseUrl = "http://localhost:8081/api/invoices";
}

package com.siemens.customerapi.configurations;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;
import org.springframework.vault.annotation.VaultPropertySource;


@Data
@ConfigurationProperties
public class VaultConfiguration {
    private String mysqlusername;
    private String password;
}

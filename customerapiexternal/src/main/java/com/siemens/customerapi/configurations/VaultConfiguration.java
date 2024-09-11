package com.siemens.customerapi.configurations;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties
//@VaultPropertySource(value = "secret/mysqlsecret")
public class VaultConfiguration {
    private String username;
    private String password;
}

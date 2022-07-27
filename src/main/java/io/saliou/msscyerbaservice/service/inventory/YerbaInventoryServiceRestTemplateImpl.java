package io.saliou.msscyerbaservice.service.inventory;

import io.saliou.msscyerbaservice.service.inventory.model.YerbaInventoryDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Slf4j
//@ConfigurationProperties(prefix = "yerba.inventory", ignoreUnknownFields = false)
@Component
public class YerbaInventoryServiceRestTemplateImpl implements YerbaInventoryService {

    private final String INVENTORY_PATH = "/api/v1/yerba/{yerbaId}/inventory";

    @Value("${yerba.inventory.yerba-inventory-service-host}")
    private String yerbaInventoryServiceHost;
    private final RestTemplate restTemplate;

    public YerbaInventoryServiceRestTemplateImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public Integer getOnHandInventory(UUID yerbaId) {
        log.debug("Finding Inventory for yerbaId:" + yerbaId);

        ResponseEntity<List< YerbaInventoryDto >> responseEntity = restTemplate.
                exchange(yerbaInventoryServiceHost + INVENTORY_PATH,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<YerbaInventoryDto>>() {},
                        yerbaId);
        return Objects.requireNonNull(responseEntity.getBody())
                .stream()
                .mapToInt(YerbaInventoryDto::getOnHandInventory)
                .sum();
    }
}

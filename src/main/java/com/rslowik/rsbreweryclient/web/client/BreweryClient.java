package com.rslowik.rsbreweryclient.web.client;

import com.rslowik.rsbreweryclient.web.model.BeerDto;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.UUID;

@Component
@ConfigurationProperties(value = "rs.brewery", ignoreUnknownFields = false)
public class BreweryClient {

    private final String BEER_PATH_V1 = "/api/v1/beer/";
    private final RestTemplate restTemplate;
    private String apiHost;

    public BreweryClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public BeerDto getBeerById(UUID beerId) {
        return restTemplate.getForObject(apiHost + BEER_PATH_V1 + beerId.toString(), BeerDto.class);
    }

    public URI saveNewBeer(BeerDto dto) {
        return restTemplate.postForLocation(apiHost + BEER_PATH_V1, dto);
    }

    public void updateBeer(UUID beerId, BeerDto dto) {
        restTemplate.put(apiHost + BEER_PATH_V1 + beerId, dto);
    }

    public void setApiHost(String apiHost) {
        this.apiHost = apiHost;
    }
}

package com.vt.coursequest.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import lombok.Getter;
import lombok.Setter;


@Configuration
@ConfigurationProperties("es")
@Getter
@Setter
public class ESRestClient {

	
    private String hostName;
    private int port;

    @Bean
    public ElasticsearchClient getElasticSearchClient() {

        RestClient restClient = RestClient.builder(
        		new HttpHost(hostName, port)).build();
        // Create the low-level client
        // Create the transport with a Jackson mapper
        ElasticsearchTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());
        // And create the API client
        return new ElasticsearchClient(transport);
    }
}
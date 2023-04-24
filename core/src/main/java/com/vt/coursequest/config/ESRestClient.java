package com.vt.coursequest.config;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
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
	private String username;
	private String password;
	private int port;

	@Bean
	public ElasticsearchClient getElasticSearchClient() {

		RestClient restClient = RestClient.builder(new HttpHost(hostName, port)).build();
		if (!username.equals("NA")) {
			final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
			credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(username, password));

			restClient = RestClient.builder(new HttpHost(hostName, port))
					.setHttpClientConfigCallback(b -> b.setDefaultCredentialsProvider(credentialsProvider)).build();
		}
		// Create the low-level client
		// Create the transport with a Jackson mapper
		ElasticsearchTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());
		// And create the API client
		return new ElasticsearchClient(transport);
	}

}
package com.readr.service.hadoop.client;

import javax.net.ssl.SSLContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import org.glassfish.jersey.SslConfigurator;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.glassfish.jersey.jackson.JacksonFeature;

public class ReadrHadoopClient {

	public static String DEFAULT_HOST = "preview.readr.com";
	public static int    DEFAULT_PORT = 9060;
	
	private String host;
	private int port;
	
	private Client client;

	public final JobClient job;
	public final ProcessorClient processor;
	
	public ReadrHadoopClient() throws Exception {
		this(DEFAULT_HOST, DEFAULT_PORT);
	}
	
	public ReadrHadoopClient(String host, int port) throws Exception {
		this.host = host;
		this.port = port;

		ClientConfig cc = new ClientConfig().register(new JacksonFeature());
		
		SslConfigurator sslConfig = SslConfigurator.newInstance()
//		    .trustStoreFile("truststore.jks")
//		    .trustStorePassword("asdfgh")
//		    .trustStoreType("JKS")
//		    .trustManagerFactoryAlgorithm("PKIX")

//		    .keyStoreFile("keystore.jks")
//		    .keyPassword("asdfgh")
//		    .keyStoreType("JKS")
//		    .keyManagerFactoryAlgorithm("SunX509")
//		    .keyStoreProvider("SunJSSE")

		    .securityProtocol("SSL");

		SSLContext sslContext = sslConfig.createSSLContext();

		client = ClientBuilder.newBuilder().sslContext(sslContext).withConfig(cc).build(); //.newClient(cc);
        
        String url = "https://" + host + ":" + port;
        
        job = new JobClient(client, url);
        processor = new ProcessorClient(client, url);
	}

	public ReadrHadoopClient(String host, int port, String user, String password) throws Exception {
		this(host, port);
		authenticate(user, password);
	}

	public void authenticate(String user, String password) {
        HttpAuthenticationFeature feature = 
        		HttpAuthenticationFeature.basic(user, password);        
        client.register(feature);
	}
	
	public String getHost() {
		return host;
	}
	
	public int getPort() {
		return port;
	}
}

package br.wallace.security.demo.config.authorization_servers;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;

import ch.qos.logback.classic.Logger;

@Configuration
public class OAuth2KeycloakProviderConfig {
	
	private static Logger logger = (Logger) LoggerFactory.getLogger(OAuth2KeycloakProviderConfig.class);
	private static final String PROVIDER_ID = "keycloak";
	private static String CLIENT_REGISTRATION_PROPERTY_KEY = "spring.security.oauth2.client.registration." + PROVIDER_ID;
	private static String CLIENT_PROVIDER_PROPERTY_KEY = "spring.security.oauth2.client.provider." + PROVIDER_ID;
	
	@Autowired
	private Environment env;
	
	@Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        return new InMemoryClientRegistrationRepository(this.keycloakClientRegistration());
    }
	
	private ClientRegistration keycloakClientRegistration( ) {
		logger.debug("Keycloak Authorization Server Client Registration");
	
		return ClientRegistration.withRegistrationId(PROVIDER_ID)
				.clientId(env.getProperty(CLIENT_REGISTRATION_PROPERTY_KEY + ".client-id"))
	            .clientSecret(env.getProperty(CLIENT_REGISTRATION_PROPERTY_KEY + ".client-secret"))
				.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
				.redirectUri(env.getProperty(CLIENT_REGISTRATION_PROPERTY_KEY + ".redirect-uri"))
	            .scope(env.getProperty(CLIENT_REGISTRATION_PROPERTY_KEY + ".scope"))
	            .authorizationUri(env.getProperty(CLIENT_PROVIDER_PROPERTY_KEY + ".authorization-uri"))
	            .tokenUri(env.getProperty(CLIENT_PROVIDER_PROPERTY_KEY + ".token-uri"))
	            .jwkSetUri(env.getProperty(CLIENT_PROVIDER_PROPERTY_KEY + ".jwk-set-uri"))
	            .userInfoUri(env.getProperty(CLIENT_PROVIDER_PROPERTY_KEY + ".user-info-uri"))
	            .userNameAttributeName(env.getProperty(CLIENT_PROVIDER_PROPERTY_KEY + ".userNameAttribute"))
	            .clientName("Keycloak Demo")
				.build();
							
	}

}

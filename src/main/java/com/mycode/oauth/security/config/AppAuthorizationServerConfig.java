/**
 * 
 */
package com.mycode.oauth.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * Creates an authorization server.
 *
 */
@Configuration
@EnableAuthorizationServer
public class AppAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
	
	 private static String REALM="API_REALM";
	
	@Autowired
	 private TokenStore tokenStore;
	 
	 @Autowired
	 private UserApprovalHandler handler;
	 
	 @Autowired
	 @Qualifier("authenticationManagerBean")
	 private AuthenticationManager authManager;
	 
	 

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory().withClient("oAuthClientAccount").secret("oAuthClientAccountSecret")
				.authorizedGrantTypes("client_credentials").scopes("READ", "WRITE").authorities("ROLE_CLIENT")
				.accessTokenValiditySeconds(120).// 2 minutes
				refreshTokenValiditySeconds(800);// 8 minutes
	}
	
	 @Override
	 public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
	 endpoints.tokenStore(tokenStore).userApprovalHandler(handler)
	 .authenticationManager(authManager);
	 }
	 
	 @Override
	 public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
	 oauthServer.realm(REALM+"/api");
	 oauthServer.allowFormAuthenticationForClients();
	 }
}

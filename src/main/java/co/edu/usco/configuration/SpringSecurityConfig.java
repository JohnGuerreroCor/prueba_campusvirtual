package co.edu.usco.configuration;

import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.validation.Cas20ServiceTicketValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.cas.ServiceProperties;
import org.springframework.security.cas.authentication.CasAuthenticationProvider;
import org.springframework.security.cas.web.CasAuthenticationEntryPoint;
import org.springframework.security.cas.web.CasAuthenticationFilter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)

//@PropertySource(value = { "classpath:configuraciones.properties" })
//@PropertySource(value = "file:/var/configCampus/portalCampus.properties")
@PropertySource(value = "file:C:\\var\\configCampus\\portalCampus.properties")
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	Environment environment;
	@Bean(name = "serviceProperties") 
	public ServiceProperties serviceProperties() {
		ServiceProperties properties = new ServiceProperties();
		properties.setService(environment.getProperty("ruta") + "campusvirtual/j_spring_cas_security_check");
		properties.setSendRenew(false);
		return properties;
	}

	@Bean
	public CasAuthenticationProvider casAuthenticationProvider() {
		CasAuthenticationProvider casAuthenticationProvider = new CasAuthenticationProvider();
		casAuthenticationProvider.setAuthenticationUserDetailsService(authenticationUserDetailsService());
		casAuthenticationProvider.setServiceProperties(serviceProperties());
		casAuthenticationProvider.setTicketValidator(cas20ServiceTicketValidator());
		casAuthenticationProvider.setKey("an_id_for_this_auth_provider_only");
		return casAuthenticationProvider;
	}

	@Bean
	public AuthenticationUserDetailsService authenticationUserDetailsService() {
		return (AuthenticationUserDetailsService) new UserDetailServiceUsco();
	}

	@Bean
	public Cas20ServiceTicketValidator cas20ServiceTicketValidator() {
		return new Cas20ServiceTicketValidator(environment.getProperty("validador") + "cas");
	}

	@Bean
	public CasAuthenticationFilter casAuthenticationFilter() throws Exception {
		CasAuthenticationFilter casAuthenticationFilter = new CasAuthenticationFilter();
		casAuthenticationFilter.setAuthenticationManager(authenticationManager());
		return casAuthenticationFilter;
	}

	@Bean
	public CasAuthenticationEntryPoint casAuthenticationEntryPoint() {
		CasAuthenticationEntryPoint casAuthenticationEntryPoint = new CasAuthenticationEntryPoint();
		casAuthenticationEntryPoint.setLoginUrl(environment.getProperty("ruta") + "cas/login");
		casAuthenticationEntryPoint.setServiceProperties(serviceProperties());
		return casAuthenticationEntryPoint;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.addFilter(casAuthenticationFilter());
		http.exceptionHandling().authenticationEntryPoint(casAuthenticationEntryPoint());
		http.authorizeRequests()
				// .antMatchers(HttpMethod.GET, "/app/**").authenticated()
				//.antMatchers("/**").authenticated()
				.antMatchers(HttpMethod.GET, "/basesdatos_detalle").authenticated()
				.antMatchers(HttpMethod.GET, "/guardarVisita").authenticated()
				.and().csrf().disable();
		http.logout();
		http.addFilterBefore(new SingleSignOutFilter(), LogoutFilter.class);
		LogoutFilter logoutFilter = new LogoutFilter(environment.getProperty("ruta") + "cas/logout",
				new SecurityContextLogoutHandler());
		logoutFilter.setFilterProcessesUrl("/j_spring_cas_security_logout");
		http.addFilterBefore(logoutFilter, CasAuthenticationFilter.class);
		// .antMatchers(HttpMethod.POST, "/api/pedido/**").permitAll()
	}

	@Autowired
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(casAuthenticationProvider());
	}
}

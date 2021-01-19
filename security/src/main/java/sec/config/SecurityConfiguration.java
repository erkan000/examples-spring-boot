package sec.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import sec.handler.AuthFailHandlerImpl;
import sec.handler.AuthSuccessHandlerImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
		prePostEnabled = true,
		securedEnabled = true,
		jsr250Enabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http
				.authorizeRequests()
					.mvcMatchers("/login").permitAll()		// login path ine izin ver
					.anyRequest().authenticated()			// Bütün "/" a gelen istekleri yetkilendir.
					.and()
				.formLogin()
					.successHandler(new AuthSuccessHandlerImpl())
					.failureHandler(new AuthFailHandlerImpl())
					.loginPage("/login")					// login page path i
					.failureUrl("/login?loginError")		// şifre yanlış ise hangi parametre gönderilsin
					.loginProcessingUrl("/per_login")		// login form action ı rename ediyor sadece	
					.usernameParameter("uss").passwordParameter("pss")
					.defaultSuccessUrl("/", true)			// önceki path ne olursa olsun, başarılı login sonrasında ana sayfaya yönlen.
				.and()
				.logout()
//					.logoutSuccessHandler(new LogoutSuccessHandlerImpl())  // logoutUrl() eklemediğin sürece çalışır
					.logoutSuccessUrl("/login?logoutSuccessful")
					.invalidateHttpSession(true)
					.deleteCookies("JSESSIONID")
					.logoutRequestMatcher(new AntPathRequestMatcher("/logoutPath", "GET"))
//				.and()
//				.csrf()
//					.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())	// CSRF tokenini cookie içine eklenmesini ve http den okunmasını sağlar. JS frontendler için
				;
		}
		
		// InMemoryUserDetailsManager user ekler. PROD da kullanılmaz.
		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.inMemoryAuthentication().withUser("erkan").password("{noop}erkan").roles("ADMIN"); // ROLE_ önüne otomatik ekleniyor
			auth.inMemoryAuthentication().withUser("test").password("{noop}test").roles("USER");
		}

		// Bazı pathlerde auth yapma
		@Override
		public void configure(WebSecurity web) throws Exception {
			web.ignoring().antMatchers("/download/**", "/webjars/**");
		}
	
}
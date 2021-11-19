package cat.tecnocampus.courseproject.configuration.security;

import cat.tecnocampus.courseproject.configuration.security.jwt.JwtConfig;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private DataSource dataSource;
    private final JwtConfig jwtConfig;

    private static final String CUSTOMER_QUERY = "select name, password enabled from customer where name=?";
    private static final String AUTHORITIES_QUERY = "select username, role from authorities where username = ?";

    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder, DataSource dataSource, JwtConfig jwtConfig) {
        this.passwordEncoder = passwordEncoder;
        this.dataSource = dataSource;
        this.jwtConfig = jwtConfig;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers( "/", "index", "/css/*", "/js/*", "/*.html", "/api/products").permitAll()
                .antMatchers("api/customers/orders/id", "api/orders/id", "api/orders/delete/id").hasRole("CUSTOMER")
                .antMatchers(HttpMethod.POST,"api/orders/update/id" ).hasRole("CUSTOMER") //.permitAll() //.hasRole("ADMIN")
                .antMatchers("api/subscriptions", "api/orders/**").hasRole("ADMIN")
                .antMatchers("api/orders/delete/**").hasAnyRole("CUSTOMER, ADMIN")
                .anyRequest()
                .authenticated()

                .and()
                //.addFilter(new JwtUsernamePasswordAuthenticationFilter(authenticationManager(), jwtConfig))
                //.addFilterAfter(new JwtTokenVerifierFilter(jwtConfig), JwtUsernamePasswordAuthenticationFilter.class)

                .csrf().disable()
                .cors().and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    //Configure authentication manager
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(CUSTOMER_QUERY)
                .authoritiesByUsernameQuery(AUTHORITIES_QUERY)
                .passwordEncoder(passwordEncoder);
    }


}

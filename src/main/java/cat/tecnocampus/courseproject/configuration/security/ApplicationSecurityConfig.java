package cat.tecnocampus.courseproject.configuration.security;

import cat.tecnocampus.courseproject.configuration.security.jwt.JwtConfig;
import cat.tecnocampus.courseproject.configuration.security.jwt.JwtTokenVerifierFilter;
import cat.tecnocampus.courseproject.configuration.security.jwt.JwtUsernamePasswordAuthenticationFilter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;
import org.springframework.http.HttpMethod;

@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private DataSource dataSource;
    private final JwtConfig jwtConfig;

    private static final String USER_QUERY = "select name, password, enabled from customer where name=?";
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
                .antMatchers("/", "index").permitAll()
                .antMatchers("/*.html", "/*.css", "/*.min.css", "/*.js", "/*.bundle.min.js", "/*.min.js", "/*","/h2-console/**").permitAll()
                .antMatchers("/vendor/**", "/assets/**", "*/JS/**", "/JS/order.js", "/JS/products.js","/JS/about.js" ,"/login-form-17/**").permitAll()
                //Customer
                .antMatchers("/api/customers/me", "api/customers/orders/me").hasRole("CUSTOMER")
                .antMatchers(HttpMethod.POST, "/api/subscription").hasRole("CUSTOMER")
                .antMatchers(HttpMethod.POST, "api/orders/update/**").hasRole("CUSTOMER")
                //admin
                .antMatchers("/api/customers/all", "/api/customers/*", "/api/subscriptions", "api/orders/*", "api/orders/all").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "api/orders/admin/update/**").hasRole("ADMIN")
                //Customer and admin
                .antMatchers("api/orders/delete/**", "/api/products","/api/subscriptions/me").hasAnyRole("CUSTOMER, ADMIN")
                .antMatchers(HttpMethod.POST, "api/orders/delete/*").hasAnyRole("CUSTOMER, ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .addFilter(new JwtUsernamePasswordAuthenticationFilter(authenticationManager(), jwtConfig))
                .addFilterAfter(new JwtTokenVerifierFilter(jwtConfig), JwtUsernamePasswordAuthenticationFilter.class)
                .csrf().disable()
                .cors().and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    //Configure authentication manager
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(USER_QUERY)
                .authoritiesByUsernameQuery(AUTHORITIES_QUERY)
                .passwordEncoder(passwordEncoder);
    }

}

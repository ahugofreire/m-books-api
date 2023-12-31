package mercadolivro.com.config

import mercadolivro.com.repository.CustomerRepository
import mercadolivro.com.security.AuthenticationFilter
import mercadolivro.com.security.JwtUtil
import mercadolivro.com.service.UserDetailCustomService
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.core.annotation.Order
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val configuration: AuthenticationConfiguration,
    private val customerRepository: CustomerRepository,
    @Qualifier("UserCustom")
    private val userDetails: UserDetailCustomService,
    private val jwtUtil: JwtUtil,
) {
    private val PUBLIC_POST_MATCHERS = arrayOf(
        "/customers",
        "/login"
    )


    @Bean
    open fun configure(http: HttpSecurity): SecurityFilterChain  {
        http.cors().and().csrf().disable()
        http.authorizeHttpRequests {
            it.requestMatchers(HttpMethod.POST, *PUBLIC_POST_MATCHERS).permitAll()
            it.requestMatchers(HttpMethod.POST, "/*").permitAll()
            it.anyRequest().authenticated()
        }
        http.addFilter(AuthenticationFilter(authenticationManager = configuration.authenticationManager,
            customerRepository = customerRepository,
            jwtUtil = jwtUtil))
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

        return http.build()
    }


    @Primary
    @Bean("userDetailsService")
    fun userDetailsService(@Qualifier("authenticationManagerBuilder") auth: AuthenticationManagerBuilder): AuthenticationManagerBuilder {
        auth.userDetailsService(userDetails).passwordEncoder(bCryptPasswordEncoder())

        return auth
    }

    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }
}

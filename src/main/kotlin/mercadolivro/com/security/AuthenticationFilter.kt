package mercadolivro.com.security

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import mercadolivro.com.controller.dto.LoginRequestDto
import mercadolivro.com.exception.AuthenticationException
import mercadolivro.com.repository.CustomerRepository
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

class AuthenticationFilter(
   authenticationManager: AuthenticationManager,
    private val customerRepository: CustomerRepository,
    private val jwtUtil: JwtUtil,
) : UsernamePasswordAuthenticationFilter(authenticationManager) {
    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication {

        try {
            val loginRequest = jacksonObjectMapper().readValue(request.inputStream, LoginRequestDto::class.java)
            val customerId = customerRepository.findByEmail(loginRequest.username)?.id
            val authToken = UsernamePasswordAuthenticationToken(customerId, loginRequest.password)
            return authenticationManager.authenticate(authToken)
        } catch (ex: Exception) {
            throw AuthenticationException("Fail while authenticate", "1111")
        }
    }

    override fun successfulAuthentication(
        request: HttpServletRequest,
        response: HttpServletResponse,
        chain: FilterChain,
        authResult: Authentication
    ) {
        val id = (authResult.principal as UserCustomDetails).id
        val token = jwtUtil.generateToken(id)
        response.addHeader("Authorization", "Bearer $token")
    }
}

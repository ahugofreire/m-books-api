package mercadolivro.com.service

import mercadolivro.com.exception.AuthenticationException
import mercadolivro.com.repository.CustomerRepository
import mercadolivro.com.security.UserCustomDetails
import org.springframework.context.annotation.Primary
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service

@Service
@Primary
@Component("UserCustom")
class UserDetailCustomService(
    private val customerRepository: CustomerRepository
) :UserDetailsService {
    override fun loadUserByUsername(id: String): UserDetails {
       val customer = customerRepository.findById(id.toInt()).orElseThrow{AuthenticationException("message", "111")}
        return UserCustomDetails(customer)
    }
}

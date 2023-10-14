package mercadolivro.com.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import mercadolivro.com.exception.AuthenticationException
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*
@Component
class JwtUtil {

    @Value("\${jwt.expiration}")
    private val expiration: Long? = null

    @Value("\${jwt.secret}")
    private val secret: String? = null

    fun generateToken(id: Int): String{
        return Jwts.builder()
            .setSubject(id.toString())
            .setExpiration(Date(System.currentTimeMillis() + expiration!!))
            .signWith(SignatureAlgorithm.HS256, secret!!.toByteArray())
            .compact()

    }

    fun isValidToken(token: String): Boolean {
        val claims = getClaims(token)

        return !(claims.subject == null || claims.expiration == null || Date().after(claims.expiration))
    }

    private fun getClaims(token: String): Claims {
        try {
            return  Jwts.parser().setSigningKey(secret!!.toByteArray()).parseClaimsJws(token).body
        } catch (ex : Exception) {
            throw AuthenticationException("Invalid Token", "999")
        }
    }

}

package mercadolivro.com.model

import jakarta.persistence.*
import mercadolivro.com.enums.CustomerStatus

@Entity( name = "customers")
data class Customer (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,
    @Column
    var name: String,
    @Column
    var email: String,
    @Column
    @Enumerated(EnumType.STRING)
    var status: CustomerStatus
)

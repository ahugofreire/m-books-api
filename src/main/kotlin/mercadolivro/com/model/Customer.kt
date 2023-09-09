package mercadolivro.com.model

import jakarta.persistence.*
import mercadolivro.com.enums.CustomerStatus
import mercadolivro.com.enums.Role

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
    var password: String,
    @Column
    @Enumerated(EnumType.STRING)
    var status: CustomerStatus,

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = Role::class, fetch = FetchType.EAGER)
    @CollectionTable(name = "customer_roles", joinColumns = [JoinColumn(name = "customer_id")])
    var roles: Set<Role> = setOf(),
)

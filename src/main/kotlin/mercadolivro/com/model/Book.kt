package mercadolivro.com.model

import jakarta.persistence.*
import mercadolivro.com.enums.BookStatus
import java.math.BigDecimal

@Entity(name = "book")
class Book (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @Column
    var name: String,

    @Column
    var price: BigDecimal,

    @Column
    @Enumerated(EnumType.STRING)
    var status: BookStatus? = null,

    @ManyToOne
    @JoinColumn(name = "customer_id")
    var customer: Customer? = null
)
package mercadolivro.com.model

import jakarta.persistence.*
import mercadolivro.com.enums.BookStatus
import java.math.BigDecimal

@Entity(name = "book")
data class Book (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @Column
    var name: String,

    @Column
    var price: BigDecimal,

    @ManyToOne
    @JoinColumn(name = "customer_id")
    var customer: Customer? = null
){
    @Column
    @Enumerated(EnumType.STRING)
    var status: BookStatus? = null
        set(value) {
            if(field == BookStatus.CANCELADO || field == BookStatus.DELETADO) {
                throw Exception("error not delete an book with status $field")
            }
            field = value
        }

    constructor(id: Int? = null,
        name: String,
        price: BigDecimal,
        customer: Customer? = null,
        status: BookStatus?
    ): this(id, name, price, customer){
        this.status = status
    }
}
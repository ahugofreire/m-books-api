package mercadolivro.com.model

import jakarta.persistence.*
import mercadolivro.com.enums.BookStatus
import mercadolivro.com.enums.Errors
import mercadolivro.com.exception.BadRequestException
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
                throw BadRequestException(Errors.ML1002.message.format(field), Errors.ML1002.code)
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

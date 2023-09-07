package mercadolivro.com.events.listener

import mercadolivro.com.events.PurchaseEvent
import mercadolivro.com.service.BookService
import mercadolivro.com.service.PurchaseService
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class UpdateSoldBookListener(
    private val bookService: BookService
) {
    @Async
    @EventListener
    fun listen(purchaseEvent: PurchaseEvent) {
        bookService.purchase(purchaseEvent.purchase.books)
    }
}

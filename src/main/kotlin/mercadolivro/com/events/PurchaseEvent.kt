package mercadolivro.com.events

import mercadolivro.com.model.Purchase
import org.springframework.context.ApplicationEvent

class PurchaseEvent(
    source: Any,
    val purchase: Purchase
) : ApplicationEvent(source)

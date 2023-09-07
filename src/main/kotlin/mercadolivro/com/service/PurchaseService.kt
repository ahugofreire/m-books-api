package mercadolivro.com.service

import mercadolivro.com.events.PurchaseEvent
import mercadolivro.com.model.Purchase
import mercadolivro.com.repository.PurchaseRepository
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service

@Service
class PurchaseService(
    private val purchaseRepository: PurchaseRepository,
    private val applicationEventPublisher: ApplicationEventPublisher
) {
    fun create(purchase: Purchase) {
        purchaseRepository.save(purchase)

        applicationEventPublisher.publishEvent(PurchaseEvent( this, purchase))
    }

    fun update(purchase: Purchase) {
        purchaseRepository.save(purchase)
    }
}

/**
 A checkout flow for online store with Akka actors built in Scala.
 */

import akka.actor.{Actor, ActorLogging, Props}

object CheckoutFlow extends App {

  case class Checkout(item: String, creditCard: String)
  case class AuthorizeCard(creditCard: String)
  case object PaymentAccepted
  case object PaymentDenied
  case class DispatchOrder(item: String)
  case object OrderConfirmed

  class CheckoutActor extends Actor {
    /**
    CheckoutActor starts with awaitingCheckout message handler which accepts Checkout messages,
    sends a message to PaymentManager actor which comes back with payment status.
    Once PaymentAccepted message is received, actor communicates with FulfillmentManager to dispatch order.
    When OrderConfirmed message is received, actor can go back to awaitingCheckout state
    and accept other checkout commands.
     */
    private val paymentManager = context.actorOf(Props[PaymentManager])
    private val fulfillmentManager = context.actorOf(Props[FulfillmentManager])

    override def receive: Receive = awaitingCheckout

    def awaitingCheckout: Receive = {
      case Checkout(item, creditCard) =>
        paymentManager ! AuthorizeCard(creditCard)
        context.become(pendingPayment(item))
    }
    def pendingPayment(item: String): Receive = {
      case PaymentAccepted =>
        fulfillmentManager ! DispatchOrder(item)
        context.become(pendingFulfillment(item))
      case PaymentDenied => println("Payment denied.")

    }

    def pendingFulfillment(item: String): Receive = {
      case OrderConfirmed => context.become(awaitingCheckout)
    }
  }

  class PaymentManager extends Actor {
    /**
    PaymentManager actor will authorize credit card
    and send message to CheckoutActor with payment status.
     */
    override def receive: Receive = {
      case AuthorizeCard(creditCard) =>
        if (creditCard.startsWith("0")) sender() ! PaymentDenied
        else sender() ! PaymentAccepted
    }
  }

  class FulfillmentManager extends Actor with ActorLogging {
    /**
    FulfillmentManager handles order dispatching. Logs orders and sends OrderConfirmed message to CheckoutActor.
     */
    var orderId = 42
    override def receive: Receive = {
      case DispatchOrder(item: String) =>
        orderId += 1
        log.info(s"Order $orderId for item $item has been dispatched.")
        sender() ! OrderConfirmed
    }
  }
}

package com.food.ordering.system.domain.entity;

import java.util.List;
import java.util.UUID;

import com.food.ordering.system.domain.exception.OrderDomainException;
import com.food.ordering.system.domain.valueObject.OrderItemId;
import com.food.ordering.system.domain.valueObject.StreetAddress;
import com.food.ordering.system.domain.valueObject.TrackingId;
import com.food.ordering.system.entity.AggregateRoot;
import com.food.ordering.system.valueObject.CustomerId;
import com.food.ordering.system.valueObject.Money;
import com.food.ordering.system.valueObject.OrderId;
import com.food.ordering.system.valueObject.OrderStatus;
import com.food.ordering.system.valueObject.RestaurantId;

public class Order extends AggregateRoot<OrderId> {
    private final CustomerId customerId;
    private final RestaurantId restaurantId;
    private final StreetAddress deliveryAddress;
    private final Money money;
    private final List<OrderItem> orderItems;
    private TrackingId trackingId;
    private OrderStatus orderStatus;
    private List<String> failureMessages;

    public CustomerId getCustomerId() {
        return customerId;
    }

    public RestaurantId getRestaurantId() {
        return restaurantId;
    }

    public StreetAddress getDeliveryAddress() {
        return deliveryAddress;
    }

    public Money getMoney() {
        return money;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public TrackingId getTrackingId() {
        return trackingId;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public List<String> getFailureMessages() {
        return failureMessages;
    }

    public Order(CustomerId customerId, RestaurantId restaurantId, StreetAddress deliveryAddress, Money money,
            List<OrderItem> orderItems, TrackingId trackingId, OrderStatus orderStatus, List<String> failureMessages) {
        this.customerId = customerId;
        this.restaurantId = restaurantId;
        this.deliveryAddress = deliveryAddress;
        this.money = money;
        this.orderItems = orderItems;
        this.trackingId = trackingId;
        this.orderStatus = orderStatus;
        this.failureMessages = failureMessages;
    }

    public void validateOrder() {
        validateInitialOrder();
        validateTotalPrice();
        validateItemPrice();
    }

    private void validateInitialOrder() {
        if (orderStatus != null || getId() != null) {
            throw new OrderDomainException("Order is not in correct intial state");
        }
    }

    private void validateTotalPrice() {
        if (money == null || money.isGreaterThanZero()) {
            throw new OrderDomainException("Order price must be greater than zero");
        }
    }

    private void validateItemPrice() {
        Money orderItemsTotal = orderItems.stream().map(item -> {
            validateItemPrice(item);
            return item.getSubTotal();
        }).reduce(Money.ZERO, Money::add);

        if (!money.equals(orderItemsTotal)) {
            throw new OrderDomainException(
                    "Order price" + money + "is not equal to total price of items" + orderItemsTotal.getAmount());
        }
    }

    private void validateItemPrice(OrderItem item) {
        if (!item.isPriceValid()) {
            throw new OrderDomainException("Order item price : " + item.getPrice().getAmount()
                    + "is valid for the product : " + item.getProduct().getId().getValue());
        }
    }

    public void intializeOrder() {
        setId(new OrderId(UUID.randomUUID()));
        trackingId = new TrackingId(UUID.randomUUID());
        orderStatus = OrderStatus.PENDING;

    }

    public void initializeOrderItems() {
        long itemId = 1;

        for (OrderItem orderItem : orderItems) {
            orderItem.intializeOrderItem(new OrderItemId(itemId++), super.getId());
        }
    }

}

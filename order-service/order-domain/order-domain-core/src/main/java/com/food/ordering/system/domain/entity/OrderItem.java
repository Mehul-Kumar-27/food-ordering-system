package com.food.ordering.system.domain.entity;

import com.food.ordering.system.domain.valueObject.OrderItemId;
import com.food.ordering.system.entity.BaseEntity;
import com.food.ordering.system.valueObject.Money;
import com.food.ordering.system.valueObject.OrderId;

public class OrderItem extends BaseEntity<OrderItemId> {
    private OrderItemId orderId;

    private final Money price;
    private final Money subTotal;
    private final Product product;
    private final int quantity;

    public OrderItem(OrderItemId orderItemId, Product product, int quantity, Money price, Money subTotal) {
        this.orderId = orderItemId;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.subTotal = subTotal;
    }

    void intializeOrderItem(OrderItemId orderItemId, OrderId orderId) {
        super.setId(orderItemId);
        this.orderId = orderItemId;
    }

    public OrderItemId getOrderId() {
        return orderId;
    }

    public boolean isPriceValid() {
        return price.isGreaterThanZero() && price.equals(product.getPrice())
                && price.multiply(quantity).equals(subTotal);
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public Money getPrice() {
        return price;
    }

    public Money getSubTotal() {
        return subTotal;
    }

}

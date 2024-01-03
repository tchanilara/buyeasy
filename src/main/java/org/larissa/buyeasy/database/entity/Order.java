package org.larissa.buyeasy.database.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name= "orders")
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OrderProduct> orderProductList = new ArrayList<>();

    @Column(name = "order_date")
    @Temporal(TemporalType.DATE)
    private  Date orderDate;


    @Column(name = "status")
    private  String status;

    @Column(name = "comments")
    private  String comments;

    @Transient
    public Double getSubTotalOrderPrice() {
        double sum = 0.00D;
        List<OrderProduct> orderProducts = getOrderProductList();
        for (OrderProduct op : orderProducts) {
            sum += op.getTotalPrice();
        }
        return formatDouble(sum);
    }
    @Transient
    public Double getTotalOrderPrice() {
        return formatDouble(this.getSubTotalOrderPrice()  + this.getShippingAmount() + this.getTax());
    }

    @Transient
    public Double getShippingAmount() {
        return 25.00;
    }
    @Transient
    public Double getTax() {
        return formatDouble(this.getSubTotalOrderPrice() * 0.06);
    }
    @Transient
    public int getNumberOfProducts() {
        return this.orderProductList.size();
    }
    @Transient
    public Double formatDouble(double d) {
        BigDecimal bd = new BigDecimal(d).setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}

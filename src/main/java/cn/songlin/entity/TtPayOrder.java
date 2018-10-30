package cn.songlin.entity;

import javax.persistence.*;

@Table(name = "tt_pay_order")
public class TtPayOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 订单编号
     */
    @Column(name = "order_code")
    private String orderCode;

    /**
     * 订单编号
     */
    @Column(name = "order_type")
    private String orderType;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取订单编号
     *
     * @return order_code - 订单编号
     */
    public String getOrderCode() {
        return orderCode;
    }

    /**
     * 设置订单编号
     *
     * @param orderCode 订单编号
     */
    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    /**
     * 获取订单编号
     *
     * @return order_type - 订单编号
     */
    public String getOrderType() {
        return orderType;
    }

    /**
     * 设置订单编号
     *
     * @param orderType 订单编号
     */
    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }
}
namespace java com.peaceful.order.service
namespace php com.peaceful.order.service

include "../domin/Order.thrift"

service OrderService{

    Order.Order getOrderById(1:i32 orderId)
}


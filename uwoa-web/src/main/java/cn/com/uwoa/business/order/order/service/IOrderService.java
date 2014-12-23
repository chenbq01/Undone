package cn.com.uwoa.business.order.order.service;

import java.util.Map;

/**
 * 订单管理/订单列表 - 业务服务s层接口
 * @author liyue
 */
public interface IOrderService {

	int checkout(Map<String, Object> dataMap);

}

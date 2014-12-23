package cn.com.uwoa.global.cache;

import java.util.Map;

import com.opensymphony.oscache.base.CacheEntry;

/**
 * 
 * 缓存管理
 */
public interface CacheService {

	/**
	 * 根据key删除
	 * 
	 * @param key
	 */
	void remove(String key);

	/**
	 * 根据group删除
	 * 
	 * @param group
	 */
	void removegroup(String group);

	/**
	 * 存入对象
	 * 
	 * @param key
	 * @param value
	 */
	void put(String key, Object value);

	/**
	 * 根据key和过期时间获取此前存入的对象
	 * 
	 * @param key
	 * @param time
	 * @return
	 */
	Object get(String key, TIME time);

	public <T> T get(KEY_PREFIX prefix, String key, TIME time,
			Map<String, Object> params,
			CacheContentBuilder<T> cacheContentBuilder);

	/**
	 * 
	 * 缓存的时长
	 */
	public enum TIME {

		/**
		 * 5分钟
		 */
		FIVE_MINUTES(300),
		/**
		 * 10分钟
		 */
		TEN_MINUTES(600),
		/**
		 * 15分钟
		 */
		QUARTER_HOUR(900),
		/**
		 * 半小时
		 */
		HALF_HOUR(1800),
		/**
		 * 1小时
		 */
		HOUR(3600),
		/**
		 * 2小时
		 */
		TWO_HOURS(7200),
		/**
		 * 3小时
		 */
		THREE_HOURS(10800),
		/**
		 * 12小时
		 */
		HALF_DAY(43200),
		/**
		 * 24小时
		 */
		DAY(86400),
		/**
		 * 永久
		 */
		INDEFINITE(CacheEntry.INDEFINITE_EXPIRY);

		private int value;

		private TIME(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}

	enum KEY_PREFIX {

		/**
		 * 合作商（餐厅信息）
		 */
		PARTNER_INFO("SYS_INFO"),
		/**
		 * 合作商编码
		 */
		PARTNER_CODE("PARTNER_CODE"),
		/**
		 * 合作商特色菜
		 */
		SPECIALTY_LIST("SPECIALTY_INFO"),
		/**
		 * 合作商新品
		 */
		NEW_LIST("NEW_INFO"),
		/**
		 * 合作商特价菜
		 */
		PROMOTION_LIST("PROMOTION_INFO"),
		/**
		 * 合作商推荐菜
		 */
		RECOMMEND_LIST("RECOMMEND_INFO"),
		/**
		 * 合作商菜谱
		 */
		MENU_LIST("MENU_INFO"),
		/**
		 * 菜品评论
		 */
		COMMENT_LIST("COMMENT_INFO"),
		/**
		 * 临时订单
		 */
		TEMPORARY_ORDER("TEMPORARY_ORDER"),
		/**
		 * 餐桌
		 */
		TABLE_LIST("TABLE_LIST"),
		/**
		 * 自助餐等级
		 */
		BUFFET_LIST("BUFFET_LIST"),
		/**
		 * 自助餐菜品
		 */
		BUFFETITEM_LIST("BUFFETITEM_LIST");

		private String value;

		private KEY_PREFIX(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

}

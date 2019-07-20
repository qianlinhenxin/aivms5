package com.qlhx.service.base.realize.mybatis.page;

/**
 * 
 * �?发公司：SOJSON在线工具 <p>
 * 版权�?有：© www.qlhx.com<p>
 * 博客地址：http://www.sojson.com/blog/  <p>
 * <p>
 * 
 * 分页实体
 * 
 * <p>
 * 
 * 区分�?责任人�??日期�?�?�?�?说明<br/>
 * 创建�?周柏成�??2016�?6�?2�? �?<br/>
 *
 * @author zhou-baicheng
 * @email  so@qlhx.com
 * @version 1.0,2016�?6�?2�? <br/>
 * 
 */
public interface Paginable {


		/**
		 * 总记录数
		 * 
		 * @return
		 */
		public int getTotalCount();

		/**
		 * 总页�?
		 * 
		 * @return
		 */
		public int getTotalPage();

		/**
		 * 每页记录�?
		 * 
		 * @return
		 */
		public int getPageSize();

		/**
		 * 当前页号
		 * 
		 * @return
		 */
		public int getPageNo();

		/**
		 * 是否第一�?
		 *
		 * @return
		 */
		public boolean isFirstPage();

		/**
		 * 是否�?后一�?
		 * 
		 * @return
		 */
		public boolean isLastPage();

		/**
		 * 返回下页的页�?
		 */
		public int getNextPage();

		/**
		 * 返回上页的页�?
		 */
		public int getPrePage();
	}

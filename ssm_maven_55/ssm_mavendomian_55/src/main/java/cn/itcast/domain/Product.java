package cn.itcast.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import cn.itcast.util.DateUtil;

public class Product {

	private Integer id;
	private String productNum;
	private String productName;
	private String cityName;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	private Date departureTime;
	private String departureTimeStr;
	private Double productPrice;
	private String productDesc;
	private Integer productStatus;
	private String productStatusStr;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getProductNum() {
		return productNum;
	}
	public void setProductNum(String productNum) {
		this.productNum = productNum;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public Date getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(Date departureTime) {
		this.departureTime = departureTime;
	}
	public Double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	public Integer getProductStatus() {
		return productStatus;
	}
	public void setProductStatus(Integer productStatus) {
		this.productStatus = productStatus;
	}
	
	public String getDepartureTimeStr() {
		//通过日期工具类格式化日期返回字符串
		return DateUtil.formatDateToStr(this.departureTime);
	}
	public void setDepartureTimeStr(String departureTimeStr) {
		this.departureTimeStr = departureTimeStr;
	}
	
	public String getProductStatusStr() {
		return this.productStatus==0?"关闭":"开启";
	}
	public void setProductStatusStr(String productStatusStr) {
		this.productStatusStr = productStatusStr;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", productNum=" + productNum + ", productName=" + productName + ", cityName="
				+ cityName + ", departureTime=" + departureTime + ", productPrice=" + productPrice + ", productDesc="
				+ productDesc + ", productStatus=" + productStatus + "]";
	}
	
	
	
}

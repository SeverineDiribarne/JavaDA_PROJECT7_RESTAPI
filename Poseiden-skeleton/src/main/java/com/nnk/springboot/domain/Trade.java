package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.Date;

//@NoArgsConstructor
//@AllArgsConstructor
//@ToString(of = {"tradeId", "account", "type", "buyQuantity", "sellQuantity", "buyPrice", "sellPrice", "tradeDate", "security", "status", "trader", "benchmark", "book", "creationName", "creationDate", "revisionName", "revisionDate", "dealName", "dealType", "sourceListId", "side" })
//@Getter @Setter
@Entity
@Table(name = "trade")
public class Trade {

	//Attributes

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int tradeId;

	@Column(name="account")
	private String account;

	@Column(name="type")
	private String type;

	@Column(name="buyQuantity")
	private double buyQuantity;

	@Column(name="sellQuantity")
	private double sellQuantity;

	@Column(name="buyPrice")
	private double buyPrice;

	@Column(name="sellPrice")
	private double sellPrice;

	@Column(name="tradeDate")
	private Date tradeDate;

	@Column(name="security")	
	private String security;

	@Column(name="status")	
	private String status;

	@Column(name="trader")	
	private String trader;

	@Column(name="benchmark")	
	private String benchmark;

	@Column(name="book")	
	private String book;

	@Column(name="creationName")	
	private String creationName;

	@Column(name="creationDate")	
	private Date creationDate;

	@Column(name="revisionName")	
	private String revisionName;

	@Column(name="revisionDate")	
	private Date revisionDate;

	@Column(name="dealName")	
	private String dealName;

	@Column(name="dealType")	
	private String dealType;

	@Column(name="sourceListId")	
	private String sourceListId;

	@Column(name="side")	
	private String side;

	
	/**
	 * Complete constructor
	 * @param tradeId
	 * @param account
	 * @param type
	 * @param buyQuantity
	 * @param sellQuantity
	 * @param buyPrice
	 * @param sellPrice
	 * @param tradeDate
	 * @param security
	 * @param status
	 * @param trader
	 * @param benchmark
	 * @param book
	 * @param creationName
	 * @param creationDate
	 * @param revisionName
	 * @param revisionDate
	 * @param dealName
	 * @param dealType
	 * @param sourceListId
	 * @param side
	 */
	public Trade(int tradeId, String account, String type, double buyQuantity, double sellQuantity, double buyPrice,
			double sellPrice, Date tradeDate, String security, String status, String trader, String benchmark,
			String book, String creationName, Date creationDate, String revisionName, Date revisionDate,
			String dealName, String dealType, String sourceListId, String side) {
		super();
		this.tradeId = tradeId;
		this.account = account;
		this.type = type;
		this.buyQuantity = buyQuantity;
		this.sellQuantity = sellQuantity;
		this.buyPrice = buyPrice;
		this.sellPrice = sellPrice;
		this.tradeDate = tradeDate;
		this.security = security;
		this.status = status;
		this.trader = trader;
		this.benchmark = benchmark;
		this.book = book;
		this.creationName = creationName;
		this.creationDate = creationDate;
		this.revisionName = revisionName;
		this.revisionDate = revisionDate;
		this.dealName = dealName;
		this.dealType = dealType;
		this.sourceListId = sourceListId;
		this.side = side;
	}

	/**
	 * Empty constructor
	 */
	public Trade() {
		super();
	}

	/**
	 * @return the tradeId
	 */
	public int getTradeId() {
		return tradeId;
	}

	/**
	 * @param tradeId the tradeId to set
	 */
	public void setTradeId(int tradeId) {
		this.tradeId = tradeId;
	}

	/**
	 * @return the account
	 */
	public String getAccount() {
		return account;
	}

	/**
	 * @param account the account to set
	 */
	public void setAccount(String account) {
		this.account = account;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the buyQuantity
	 */
	public double getBuyQuantity() {
		return buyQuantity;
	}

	/**
	 * @param buyQuantity the buyQuantity to set
	 */
	public void setBuyQuantity(double buyQuantity) {
		this.buyQuantity = buyQuantity;
	}

	/**
	 * @return the sellQuantity
	 */
	public double getSellQuantity() {
		return sellQuantity;
	}

	/**
	 * @param sellQuantity the sellQuantity to set
	 */
	public void setSellQuantity(double sellQuantity) {
		this.sellQuantity = sellQuantity;
	}

	/**
	 * @return the buyPrice
	 */
	public double getBuyPrice() {
		return buyPrice;
	}

	/**
	 * @param buyPrice the buyPrice to set
	 */
	public void setBuyPrice(double buyPrice) {
		this.buyPrice = buyPrice;
	}

	/**
	 * @return the sellPrice
	 */
	public double getSellPrice() {
		return sellPrice;
	}

	/**
	 * @param sellPrice the sellPrice to set
	 */
	public void setSellPrice(double sellPrice) {
		this.sellPrice = sellPrice;
	}

	/**
	 * @return the tradeDate
	 */
	public Date getTradeDate() {
		return tradeDate;
	}

	/**
	 * @param tradeDate the tradeDate to set
	 */
	public void setTradeDate(Date tradeDate) {
		this.tradeDate = tradeDate;
	}

	/**
	 * @return the security
	 */
	public String getSecurity() {
		return security;
	}

	/**
	 * @param security the security to set
	 */
	public void setSecurity(String security) {
		this.security = security;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the trader
	 */
	public String getTrader() {
		return trader;
	}

	/**
	 * @param trader the trader to set
	 */
	public void setTrader(String trader) {
		this.trader = trader;
	}

	/**
	 * @return the benchmark
	 */
	public String getBenchmark() {
		return benchmark;
	}

	/**
	 * @param benchmark the benchmark to set
	 */
	public void setBenchmark(String benchmark) {
		this.benchmark = benchmark;
	}

	/**
	 * @return the book
	 */
	public String getBook() {
		return book;
	}

	/**
	 * @param book the book to set
	 */
	public void setBook(String book) {
		this.book = book;
	}

	/**
	 * @return the creationName
	 */
	public String getCreationName() {
		return creationName;
	}

	/**
	 * @param creationName the creationName to set
	 */
	public void setCreationName(String creationName) {
		this.creationName = creationName;
	}

	/**
	 * @return the creationDate
	 */
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * @return the revisionName
	 */
	public String getRevisionName() {
		return revisionName;
	}

	/**
	 * @param revisionName the revisionName to set
	 */
	public void setRevisionName(String revisionName) {
		this.revisionName = revisionName;
	}

	/**
	 * @return the revisionDate
	 */
	public Date getRevisionDate() {
		return revisionDate;
	}

	/**
	 * @param revisionDate the revisionDate to set
	 */
	public void setRevisionDate(Date revisionDate) {
		this.revisionDate = revisionDate;
	}

	/**
	 * @return the dealName
	 */
	public String getDealName() {
		return dealName;
	}

	/**
	 * @param dealName the dealName to set
	 */
	public void setDealName(String dealName) {
		this.dealName = dealName;
	}

	/**
	 * @return the dealType
	 */
	public String getDealType() {
		return dealType;
	}

	/**
	 * @param dealType the dealType to set
	 */
	public void setDealType(String dealType) {
		this.dealType = dealType;
	}

	/**
	 * @return the sourceListId
	 */
	public String getSourceListId() {
		return sourceListId;
	}

	/**
	 * @param sourceListId the sourceListId to set
	 */
	public void setSourceListId(String sourceListId) {
		this.sourceListId = sourceListId;
	}

	/**
	 * @return the side
	 */
	public String getSide() {
		return side;
	}

	/**
	 * @param side the side to set
	 */
	public void setSide(String side) {
		this.side = side;
	}

	@Override
	public String toString() {
		return "Trade [tradeId=" + tradeId + ", account=" + account + ", type=" + type + ", buyQuantity="
				+ buyQuantity + ", sellQuantity=" + sellQuantity + ", buyPrice=" + buyPrice + ", sellPrice="
				+ sellPrice + ", tradeDate=" + tradeDate + ", security=" + security + ", status=" + status
				+ ", trader=" + trader + ", benchmark=" + benchmark + ", book=" + book + ", creationName="
				+ creationName + ", creationDate=" + creationDate + ", revisionName=" + revisionName
				+ ", revisionDate=" + revisionDate + ", dealName=" + dealName + ", dealType=" + dealType
				+ ", sourceListId=" + sourceListId + ", side=" + side + "]";
	}
}
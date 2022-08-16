package com.nnk.springboot.domain;


import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;


@DynamicUpdate
@Entity
@Table(name = "bidlist")
public class BidList {


	//Attributes

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="bid_list_id")
	private int bidListId;

	@Column(name="account")
	private String account ;

	@Column(name="type")
	private String type ;

	@Column(name="bid_quantity")
	private double bidQuantity ;

	@Column(name="ask_quantity")
	private Double askQuantity ;

	@Column(name="bid")
	private Double bid ;

	@Column(name="ask")
	private Double ask ;

	@Column(name="benchmark")
	private String  benchmark ;

	@Column(name="bid_list_date")
	private Date bidListDate ;

	@Column(name="commentary")
	private String commentary ;

	@Column(name="security")
	private String security ;

	@Column(name="status")
	private String status ;

	@Column(name="trader")
	private String trader ;

	@Column(name="book")
	private String book ;

	@Column(name="creation_name")
	private String creationName ;

	@Column(name="creation_date")
	private Date creationDate ;

	@Column(name="revision_name")
	private String revisionName ;

	@Column(name="revision_date")
	private Date revisionDate ;

	@Column(name="deal_name")
	private String dealName ;

	@Column(name="deal_type")
	private String dealType ;

	@Column(name="source_list_id")
	private String sourceListId ;

	@Column(name="side")
	private String side ;

	
	/**
	 * Complete constructor
	 * @param bidListId
	 * @param account
	 * @param type
	 * @param bidQuantity
	 * @param askQuantity
	 * @param bid
	 * @param ask
	 * @param benchmark
	 * @param bidListDate
	 * @param commentary
	 * @param security
	 * @param status
	 * @param trader
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
	public BidList(int bidListId, String account, String type, double bidQuantity, double askQuantity, double bid,
			double ask, String benchmark, Date bidListDate, String commentary, String security, String status,
			String trader, String book, String creationName, Date creationDate, String revisionName, Date revisionDate,
			String dealName, String dealType, String sourceListId, String side) {
		super();
		this.bidListId = bidListId;
		this.account = account;
		this.type = type;
		this.bidQuantity = bidQuantity;
		this.askQuantity = askQuantity;
		this.bid = bid;
		this.ask = ask;
		this.benchmark = benchmark;
		this.bidListDate = bidListDate;
		this.commentary = commentary;
		this.security = security;
		this.status = status;
		this.trader = trader;
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
	public BidList() {
		super();
	}

	/**
	 * @return the bidListId
	 */
	public int getBidListId() {
		return bidListId;
	}

	/**
	 * @param bidListId the bidListId to set
	 */
	public void setBidListId(int bidListId) {
		this.bidListId = bidListId;
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
	 * @return the bidQuantity
	 */
	public double getBidQuantity() {
		return bidQuantity;
	}

	/**
	 * @param bidQuantity the bidQuantity to set
	 */
	public void setBidQuantity(double bidQuantity) {
		this.bidQuantity = bidQuantity;
	}

	/**
	 * @return the askQuantity
	 */
	public double getAskQuantity() {
		return askQuantity;
	}

	/**
	 * @param askQuantity the askQuantity to set
	 */
	public void setAskQuantity(double askQuantity) {
		this.askQuantity = askQuantity;
	}

	/**
	 * @return the bid
	 */
	public double getBid() {
		return bid;
	}

	/**
	 * @param bid the bid to set
	 */
	public void setBid(double bid) {
		this.bid = bid;
	}

	/**
	 * @return the ask
	 */
	public double getAsk() {
		return ask;
	}

	/**
	 * @param ask the ask to set
	 */
	public void setAsk(double ask) {
		this.ask = ask;
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
	 * @return the bidListDate
	 */
	public Date getBidListDate() {
		return bidListDate;
	}

	/**
	 * @param bidListDate the bidListDate to set
	 */
	public void setBidListDate(Date bidListDate) {
		this.bidListDate = bidListDate;
	}

	/**
	 * @return the commentary
	 */
	public String getCommentary() {
		return commentary;
	}

	/**
	 * @param commentary the commentary to set
	 */
	public void setCommentary(String commentary) {
		this.commentary = commentary;
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
		return "BidList [bidListId=" + bidListId + ", account=" + account + ", type=" + type + ", bidQuantity="
				+ bidQuantity + ", askQuantity=" + askQuantity + ", bid=" + bid + ", ask=" + ask + ", benchmark="
				+ benchmark + ", bidListDate=" + bidListDate + ", commentary=" + commentary + ", security=" + security
				+ ", status=" + status + ", trader=" + trader + ", book=" + book + ", creationName=" + creationName
				+ ", creationDate=" + creationDate + ", revisionName=" + revisionName + ", revisionDate=" + revisionDate
				+ ", dealName=" + dealName + ", dealType=" + dealType + ", sourceListId=" + sourceListId + ", side="
				+ side + "]";
	}
}
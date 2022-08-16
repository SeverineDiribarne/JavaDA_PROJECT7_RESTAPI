package com.nnk.springboot.domain;

import javax.persistence.*;

import java.util.Date;


@Entity
@Table(name = "curvepoint")
public class CurvePoint {

	//Attributes
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="Id")
	private int id;

	@Column(name="curve_id")
	private int curveId;

	@Column(name="as_of_date")
	private Date asOfDate;

	@Column(name="term")
	private double term ;

	@Column(name="value")
	private double value ;

	@Column(name="creation_date")
	private Date creationDate ;

	/**
	 * Complete constructor
	 * @param id
	 * @param curveId
	 * @param asOfDate
	 * @param term
	 * @param value
	 * @param creationDate
	 */
	public CurvePoint(int id, int curveId, Date asOfDate, double term, double value, Date creationDate) {
		super();
		this.id = id;
		this.curveId = curveId;
		this.asOfDate = asOfDate;
		this.term = term;
		this.value = value;
		this.creationDate = creationDate;
	}

	/**
	 *Empty constructor
	 */
	public CurvePoint() {
		super();
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the curveId
	 */
	public int getCurveId() {
		return curveId;
	}

	/**
	 * @param curveId the curveId to set
	 */
	public void setCurveId(int curveId) {
		this.curveId = curveId;
	}

	/**
	 * @return the asOfDate
	 */
	public Date getAsOfDate() {
		return asOfDate;
	}

	/**
	 * @param asOfDate the asOfDate to set
	 */
	public void setAsOfDate(Date asOfDate) {
		this.asOfDate = asOfDate;
	}

	/**
	 * @return the term
	 */
	public double getTerm() {
		return term;
	}

	/**
	 * @param term the term to set
	 */
	public void setTerm(double term) {
		this.term = term;
	}

	/**
	 * @return the value
	 */
	public double getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(double value) {
		this.value = value;
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

	@Override
	public String toString() {
		return "CurvePoint [id=" + id + ", CurveId=" + curveId + ", asOfDate=" + asOfDate + ", term=" + term
				+ ", value=" + value + ", creationDate=" + creationDate + "]";
	}
}
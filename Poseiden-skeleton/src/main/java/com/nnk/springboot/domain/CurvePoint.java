package com.nnk.springboot.domain;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Date;

//@NoArgsConstructor
//@AllArgsConstructor
//@ToString(of= {"id", "CurveId", "asOfDate", "term", "value", "creationDate"})
//@Getter @Setter
@Entity
@Table(name = "curvepoint")
public class CurvePoint {

	//Attributes
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int id;

	private int CurveId;

	private Date asOfDate;

	private double term ;

	private double value ;

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
		CurveId = curveId;
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
		return CurveId;
	}

	/**
	 * @param curveId the curveId to set
	 */
	public void setCurveId(int curveId) {
		CurveId = curveId;
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
		return "CurvePoint [id=" + id + ", CurveId=" + CurveId + ", asOfDate=" + asOfDate + ", term=" + term
				+ ", value=" + value + ", creationDate=" + creationDate + "]";
	}
}
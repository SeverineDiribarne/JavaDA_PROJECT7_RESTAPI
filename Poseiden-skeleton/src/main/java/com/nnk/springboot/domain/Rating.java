package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

//@NoArgsConstructor
//@AllArgsConstructor
//@ToString(of = {"id", "moodysRating", "sandPRating", "fitchRating", "orderNumber" })
//@Getter @Setter
@Entity
@Table(name = "rating")
public class Rating {

	//Attributes

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="Id")
	private int id ;

	@Column(name="moodys_rating")
	private String moodysRating ;

	@Column(name="sandprating")
	private String sandPRating ;

	@Column(name="fitch_rating")
	private String fitchRating ;

	@Column(name="order_number")
	private int orderNumber ;

	/**
	 * Complete constructor
	 * @param id
	 * @param moodysRating
	 * @param sandPRating
	 * @param fitchRating
	 * @param orderNumber
	 */
	public Rating(int id, String moodysRating, String sandPRating, String fitchRating, int orderNumber) {
		super();
		this.id = id;
		this.moodysRating = moodysRating;
		this.sandPRating = sandPRating;
		this.fitchRating = fitchRating;
		this.orderNumber = orderNumber;
	}

	/**
	 * Empty constructor
	 */
	public Rating() {
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
	 * @return the moodysRating
	 */
	public String getMoodysRating() {
		return moodysRating;
	}

	/**
	 * @param moodysRating the moodysRating to set
	 */
	public void setMoodysRating(String moodysRating) {
		this.moodysRating = moodysRating;
	}

	/**
	 * @return the sandPRating
	 */
	public String getSandPRating() {
		return sandPRating;
	}

	/**
	 * @param sandPRating the sandPRating to set
	 */
	public void setSandPRating(String sandPRating) {
		this.sandPRating = sandPRating;
	}

	/**
	 * @return the fitchRating
	 */
	public String getFitchRating() {
		return fitchRating;
	}

	/**
	 * @param fitchRating the fitchRating to set
	 */
	public void setFitchRating(String fitchRating) {
		this.fitchRating = fitchRating;
	}

	/**
	 * @return the orderNumber
	 */
	public int getOrderNumber() {
		return orderNumber;
	}

	/**
	 * @param orderNumber the orderNumber to set
	 */
	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}

	@Override
	public String toString() {
		return "Rating [id=" + id + ", moodysRating=" + moodysRating + ", sandPRating=" + sandPRating + ", fitchRating="
				+ fitchRating + ", orderNumber=" + orderNumber + "]";
	}
}
package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

//@NoArgsConstructor
//@AllArgsConstructor
//@ToString(of = {"id", "name", "description", "json", "template", "sqlStr", "sqlPart" })
//@Getter @Setter
@Entity
@Table(name = "rulename")
public class RuleName {

	//Attributes

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="Id")
	private int id;

	@Column(name="name")
	private String name;

	@Column(name="description")
	private String description;

	@Column(name="json")
	private String json;

	@Column(name="template")
	private String template;

	@Column(name="sql_str")
	private String sqlStr;

	@Column(name="sql_part")
	private String sqlPart;

	/**
	 * Complete constructor
	 * @param id
	 * @param name
	 * @param description
	 * @param json
	 * @param template
	 * @param sqlStr
	 * @param sqlPart
	 */
	public RuleName(int id, String name, String description, String json, String template, String sqlStr,
			String sqlPart) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.json = json;
		this.template = template;
		this.sqlStr = sqlStr;
		this.sqlPart = sqlPart;
	}

	/**
	 * Empty constructor
	 */
	public RuleName() {
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the json
	 */
	public String getJson() {
		return json;
	}

	/**
	 * @param json the json to set
	 */
	public void setJson(String json) {
		this.json = json;
	}

	/**
	 * @return the template
	 */
	public String getTemplate() {
		return template;
	}

	/**
	 * @param template the template to set
	 */
	public void setTemplate(String template) {
		this.template = template;
	}

	/**
	 * @return the sqlStr
	 */
	public String getSqlStr() {
		return sqlStr;
	}

	/**
	 * @param sqlStr the sqlStr to set
	 */
	public void setSqlStr(String sqlStr) {
		this.sqlStr = sqlStr;
	}

	/**
	 * @return the sqlPart
	 */
	public String getSqlPart() {
		return sqlPart;
	}

	/**
	 * @param sqlPart the sqlPart to set
	 */
	public void setSqlPart(String sqlPart) {
		this.sqlPart = sqlPart;
	}

	@Override
	public String toString() {
		return "RuleName [id=" + id + ", name=" + name + ", description=" + description + ", json=" + json
				+ ", template=" + template + ", sqlStr=" + sqlStr + ", sqlPart=" + sqlPart + "]";
	}
}
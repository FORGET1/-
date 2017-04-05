package com.pingdu.entity;

import java.io.Serializable;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="book")
public class Book implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7828330583061229497L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id" ,nullable=false)
	private Integer id;
	
	@Column(name="name",nullable=false)
	private String name;
	
	@Column(name="author",nullable=false)
	private String author;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
}

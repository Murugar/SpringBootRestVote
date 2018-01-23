package com.iqmsoft.rest.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Restaurant implements Comparable<Restaurant> {

  

    @Override
	public String toString() {
		return "Restaurant [id=" + id + ", name=" + name + ", type=" + type + ", picUrl=" + picUrl + ", owner=" + owner
				+ ", age=" + age + ", street1=" + street1 + ", street2=" + street2 + ", city=" + city + ", zipcode="
				+ zipcode + ", state=" + state + ", counter=" + counter + "]";
	}

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Length(min = 1, max = 50)
    private String name;
    
    @Length(min = 1, max = 30)
    private String type;
    
    @Length(min = 1, max = 100)
    private String picUrl;
    
    @Length(min = 1, max = 100)
    private String owner;
    
    private int age;
    
    private String street1;
    
    public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getStreet1() {
		return street1;
	}

	public void setStreet1(String street1) {
		this.street1 = street1;
	}

	public String getStreet2() {
		return street2;
	}

	public void setStreet2(String street2) {
		this.street2 = street2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setId(Long id) {
		this.id = id;
	}

	private String street2;
    
    private String city;
    
    private String zipcode;
    
    private String state;
    
    
  
    public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	private Long counter = 0L;

   
	public Long getCounter() {
		return counter;
	}

	public void setCounter(Long counter) {
		this.counter = counter;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Restaurant() {
    }

    public Restaurant(String name) {
        this.name = name;
    }
    
    public Restaurant(String name, String type) {
        this.name = name;
        this.type = type;
    }
    
    public Restaurant(String name, String type, String purl) {
        this.name = name;
        this.type = type;
        this.picUrl = purl;
    }

    public Restaurant(String name, String type, String purl, int age) {
        this.name = name;
        this.type = type;
        this.picUrl = purl;
        this.age = age;
    }
   

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
    @Override
	public int compareTo(Restaurant o) {
		
		return counter.compareTo(o.getCounter());
	}
    
    
}

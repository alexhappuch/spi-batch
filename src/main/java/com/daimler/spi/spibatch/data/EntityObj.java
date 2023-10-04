package com.daimler.spi.spibatch.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "entity_tbl", schema = "ap_2")
public class EntityObj {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String data;
    // getters, setters, etc.
    
    EntityObj(Long id, String data) {
		super();
		this.id = id;
		this.data = data;
	}
    
    EntityObj() {
	
	}
    
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "MyEntity [id=" + id + ", data=" + data + "]";
	}
	
    
	
    
}


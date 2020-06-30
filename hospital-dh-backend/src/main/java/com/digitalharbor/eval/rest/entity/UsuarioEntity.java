package com.digitalharbor.eval.rest.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.* ;

@Getter
@Setter
@Entity(name="usuario")
public class UsuarioEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="public_id", length=16)
	private String publicId ;
	
	@Column(name="username" , length=12, nullable = false)
	private String username ;

	@Column(name="password", length = 64, nullable =false)
	private String password ;
	
	@Column (name="fecha_creacion", nullable=false)
	private Date fechaCreacion ;
	
}

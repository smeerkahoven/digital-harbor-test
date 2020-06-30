package com.digitalharbor.eval.rest.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="hospital")
public class HospitalEntity {
	
	@Id
    @SequenceGenerator(name = "hospital_id_seq", sequenceName = "hospital_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hospital_id_seq")
	private Integer id ;
	
	@Column(name="nombre", length = 32)
	private String nombre ;
	
	@Column(name="direccion", length = 128)
	private String direccion ;
	
	@Column(name="fecha_creacion")
	private Date fechaCreacion ;
	
	@Column(name="fecha_actualizacion")
	private Date fechaActualizacion ;
	
	@ManyToOne
    @JoinColumn(name="creado_por")
	private UsuarioEntity creadoPor ;
	
	@ManyToOne
    @JoinColumn(name="actualizado_por")
	private UsuarioEntity actualizadoPor ;


}

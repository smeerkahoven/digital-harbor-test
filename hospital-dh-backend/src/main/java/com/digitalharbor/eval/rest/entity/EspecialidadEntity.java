package com.digitalharbor.eval.rest.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="especialidad")
public class EspecialidadEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @SequenceGenerator(name = "especialidad_id_seq", sequenceName = "especialidad_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "especialidad_id_seq")
	private Integer id ;
	
	@Column(name="nombre", length=32, nullable=false)
	private String nombre ;
	
	@Column(name="descripcion", length=128, nullable=false)
	private String descripcion ;
	
	@Lob
	@Column(nullable =true , name="avatar")
	private byte[] avatar ;
	
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
	
	@JsonIgnore
	@ManyToOne
    @JoinColumn(name="hospital_id")
	private HospitalEntity hospitalId;


	@JsonIgnore
	@ManyToMany
	Set<DoctorEntity> doctores ;
	
}

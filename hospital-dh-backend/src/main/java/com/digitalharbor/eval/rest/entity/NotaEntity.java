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
@Entity(name="nota")
public class NotaEntity {
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @SequenceGenerator(name = "nota_id_seq", sequenceName = "nota_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "nota_id_seq")
	private Integer id ;
	
	@Column(name="descripcion", length= 512, nullable=false)
	private String descripcion;
	
	@Column(name="fecha_atencion")
	private Date fechaAtencion;
	
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
	
	@ManyToOne
    @JoinColumn(name="paciente_id")
	private PacienteEntity pacienteId;
	
	@ManyToOne
    @JoinColumn(name="doctor_id")
	private DoctorEntity doctorId ;
}

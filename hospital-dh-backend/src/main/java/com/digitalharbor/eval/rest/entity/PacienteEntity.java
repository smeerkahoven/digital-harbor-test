package com.digitalharbor.eval.rest.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import lombok.*;

@Getter
@Setter
@Entity(name="paciente")
public class PacienteEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @SequenceGenerator(name = "paciente_id_seq", sequenceName = "paciente_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "paciente_id_seq")
	private Integer id ;
	
	@Column(name="nombre", length = 32)
	private String nombre ;
	
	@Column(name="apellido", length = 32)
	private String apellido ;
	
	@Column(name="direccion", length = 128)
	private String direccion ;
	
	@Column(name="fecha_nacimiento")
	private Date fechaNacimiento ;
	
	@Lob
	@Column(nullable =true , name="foto_perfil")
	private byte[] fotoPerfil ;
	
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
    @JoinColumn(name="hospital_id")
	private HospitalEntity hospitalId;

}

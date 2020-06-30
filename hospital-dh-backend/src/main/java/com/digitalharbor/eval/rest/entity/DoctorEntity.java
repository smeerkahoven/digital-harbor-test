package com.digitalharbor.eval.rest.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="doctor")
public class DoctorEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @SequenceGenerator(name = "doctor_id_seq", sequenceName = "doctor_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "doctor_id_seq")
	private Integer id ;
	
	@Column(name="nombre", length = 32)
	private String nombre ;
	
	@Column(name="apellido", length = 32)
	private String apellido ;
	
	@Column(name="direccion", length = 128)
	private String direccion ;
	
	@Column(name="fecha_nacimiento")
	private Date fechaNacimiento ;
	
	@Column(name="tipo")
	private String tipo;
	
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

	
	@ManyToMany
	@JoinTable(
			name="doctor_especialidad",
			joinColumns = @JoinColumn(name = "doctor_id"),
			inverseJoinColumns = @JoinColumn(name = "especialidad_id")
			)
	List<EspecialidadEntity> especialidades ;
	
}

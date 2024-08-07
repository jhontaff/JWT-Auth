package com.mywallet.user.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Entity
@Table(name="users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id", unique=true, nullable= false)
	private Long userId;
	
	@Column(name="username")
	@NotBlank(message="Usuario no válido: Campo vacío")
	@Size(min = 5, max = 15, message = "Usuario no válido: El usuario debe tener entre 5 y 15 caracteres")
	@Pattern(
			regexp="^[A-Za-z0-9]{6,15}$",
			message="Usuario no válido: El usuario no puede contener caracteres especiales")
	private String username;
	
	@Column(name="user_password", nullable=false)
	@Size(min = 8, max = 15, message = "Contraseña no válida: La contraseña debe tener entre 8 y 15 caracteres")
	@Pattern(
			regexp="^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[_.-])(?=\\S+$)[A-Za-z0-9_.-]+$", 
			message="La contraseña debe contener al menos 1 mayúscula, 1 minúscula, 1 caracter _.-, y 1 dígito")
	private String password;

	@Column(name="confirm_password", nullable=false)
	@Size(min = 8, max = 15, message = "Confirmación de contraseña no válida: La contraseña debe tener entre 8 y 15 caracteres")
	@Pattern(
			regexp="^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[_.-])(?=\\S+$)[A-Za-z0-9_.-]+$",
			message="La confirmación de contraseña debe contener al menos 1 mayúscula, 1 minúscula, 1 caracter _.-, y 1 dígito")
	private String confirmPassword;
	
	@Column(name="phone_number", nullable= false, length=13)
	@NotBlank(message="Teléfono no válido: Campo vacío")
	@Pattern(regexp="^\\+?[0-9]{10,13}$",
			message="Teléfono no valido: Caracteres no válidos")
	private String phone;
	
	@Column(name="email", unique = true)
	@Email(message="Email no válido")
	@NotBlank(message="Email no válido: Campo vacío")
	private String email;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "roles",
					joinColumns = @JoinColumn(name = "user_id"),
					inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();
	
	
	@Column(name="registration_date", nullable=false)
	private LocalDateTime registrationDate;
}

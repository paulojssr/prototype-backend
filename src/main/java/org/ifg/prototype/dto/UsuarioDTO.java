/**
 * 
 */
package org.ifg.prototype.dto;

import org.ifg.prototype.entities.Usuario;

/**
 * @author paulojorge
 *
 */
public class UsuarioDTO {

	private Long codigo;
	private String nome;
	private String email;
	private String senha;
	
	
	/**
	 * 
	 */
	public UsuarioDTO() {

	}
	
	public UsuarioDTO(Usuario usuario) {
		this.codigo = usuario.getCodigo();
		this.nome = usuario.getNome();
		this.email = usuario.getEmail();
		this.senha = usuario.getSenha();
	}
	/**
	 * @return the codigo
	 */
	public Long getCodigo() {
		return codigo;
	}
	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the senha
	 */
	public String getSenha() {
		return senha;
	}
	/**
	 * @param senha the senha to set
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
}

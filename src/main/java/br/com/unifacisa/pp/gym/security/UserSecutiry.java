package br.com.unifacisa.pp.gym.security;


import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.unifacisa.pp.gym.enumeration.TipoUsuario;
import lombok.Data;

@Data
public class UserSecutiry implements UserDetails {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String email;
	private String senha;
	private Set<? extends GrantedAuthority> authorities;

	public UserSecutiry() {
		
	}

	public UserSecutiry(Integer id, String email, String senha, Set<TipoUsuario> perfil) {
		this.id = id;
		this.email = email;
		this.senha = senha;
		this.authorities = perfil.stream().map(x -> new SimpleGrantedAuthority(x.getDescricao())).collect(Collectors.toSet());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public boolean hasRole(TipoUsuario user) {
		return getAuthorities().contains(new SimpleGrantedAuthority(user.getDescricao()));
	}

}

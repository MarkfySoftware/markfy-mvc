package com.markfy.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.markfy.dto.usuario.AlterarUsuarioDTO;
import com.markfy.dto.usuario.CadastroUsuarioDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Set;

@Table
@Entity
@Data
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;
    private String nomeUsuario;
    private String sobrenomeUsuario;
    private String emailUsuario;
    private String senha;
    @ManyToOne
    private Loja loja;
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> roles;

    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", nomeUsuario='" + nomeUsuario + '\'' +
                ", sobrenomeUsuario='" + sobrenomeUsuario + '\'' +
                ", emailUsuario='" + emailUsuario + '\'' +
                ", senha ='" + senha + '\'' +
                '}';
    }

    public Usuario(String nomeUsuario, String sobrenomeUsuario, String emailUsuario, String senha,  Set<String> roles, Loja loja) {
        this.nomeUsuario = nomeUsuario;
        this.sobrenomeUsuario = sobrenomeUsuario;
        this.emailUsuario = emailUsuario;
        this.senha = senha;
        this.roles = roles;
        this.loja = loja;
    }

    public Usuario(CadastroUsuarioDTO cadastroUsuarioDTO, Loja loja) {
        this.nomeUsuario = cadastroUsuarioDTO.nomeUsuario();
        this.sobrenomeUsuario = cadastroUsuarioDTO.sobrenomeUsuario();
        this.emailUsuario = cadastroUsuarioDTO.emailUsuario();
        this.senha = cadastroUsuarioDTO.senha();
        this.loja = loja;
    }

    public Usuario(String nomeUsuario, String sobrenomeUsuario, String emailUsuario, String senha, Loja lojaSave) {
        this.nomeUsuario = nomeUsuario;
        this.sobrenomeUsuario = sobrenomeUsuario;
        this.emailUsuario = emailUsuario;
        this.senha = senha;
        this.loja = lojaSave;
    }

    public void alterarUsuario(AlterarUsuarioDTO alterarUsuarioDTO) {
        if(alterarUsuarioDTO.nomeUsuario() != null) this.nomeUsuario = alterarUsuarioDTO.nomeUsuario();
        if(alterarUsuarioDTO.sobrenomeUsuario() != null) this.sobrenomeUsuario = alterarUsuarioDTO.sobrenomeUsuario();
        if(alterarUsuarioDTO.emailUsuario() != null) this.emailUsuario = alterarUsuarioDTO.emailUsuario();
        if(alterarUsuarioDTO.senha() != null) this.senha = alterarUsuarioDTO.senha();
    }
}

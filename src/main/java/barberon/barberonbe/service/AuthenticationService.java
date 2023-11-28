package barberon.barberonbe.service;


import barberon.barberonbe.model.Usuario;
import barberon.barberonbe.repository.BarbeiroRepository;
import barberon.barberonbe.repository.ClienteRepository;
import barberon.barberonbe.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final BarbeiroRepository barbeiroRepository;
    private final ClienteRepository clienteRepository;

    public AuthenticationService(BarbeiroRepository barbeiroRepository, ClienteRepository clienteRepository) {
        this.barbeiroRepository = barbeiroRepository;
        this.clienteRepository = clienteRepository;
    }

    public Usuario authenticate(String email, String senha, String userType) {
        Usuario usuario;

        if(userType.equals("BARBEIRO")) {
            usuario = barbeiroRepository.findByEmail(email);
        } else if(userType.equals("CLIENTE")) {
            usuario = clienteRepository.findByEmail(email);
        } else {
            throw new RuntimeException("Tipo de usuário inválido.");
        }

        if (usuario == null) {
            throw new RuntimeException("Email não encontrado.");
        }

        if (!usuario.getSenha().equals(senha)) {
            throw new RuntimeException("Senha incorreta.");
        }
        return usuario;
    }
}
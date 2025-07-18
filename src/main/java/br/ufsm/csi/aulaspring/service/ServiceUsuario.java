package br.ufsm.csi.aulaspring.service;


import br.ufsm.csi.aulaspring.exception.CriptoExistsException;
import br.ufsm.csi.aulaspring.exception.EmailExistsException;
import br.ufsm.csi.aulaspring.repository.UsuarioRepository;
import br.ufsm.csi.aulaspring.exception.ServiceExc;
import br.ufsm.csi.aulaspring.model.Usuario;
import br.ufsm.csi.aulaspring.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.security.NoSuchAlgorithmException;

@Service
public class ServiceUsuario {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void salvarUsuario(Usuario user) throws Exception {

        try {

            if(usuarioRepository.findByEmail(user.getEmail()) !=null) {
                throw new EmailExistsException("Este email já esta cadastrado: " + user.getEmail());
            }

            user.setSenha(Util.md5(user.getSenha()));

        } catch (NoSuchAlgorithmException e) {
            throw new CriptoExistsException("Error na criptografia da senha");
        }
        usuarioRepository.save(user);
    }

    public Usuario loginUser(String user, String senha) throws ServiceExc {

        return usuarioRepository.buscarLogin(user, senha);
    }
}

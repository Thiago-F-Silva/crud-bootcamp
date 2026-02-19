import java.util.ArrayList;
import java.util.List;

import com.crud.model.Usuario;
import com.crud.repository.UsuarioRepository;

public class UsuarioRepositoryTest implements UsuarioRepository {

    @Override
    public void salvar(Usuario usuario) {}

    @Override
    public List<Usuario> listarUsuarios() {
        return new ArrayList<>();
    }

    @Override
    public Usuario buscarPorId(Long id) {
        return null;
    }

    @Override
    public void atualizar(Usuario usuario) {}

    @Override
    public void deletar(Long id) {}
    
}

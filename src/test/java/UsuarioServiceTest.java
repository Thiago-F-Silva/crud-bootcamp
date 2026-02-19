import com.crud.model.Usuario;
import com.crud.repository.UsuarioRepository;
import com.crud.service.UsuarioService;

public class UsuarioServiceTest {
    
    public static void main(String[] args) {
        
        testCriarUsuarioComNomeValido();

        System.out.println("Todos os testes foram finalizados");

    }

    public static void testCriarUsuarioComNomeValido(){

        UsuarioRepository repositoryTest = new UsuarioRepositoryTest();
        UsuarioService service = new UsuarioService(repositoryTest);

        Usuario u = new Usuario();
        u.setNome("Thiago");

        try {
            service.salvarUsuario(u);
            System.out.println("teste para criar usuario deu certo");
        } catch (Exception e) {
            System.out.println("teste para criar usuário falhou");
        }

    }

}

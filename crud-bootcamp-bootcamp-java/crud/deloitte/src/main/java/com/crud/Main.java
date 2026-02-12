package com.crud;

import java.util.List;
import java.util.Scanner;

import com.crud.model.Usuario;
import com.crud.service.UsuarioService;


public class Main {

    private static final UsuarioService usuarioService = new UsuarioService();
    private static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
         
        boolean menu = true;

        while (menu) {
            mostrarMenu();
            String opcao = scan.nextLine();
            int input = Integer.parseInt(opcao);

            switch (input) {
                case 1:
                    salvarUsuario();
                    break;
                case 2: 
                    buscarPorId();
                    break;
                case 3:
                    atualizarUsuario();
                    break;
                case 4:
                    excluirUsuario();
                    break;
                case 5:
                    listarUsuarios();
                    break;
                case 0: 
                    menu = false;
                    break;
            
                default:
                    System.out.println("opção inválida");
                    break;
            }

        }
        
        
    }

    private static void mostrarMenu(){

        System.out.println("\n===== MENU =====");
        System.out.println("1 - Criar usuário");
        System.out.println("2 - Buscar usuário por ID");
        System.out.println("3 - Atualizar usuário");
        System.out.println("4 - Excluir usuário");
        System.out.println("5 - Listar usuários");
        System.out.println("0 - Sair");
        System.out.println("================\n");
        System.out.print("Escolha uma opção: ");

    }

    private static void salvarUsuario() {

        Usuario u = new Usuario();
        boolean valido = false;

        while (!valido) {
            System.out.println("Nome: ");
            String nome = scan.nextLine();
            u.setNome(nome);

            System.out.println("Email: ");
            String email = scan.nextLine();
            u.setEmail(email);

            usuarioService.salvarUsuario(u);
            valido = true;
        }

    }

    private static void buscarPorId() {

        System.out.println("Digite o ID do usuario: ");
        Long id = scan.nextLong();

        Usuario usuario = usuarioService.buscarPorId(id);
        System.out.println("Usuário encontrado: \n");
        System.out.printf(
            "ID: %d | Nome: %s | Email: %s",
            usuario.getId(), usuario.getNome(), usuario.getEmail()
        );

    }

    private static void atualizarUsuario() {
        System.out.println("Digite o ID do usuário a ser atualizado: ");
        String opcao = scan.nextLine();
        Long id = Long.parseLong(opcao);

        Usuario u = new Usuario();
        u.setId(id);

        boolean valido = false;

        while (!valido) {
            System.out.println("Digite o novo nome: ");
            String nome = scan.nextLine();
            u.setNome(nome);

            System.out.println("Digite o novo email: ");
            String email = scan.nextLine();
            u.setEmail(email);

            usuarioService.atualizarUsuario(u, id);
            valido = true;
        }

    }

    private static void excluirUsuario() {

        Usuario usuarioLogado = new Usuario();

        System.out.println("Digite seu nome: ");
        String nome = scan.nextLine();
        usuarioLogado.setNome(nome);

        System.out.println("Digite seu email: ");
        String email = scan.nextLine();
        usuarioLogado.setEmail(email);

        System.out.print("ID do usuário a excluir: ");
        Long id = Long.valueOf(scan.nextLine());

        usuarioService.excluirUsuario(id, usuarioLogado);
        System.out.println("\nUsuário excluído com sucesso!");
        
    }

    private static void listarUsuarios() {

    List<Usuario> usuarios = usuarioService.listarUsuarios();

    if (usuarios.isEmpty()) {
        System.out.println("\nNenhum usuário cadastrado.");
        return;
    }

    System.out.println("\n=== LISTA DE USUÁRIOS ===");

    for (Usuario u : usuarios) {
        System.out.printf(
            "ID: %d | Nome: %s | Email: %s",
            u.getId(), u.getNome(), u.getEmail()
        );
    }
}

}



package com.crud;

import java.util.List;
import java.util.Scanner;

import com.crud.controller.UsuarioController;
import com.crud.model.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main implements CommandLineRunner {

    private static final Scanner scan = new Scanner(System.in);

    @Autowired
    private UsuarioController controller;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    public void run(String... args) {

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
                    menuBuscarUsuario();
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

    private void mostrarMenu() {

        System.out.println("\n===== MENU =====");
        System.out.println("1 - Criar usuário");
        System.out.println("2 - Buscar usuário");
        System.out.println("3 - Atualizar usuário");
        System.out.println("4 - Excluir usuário");
        System.out.println("5 - Listar usuários");
        System.out.println("0 - Sair");
        System.out.println("================\n");
        System.out.print("Escolha uma opção: ");

    }

    private void menuBuscarUsuario() {

        System.out.println("\n===== MENU =====");
        System.out.println("1 - Buscar por ID");
        System.out.println("2 - Buscar por nome e email");
        System.out.println("================\n");
        System.out.print("Escolha uma opção: ");

        String opcao = scan.nextLine();
        int input = Integer.parseInt(opcao);

        switch (input) {
            case 1:
                buscarPorId();
                break;
            case 2:
                buscarPorNomeEmail();
                break;
            default:
                throw new AssertionError();
        }

    }

    private void salvarUsuario() {

        boolean valido = false;

        while (!valido) {
            System.out.println("Nome: ");
            String nome = scan.nextLine();

            System.out.println("Email: ");
            String email = scan.nextLine();

            controller.criarUsuario(nome, email);

            valido = true;
        }

    }

    private void buscarPorId() {

        System.out.println("Digite o ID do usuario: ");
        String idString = scan.nextLine();

        Usuario usuario = controller.buscarPorId(idString).orElseThrow(() -> new RuntimeException());

        System.out.println("Usuário encontrado: \n");
        System.out.printf(
                "ID: %d | Nome: %s | Email: %s\n",
                usuario.getId(), usuario.getNome(), usuario.getEmail());

    }

    private void buscarPorNomeEmail() {
        System.out.println("Digite o nome do usuario");
        String nome = scan.nextLine();

        System.out.println("Digite o email do usuario");
        String email = scan.nextLine();

        Usuario usuario = controller.buscarPorNomeEmail(nome, email);

        System.out.println("Usuário encontrado: \n");
        System.out.printf(
                "ID: %d | Nome: %s | Email: %s\n",
                usuario.getId(), usuario.getNome(), usuario.getEmail());

    }

    private void atualizarUsuario() {
        System.out.println("Digite o ID do usuário a ser atualizado: ");
        String id = scan.nextLine();

        Usuario usuarioValidado = controller.buscarPorId(id).orElseThrow(() -> new RuntimeException());

        boolean valido = false;

        while (!valido) {
            System.out.println("Digite o novo nome: ");
            String nome = scan.nextLine();

            System.out.println("Digite o novo email: ");
            String email = scan.nextLine();

            controller.atualizarUsuario(nome, email, usuarioValidado.getId());

            valido = true;
        }

    }

    private void excluirUsuario() {

        System.out.println("Digite seu nome: ");
        String nome = scan.nextLine();

        System.out.println("Digite seu email: ");
        String email = scan.nextLine();

        controller.autenticacao(nome, email);

        System.out.println("ID do usuário a excluir: ");
        String id = scan.nextLine();

        controller.excluirUsuario(id);
        System.out.println("\nUsuário excluído com sucesso!");

    }

    private void listarUsuarios() {

        List<Usuario> lista = controller.listarUsuarios();

        if (lista.isEmpty()) {
            System.out.println("\nNenhum usuário cadastrado.");
            return;
        }

        System.out.println("\n=== LISTA DE USUÁRIOS ===");

        for (Usuario u : lista) {
            System.out.printf(
                    "\nID: %d | Nome: %s | Email: %s\n",
                    u.getId(), u.getNome(), u.getEmail());
        }
    }

}

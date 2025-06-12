import java.util.Scanner;
import java.util.List;

public class Main {
    private static Database db;
    private static UsuarioRepository repo;
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        iniciarSistema();
        
        int opcao;
        do {
            exibirMenu();
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer
            
            switch(opcao) {
                case 1: criarLocatario(); break;
                case 2: listarTodos(); break;
                case 3: buscarPorId(); break;
                case 4: atualizarUsuario(); break;
                case 5: deletarUsuario(); break;
                case 6: System.out.println("Saindo do sistema..."); break;
                default: System.out.println("Opção inválida!");
            }
        } while(opcao != 6);
        
        encerrarSistema();
    }
    
    private static void iniciarSistema() {
        System.out.println("=== INICIANDO SISTEMA DE ALUGUEL DE QUADRAS ===");
        db = new Database();
        repo = new UsuarioRepository(db);
        System.out.println("Sistema pronto para uso!");
    }
    
    private static void encerrarSistema() {
        db.close();
        scanner.close();
        System.out.println("Sistema encerrado!");
    }
    
    private static void exibirMenu() {
        System.out.println("\n==== MENU PRINCIPAL ====");
        System.out.println("1. Cadastrar novo locatário");
        System.out.println("2. Listar todos os usuários");
        System.out.println("3. Buscar usuário por ID");
        System.out.println("4. Atualizar usuário");
        System.out.println("5. Excluir usuário");
        System.out.println("6. Sair");
        System.out.print("Escolha uma opção: ");
    }
    
    private static void criarLocatario() {
        System.out.println("\n--- NOVO LOCATÁRIO ---");
        
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        
        System.out.print("Email: ");
        String email = scanner.nextLine();
        
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        
        Locatario novoLocatario = new Locatario(nome, email, senha, cpf);
        repo.criar(novoLocatario);
    }
    
    private static void listarTodos() {
        System.out.println("\n--- LISTA DE USUÁRIOS ---");
        repo.listarTodos();
    }
    
    private static void buscarPorId() {
        System.out.print("\nDigite o ID do usuário: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer
        
        Usuario usuario = repo.buscar(id);
        if(usuario != null) {
            System.out.println("\nUSUÁRIO ENCONTRADO:");
            System.out.println("ID: " + usuario.getId());
            System.out.println("Nome: " + usuario.getNome());
            System.out.println("Email: " + usuario.getEmail());
            System.out.println("CPF: " + usuario.getCpf());
        }
    }
    
    private static void atualizarUsuario() {
        System.out.print("\nDigite o ID do usuário a ser atualizado: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer
        
        Usuario usuario = repo.buscar(id);
        if(usuario == null) {
            System.out.println("Usuário não encontrado!");
            return;
        }
        
        System.out.println("\nDados atuais:");
        System.out.println("Nome: " + usuario.getNome());
        System.out.println("Email: " + usuario.getEmail());
        System.out.println("CPF: " + usuario.getCpf());
        
        System.out.println("\nNovos dados:");
        System.out.print("Novo nome (deixe em branco para manter): ");
        String novoNome = scanner.nextLine();
        
        System.out.print("Novo email (deixe em branco para manter): ");
        String novoEmail = scanner.nextLine();
        
        System.out.print("Novo CPF (deixe em branco para manter): ");
        String novoCpf = scanner.nextLine();
        
        if(!novoNome.isEmpty()) usuario.setNome(novoNome);
        if(!novoEmail.isEmpty()) usuario.setEmail(novoEmail);
        if(!novoCpf.isEmpty()) usuario.setCpf(novoCpf);
        
        repo.atualizar(usuario);
    }
    
    private static void deletarUsuario() {
        System.out.print("\nDigite o ID do usuário a ser excluído: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer
        
        System.out.print("Tem certeza que deseja excluir? (S/N): ");
        String confirmacao = scanner.nextLine();
        
        if(confirmacao.equalsIgnoreCase("S")) {
            repo.deletar(id);
        } else {
            System.out.println("Operação cancelada!");
        }
    }
}
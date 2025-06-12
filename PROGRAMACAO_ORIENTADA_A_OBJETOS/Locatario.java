public class Locatario extends Usuario {
    public Locatario() {
        super();
        System.out.println("Tipo: Locatário");
    }
    
    public Locatario(String nome, String email, String senha, String cpf) {
        super(nome, email, senha, cpf);
        System.out.println("Tipo: Locatário");
    }
}
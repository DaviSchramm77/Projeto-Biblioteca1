import java.util.ArrayList;

public class Biblioteca {

    private ArrayList<Livro> livros;
    private ArrayList<Usuario> usuarios;
    private ArrayList<Emprestimo> emprestimos;

    public Biblioteca() {
        livros = new ArrayList<>();
        usuarios = new ArrayList<>();
        emprestimos = new ArrayList<>();
    }

    public void adicionarLivro(Livro livro) {
        livros.add(livro);
    }

    public void adicionarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public ArrayList<Livro> getLivros() {
        return livros;
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public ArrayList<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    public Livro buscarPorTitulo(String titulo) {
        for (Livro livro : livros) {
            if (livro.getTitulo().equalsIgnoreCase(titulo)) {
                return livro;
            }
        }
        return null;
    }

    public void removerLivro(int codigo) {
        livros.removeIf(l -> l.getCodigo() == codigo);
    }

    public boolean emprestarLivro(String titulo, int idUsuario) {

        Livro livro = buscarPorTitulo(titulo);

        if (livro == null || livro.isEmprestado()) {
            return false;
        }

        for (Usuario u : usuarios) {
            if (u.getId() == idUsuario) {
                livro.setEmprestado(true);
                emprestimos.add(new Emprestimo(livro, u));
                return true;
            }
        }

        return false;
    }

    public boolean devolverLivro(String titulo) {

        for (Emprestimo e : emprestimos) {

            if (e.getLivro().getTitulo().equalsIgnoreCase(titulo)) {

                e.getLivro().setEmprestado(false);
                emprestimos.remove(e);
                return true;
            }
        }

        return false;
    }
}

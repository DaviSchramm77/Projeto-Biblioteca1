import javax.swing.*;
import java.awt.*;

public class TelaBiblioteca extends JFrame {

    private Biblioteca biblioteca;

    private JTextField txtCodigo;
    private JTextField txtTitulo;
    private JTextField txtAutor;
    private JTextField txtAno;

    private JTextField txtPesquisa;

    private JLabel lblTotal;

    private JTextArea area;

    public TelaBiblioteca() {

        biblioteca = new Biblioteca();

        setTitle("Biblioteca");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel painel = new JPanel(new GridLayout(8,2,5,5));

        txtCodigo = new JTextField();
        txtTitulo = new JTextField();
        txtAutor = new JTextField();
        txtAno = new JTextField();
        txtPesquisa = new JTextField();

        painel.add(new JLabel("Codigo"));
        painel.add(txtCodigo);

        painel.add(new JLabel("Titulo"));
        painel.add(txtTitulo);

        painel.add(new JLabel("Autor"));
        painel.add(txtAutor);

        painel.add(new JLabel("Ano"));
        painel.add(txtAno);

        painel.add(new JLabel("Pesquisar titulo"));
        painel.add(txtPesquisa);

        JButton btnCadastrar = new JButton("Cadastrar");
        JButton btnListar = new JButton("Listar");
        JButton btnPesquisar = new JButton("Pesquisar");
        JButton btnRemover = new JButton("Remover");
        JButton btnLimpar = new JButton("Limpar");

        painel.add(btnCadastrar);
        painel.add(btnListar);
        painel.add(btnPesquisar);
        painel.add(btnRemover);
        painel.add(btnLimpar);

        lblTotal = new JLabel("Total de livros: 0");

        area = new JTextArea();

        add(painel, BorderLayout.NORTH);
        add(new JScrollPane(area), BorderLayout.CENTER);
        add(lblTotal, BorderLayout.SOUTH);

        biblioteca.adicionarUsuario(new Usuario(1, "Aluno"));

        btnCadastrar.addActionListener(e -> cadastrarLivro());
        btnListar.addActionListener(e -> listarLivros());
        btnPesquisar.addActionListener(e -> pesquisarLivro());
        btnRemover.addActionListener(e -> removerLivro());
        btnLimpar.addActionListener(e -> limparCampos());
    }

    private void cadastrarLivro() {

        try {

            int codigo = Integer.parseInt(txtCodigo.getText());
            String titulo = txtTitulo.getText();
            String autor = txtAutor.getText();
            int ano = Integer.parseInt(txtAno.getText());

            Livro livro = new Livro(codigo, titulo, autor, ano);

            biblioteca.adicionarLivro(livro);

            lblTotal.setText("Total de livros: " + biblioteca.getLivros().size());

            JOptionPane.showMessageDialog(this, "Livro cadastrado!");

            limparCampos();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Dados invalidos");
        }
    }

    private void listarLivros() {

        area.setText("");

        for (Livro livro : biblioteca.getLivros()) {
            area.append(livro + "\n");
        }
    }

    private void pesquisarLivro() {

        Livro livro = biblioteca.buscarPorTitulo(txtPesquisa.getText());

        if (livro != null) {
            area.setText(livro.toString());
        } else {
            area.setText("Livro nao encontrado");
        }
    }

    private void removerLivro() {

        try {

            int codigo = Integer.parseInt(txtCodigo.getText());

            biblioteca.removerLivro(codigo);

            lblTotal.setText("Total de livros: " + biblioteca.getLivros().size());

            JOptionPane.showMessageDialog(this, "Livro removido");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Informe o codigo");
        }
    }

    private void limparCampos() {

        txtCodigo.setText("");
        txtTitulo.setText("");
        txtAutor.setText("");
        txtAno.setText("");
        txtPesquisa.setText("");
    }
}

package biblioteca;

import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private List<Livro> livros;

    public Biblioteca() {
        this.livros = new ArrayList<>();
    }

    public void adicionar(Livro livro) throws Exception {
        if (livro == null) {
            throw new Exception("Livro não pode ser nulo.");
        }
        livros.add(livro);
    }

    public void remover(String titulo) throws Exception {
        Livro livro = pesquisarPorTitulo(titulo);
        if (livro == null) {
            throw new Exception("Livro não encontrado.");
        }
        livros.remove(livro);
    }

    public Livro pesquisarPorTitulo(String titulo) {
        for (Livro livro : livros) {
            if (livro.getTitulo().equalsIgnoreCase(titulo)) {
                return livro;
            }
        }
        return null;
    }

    public List<Livro> pesquisarTodos() {
        return new ArrayList<>(livros);
    }
}

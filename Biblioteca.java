package biblioteca;

import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    // BD em memória
    private List<Livro> acervo = new ArrayList<>();

    public void adicionar(Livro livro) throws Exception {
        if (livro.getTitulo() == null || livro.getTitulo().isEmpty())
            throw new Exception("Não é permitido cadastrar livro sem título");
        if (livro.getAutor() == null || livro.getAutor().isEmpty())
            throw new Exception("Não é permitido cadastrar livro sem autor");
        if (livro.getAnoPublicacao() < 1400 || livro.getAnoPublicacao() > java.time.LocalDate.now().getYear())
            throw new Exception("Ano de publicação deve ser entre 1400 e " + java.time.LocalDate.now().getYear());
        if (livro.getnPaginas() <= 0)
            throw new Exception("Número de páginas deve ser maior que zero");

        for (Livro livroAcervo : acervo) {
            if (livroAcervo.getTitulo().equalsIgnoreCase(livro.getTitulo()))
                throw new Exception("Já existe livro cadastrado com este título");
        }
        acervo.add(livro);
    }

    public List<Livro> pesquisarPorTitulo(String titulo) {
        List<Livro> livrosEncontrados = new ArrayList<>();

        for (Livro livro : acervo) {
            if (livro.getTitulo().toLowerCase().contains(titulo.toLowerCase())) {
                livrosEncontrados.add(livro);
            }
        }

        return livrosEncontrados;
    }

    public void removerPorTitulo(String titulo) throws Exception {
        boolean livroRemovido = false;
        for (int i = 0; i < acervo.size(); i++) {
            if (acervo.get(i).getTitulo().equalsIgnoreCase(titulo)) {
                acervo.remove(i);
                livroRemovido = true;
                break;
            }
        }
        if (!livroRemovido) {
            throw new Exception("Livro não encontrado para remoção.");
        }
    }

    public List<Livro> pesquisarTodos() {
        return new ArrayList<>(acervo); // Retorna uma cópia da lista de livros
    }
}
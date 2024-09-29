package biblioteca;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Biblioteca biblio = new Biblioteca();
    static Scanner input = new Scanner(System.in);

    private static int inputNumerico(String mensagem) {
        int valor = 0;
        boolean entradaValida = false;
        System.out.println(mensagem);
        do {
            String valorStr = input.nextLine();
            try {
                valor = Integer.parseInt(valorStr);
                entradaValida = true;
            } catch (Exception e) {
                System.out.println("Erro. Por favor informe um número inteiro.");
            }
        } while (!entradaValida);
        return valor;
    }

    private static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static void listar() {
        List<Livro> livros = biblio.pesquisarTodos();

        if (livros.isEmpty()) {
            System.out.println("Nenhum livro encontrado.");
            return;
        }

        livros.sort(Comparator.comparing(Livro::getTitulo));
        System.out.println("======== LISTA DE LIVROS =========");
        for (Livro livro : livros) {
            System.out.println("Título: " + livro.getTitulo());
            System.out.println("Autor: " + livro.getAutor());
            System.out.println("Ano de Publicação: " + livro.getAnoPublicacao());
            System.out.println("Número de Páginas: " + livro.getnPaginas());
            System.out.println("-----------------------------------");
        }
    }

    private static void adicionar() {
        Livro novoLivro = new Livro();
        System.out.println("======== ADICIONANDO NOVO LIVRO ========");

        String titulo;
        do {
            System.out.print("Informe o título do livro: ");
            titulo = input.nextLine();
        } while (titulo.isEmpty());
        novoLivro.setTitulo(titulo);

        String autor;
        do {
            System.out.print("Informe o nome do autor: ");
            autor = input.nextLine();
        } while (autor.isEmpty());
        novoLivro.setAutor(autor);

        int anoPublicacao;
        int anoAtual = LocalDate.now().getYear();
        do {
            System.out.print("Informe o ano de publicação (1400 a " + anoAtual + "): ");
            anoPublicacao = inputNumerico("");
        } while (anoPublicacao < 1400 || anoPublicacao > anoAtual);
        novoLivro.setAnoPublicacao(anoPublicacao);

        int nPaginas;
        do {
            System.out.print("Informe o número de páginas (positivo): ");
            nPaginas = inputNumerico("");
        } while (nPaginas <= 0);
        novoLivro.setnPaginas(nPaginas);

        try {
            biblio.adicionar(novoLivro);
            System.out.println("Livro adicionado com sucesso!");
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
        }

        System.out.println("Pressione ENTER para continuar...");
        input.nextLine();
    }

    private static void pesquisar() {
        System.out.print("Informe o título do livro a ser pesquisado: ");
        String titulo = input.nextLine();
        List<Livro> livros = biblio.pesquisarPorTitulo(titulo);

        if (!livros.isEmpty()) {
            System.out.println("Livros encontrados:");
            for (Livro livro : livros) {
                System.out.println("Título: " + livro.getTitulo());
                System.out.println("Autor: " + livro.getAutor());
                System.out.println("Ano de Publicação: " + livro.getAnoPublicacao());
                System.out.println("Número de Páginas: " + livro.getnPaginas());
                System.out.println("-----------------------------------");
            }
        } else {
            System.out.println("Nenhum livro encontrado com esse título.");
        }

        System.out.println("Pressione ENTER para continuar...");
        input.nextLine();
    }

    private static void remover() {
        System.out.print("Informe o título do livro a ser removido: ");
        String titulo = input.nextLine();

        try {
            biblio.removerPorTitulo(titulo);
            System.out.println("Livro removido com sucesso!");
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
        }

        System.out.println("Pressione ENTER para continuar...");
        input.nextLine();
    }

    public static void main(String[] args) {
        String menu = "SISTEMA DE GERENCIAMENTO DE BIBLIOTECA\n"
        + "Escolha uma das opções:\n"
        + "1 - Adicionar novo livro;\n"
        + "2 - Listar todos os livros;\n"
        + "3 - Pesquisar livro;\n"
        + "4 - Remover livro;\n"
        + "0 - Sair;\n";
        int opcao;
        do {
            limparTela();
            opcao = inputNumerico(menu);
            switch (opcao) {
                case 0:
                    System.out.println("VOLTE SEMPRE!!!");
                    break;
                case 1:
                    adicionar();
                    break;
                case 2:
                    listar();
                    break;
                case 3:
                    pesquisar();
                    break;
                case 4:
                    remover();
                    break;
                default:
                    System.out.println("Opção inválida!!!");
                    break;
            }
        } while (opcao != 0);
    }
}
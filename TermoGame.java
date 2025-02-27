import java.util.Scanner; // Importa a classe Scanner para entrada do usuário
import java.util.Random; // Importa a classe Random para gerar palavras aleatórias

public class TermoGame { // Classe principal do jogo

    // Constantes do jogo
    private static final int TAMANHO_PALAVRA = 5; // Define o tamanho da palavra secreta
    private static final int MAX_TENTATIVAS = 6; // Número máximo de tentativas permitidas
    private static final char CORRETO = '✔'; // Indica uma letra na posição correta
    private static final char PRESENTE = '✖'; // Indica uma letra presente na palavra, mas na posição errada
    private static final char AUSENTE = '_'; // Indica uma letra ausente na palavra secreta

    private String palavraSecreta; // Palavra secreta que o jogador precisa adivinhar
    private Scanner scanner = new Scanner(System.in); // Scanner para entrada do jogador

    // Método público que inicia o jogo
    // Métodos public podem ser acessados por outras classes
    public void iniciarJogo() {
        selecionarPalavraSecreta(); // Chama o método para escolher a palavra secreta
        System.out.println("Bem-vindo ao Termo! Descubra a palavra secreta de 5 letras.");

        for (int tentativa = 1; tentativa <= MAX_TENTATIVAS; tentativa++) { // Loop para cada tentativa do jogador
            System.out.print("Tentativa " + tentativa + "/" + MAX_TENTATIVAS + ": Digite sua palavra: ");
            String palavra = scanner.next().toUpperCase(); // Lê a palavra do jogador e converte para maiúsculas

            if (palavra.length() != TAMANHO_PALAVRA) { // Verifica se a palavra tem o tamanho correto
                System.out.println("A palavra deve ter exatamente " + TAMANHO_PALAVRA + " letras.");
                tentativa--; // Não conta a tentativa inválida
                continue;
            }

            if (palavra.equals(palavraSecreta)) { // Verifica se a palavra do jogador está correta
                System.out.println("Parabéns! Você acertou a palavra secreta: " + palavraSecreta);
                return; // Encerra o jogo
            }

            exibirFeedback(palavra); // Exibe o feedback sobre a tentativa do jogador
        }

        System.out.println("Você perdeu! A palavra secreta era: " + palavraSecreta); // Mensagem ao perder o jogo
    }

    // Método privado que seleciona uma palavra aleatória
    // Métodos private só podem ser acessados dentro da própria classe
    private void selecionarPalavraSecreta() {
        String[] palavras = {"CASAS", "VIDRO", "TORRE", "LIVRO", "CAMPO", "PRAIA", "GRAMA", "NOBRE", "TESLA", "SONAR"}; // Lista de palavras possíveis
        Random random = new Random(); // Cria um objeto Random para gerar números aleatórios
        palavraSecreta = palavras[random.nextInt(palavras.length)]; // Seleciona uma palavra aleatória da lista
    }

    // Método privado que exibe o feedback sobre a tentativa do jogador
    private void exibirFeedback(String palavra) {
        char[] feedback = new char[TAMANHO_PALAVRA]; // Array para armazenar o feedback
        boolean[] marcados = new boolean[TAMANHO_PALAVRA]; // Array para marcar letras já processadas

        // Verificar letras corretas
        for (int i = 0; i < TAMANHO_PALAVRA; i++) {
            if (palavra.charAt(i) == palavraSecreta.charAt(i)) { // Verifica se a letra está na posição correta
                feedback[i] = CORRETO;
                marcados[i] = true;
            } else {
                feedback[i] = AUSENTE; // Inicialmente marca como ausente
            }
        }

        // Verificar letras presentes
        for (int i = 0; i < TAMANHO_PALAVRA; i++) {
            if (feedback[i] == CORRETO) continue; // Ignora letras já marcadas como corretas

            for (int j = 0; j < TAMANHO_PALAVRA; j++) {
                if (!marcados[j] && palavra.charAt(i) == palavraSecreta.charAt(j)) { // Verifica se a letra está presente em outra posição
                    feedback[i] = PRESENTE;
                    marcados[j] = true;
                    break;
                }
            }
        }

        System.out.print("Feedback: ");
        for (char c : feedback) { // Imprime o feedback
            System.out.print(c + " ");
        }
        System.out.println();
    }

    // Método principal que inicia o jogo quando o programa é executado
    public static void main(String[] args) {
        TermoGame jogo = new TermoGame(); // Cria um novo objeto do jogo
        jogo.iniciarJogo(); // Chama o método para iniciar o jogo
    }
}
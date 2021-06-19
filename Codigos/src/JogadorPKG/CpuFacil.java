package JogadorPKG;

import Regras.*;
import Embarcacoes.*;

import java.util.Random;
import javax.naming.directory.InvalidAttributesException;

/**
 * Regras da CPU Fácil: 1) A CPU insere as peças aleatoriamente, sem critério.
 * 2) A CPU ataca aleatoriamente, sem critério. 3) A CPU não se preocupa em
 * deixar a embarcação na vertical ou horizontal (insere na padrão)....
 */
public class CpuFacil implements IJogador {
    private final Tabuleiro meuTabuleiro = new Tabuleiro();

    public CpuFacil() {
        for (Embarcacao embarcacao : meuTabuleiro.getMinhaEsquadra()) {
            boolean result;
            do {
                int linha = this.randomRow();
                int coluna = this.randomCol();
                result = this.inserirEmbarcacao(embarcacao, linha, coluna);
            } while (!result);
        }
    }

    /**
     * Método para gerar um index de linha aleatório.
     * @return (int) Index gerado para linha.
     */
    private int randomRow() {
        Random random = new Random();
        return random.nextInt(this.meuTabuleiro.getMaxLinhas() + 1);
    }

    /**
     * Método para gerar um index de coluna aleatório.
     * @return (int) Index gerado para coluna.
     */
    private int randomCol() {
        Random random = new Random();
        return random.nextInt(this.meuTabuleiro.getMaxColunas() + 1);
    }

    public Tabuleiro getMeuTabuleiro() {
        return meuTabuleiro;
    }

    public void inverterOrientacao(Embarcacao embarcacao) {
        embarcacao.inverteOrientacao();
    }

    @Override
    public void setTabuleiro(Tabuleiro meuTabuleiro) {

    }

    @Override
    public boolean inserirEmbarcacao(Embarcacao qual, int coluna, int linha) {
        return meuTabuleiro.inserirEmbarcacao(qual, linha, coluna);
    }

    @Override
    public boolean bombardear(Jogador inimigo, int linha, int coluna) throws InvalidAttributesException {
        boolean jaBombardeada;
        do {
            linha = randomRow();
            coluna = randomCol();
            Casa casa = meuTabuleiro.getCasa(linha, coluna);
            jaBombardeada = casa.foiBombardeada();
        } while (jaBombardeada);

        Tabuleiro tabuleiroInimigo = inimigo.getMeuTabuleiro();
        return tabuleiroInimigo.bombardear(linha, coluna);
    }
}
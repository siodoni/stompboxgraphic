package br.com.siodoni.stompboxgraphic.main;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

/**
 * Classe responsável por gerar o arquivo de log.
 */
public final class Log {

    private static File arquivo;
    private static FileWriter escrever;
    private static PrintWriter saida;
    private static String nomeArquivo = "";
    private static String nomeArquivoErro = "";

    /**
     * Método responsável por abrir o arquivo de log.
     * @param nomeArquivo Informe o nome do arquivo.
     * @param erro Indique se o log será de erro.
     * @see #getNomeArquivo()
     * @see #getNomeArquivoErro()
     */
    public static void abrirArquivo(String nomeArquivo, boolean erro) {
        try {
            arquivo = new File(nomeArquivo);
            if (!erro) {
                setNomeArquivo(nomeArquivo);
            } else {
                setNomeArquivoErro(nomeArquivo);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro na abertura do arquivo " + nomeArquivo + ": " + e.toString(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Método responsável por gerar a linha do log.
     * @param nomeArquivo Informe o nome do arquivo.
     * @param linha Informe a mensagem que deseja gerar no log.
     * @see #getNomeArquivo()
     * @see #getNomeArquivoErro()
     */
    public static void insereLinha(String nomeArquivo, String linha) {
        try {
            escrever = new FileWriter(nomeArquivo, true);
            saida = new PrintWriter(escrever);
            saida.println(linha);
            fechaArquivo();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro na inserção da linha do arquivo: " + e.toString(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void fechaArquivo() {
        try {
            saida.close();
            escrever.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro no fechamento do arquivo: " + e.toString(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Retorna o nome do arquivo.
     * @return String do nome do arquivo.
     */
    public static String getNomeArquivo() {
        return Log.nomeArquivo;
    }

    /**
     * Método que atribui o nome do arquivo.
     * @param nomeArquivo Informe o nome do arquivo.
     */
    public static void setNomeArquivo(String nomeArquivo) {
        Log.nomeArquivo = nomeArquivo;
    }

    /**
     * Retorna o nome do arquivo de erro.
     * @return String do nome do arquivo de erro.
     */
    public static String getNomeArquivoErro() {
        return Log.nomeArquivoErro;
    }

    /**
     * Método que atribui o nome do arquivo de erro.
     * @param nomeArquivoErro Informe o nome do arquivo de erro.
     */
    public static void setNomeArquivoErro(String nomeArquivoErro) {
        Log.nomeArquivoErro = nomeArquivoErro;
    }
}

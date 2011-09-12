package br.com.siodoni.stompboxgraphic.main;

import java.io.IOException;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.util.Date;
import javax.imageio.ImageIO;

public class Gerador {

    private String parametro[];
    private int valor[];
    private String nomeArquivo, localArquivo, knob;
    private int tpKnob;
    private StringBuffer log = new StringBuffer();

    public Gerador(String parametro[], int valor[], String nomeArquivo, String localArquivo, int knob) {
        this.parametro = parametro;
        this.valor = valor;
        this.nomeArquivo = nomeArquivo;
        this.localArquivo = localArquivo;
        this.tpKnob = knob;

        if (tpKnob == 0) {
            this.knob = "normal";
        } else if (tpKnob == 1) {
            this.knob = "jazzbass";
        } else if (tpKnob == 2) {
            this.knob = "boss";
        } else if (tpKnob == 3) {
            this.knob = "ch";
        } else if (tpKnob == 4) {
            this.knob = "lin";
        } else if (tpKnob == 5) {
            this.knob = "boutique";
        }
    }

    public ImageIcon gerar() {
        int quantidade = 0;

        for (int i = 0; i < parametro.length; i++) {
            if (parametro[i].length() > 0) {
                quantidade++;
            }
        }

        int width = (quantidade * 70) + 10, height = 100, x = 5, tamKnob = 70;
        Image img;
        Font font;
        BufferedImage buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = buffer.createGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);
        g.setColor(Color.WHITE);
        font = new Font("Arial", Font.BOLD, 12);
        g.setFont(font);

        tamKnob = 10;

        for (int i = 0; i < parametro.length; i++) {
            if (parametro[i].length() > 0) {
                g.drawString(parametro[i], tamKnob, 20);

                try {
                    img = ImageIO.read(getClass().getClassLoader().getResource("br/com/siodoni/stompboxgraphic/img/" + valor[i] + this.knob + ".png"));
                    g.drawImage(img, tamKnob, 30, null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                tamKnob += 70;
            }
        }

        //Gera a imagem no Sistema Operacional.
        try {
            ImageIO.write(buffer, "PNG", new File(localArquivo + nomeArquivo + ".png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        //Gera o LOG.
        for (int i = 0; i < parametro.length; i++) {
            if (parametro[i].length() > 0) {
                log.append(parametro[i]);
                log.append("|");
                log.append(valor[i]);
                log.append("|");
            }
        }

        long lDateTime = new Date().getTime();

        Log.setNomeArquivo("stomp_box.log");
        Log.abrirArquivo(Log.getNomeArquivo(), true);
        Log.insereLinha(Log.getNomeArquivo(),
                lDateTime + "|"
                + this.nomeArquivo + "|"
                + this.localArquivo + "|"
                + this.tpKnob + "|"
                + this.log.toString());
        return new ImageIcon(buffer);
    }
}

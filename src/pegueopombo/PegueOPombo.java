/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pegueopombo;

import java.applet.Applet;
import java.applet.AudioClip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.net.MalformedURLException;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author dunkelheit
 */
public class PegueOPombo extends JFrame {

    JLabel labelPombo;

    JLabel labelPonto = new JLabel("Pombos: 0");

    JLabel timerLabel = new JLabel("  Chances: 5");

    Random random;

    int qtd;

    int tentativas = 5;

    ImageIcon back = new ImageIcon("background.png");

    ImageIcon gameover = new ImageIcon("gameover.jpg");

    public PegueOPombo(int velocidade) {

        setTitle("Pegue o Pombo");
        setLayout(new FlowLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        setContentPane(new JLabel(back));

        getContentPane().setLayout(new FlowLayout());

        labelPombo = new JLabel(new ImageIcon("pombo.png"));

        getContentPane().add(labelPombo);

        getContentPane().add(labelPonto);

        getContentPane().add(timerLabel);

        random = new Random();

        new Timer(velocidade, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {

                labelPombo.setLocation(random.nextInt(getWidth() - 80), random.nextInt(getHeight() - 80));

            }

        }).start();

        labelPombo.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent me) {

                pegouPombo();

            }

        });

        getContentPane().addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent me) {
                qtdTentativas();
            }
        });

        setExtendedState(MAXIMIZED_BOTH);

    }

    public void pegouPombo() {
        qtd++;
        try {
            AudioClip clip = Applet.newAudioClip(new File("som_7.wav").toURL());
            clip.play();
        } catch (MalformedURLException ex) {

        }
        labelPonto.setText("Pombos: " + qtd);
    }

    public void qtdTentativas() {

        tentativas--;
        Random wavRandom = new Random();
        int numero = wavRandom.nextInt(6);
        try {
            AudioClip clip = Applet.newAudioClip(new File("som_" + numero + ".wav").toURL());
            clip.play();
        } catch (MalformedURLException ex) {

        }

        timerLabel.setText("  Chances: " + tentativas);
        if (tentativas == 0) {
            getContentPane().removeAll();
            setContentPane(new JLabel(gameover));
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        System.out.println("Entre com a velocidade do jogo:");

        int velocidade = s.nextInt();

        new PegueOPombo(velocidade);

    }

}

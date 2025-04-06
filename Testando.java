package com.mycompany.testando;

import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellResponse;
import java.awt.EventQueue;
import java.io.*;
import javax.swing.JFileChooser;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import javax.swing.JComboBox;
import java.awt.Font;
import java.beans.JavaBean;
import java.nio.file.CopyOption;
import javax.swing.JTextPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JTextField;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.StandardCopyOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;

public class Testando {

    private JFrame frame;
    private JTextField textField;
    private JTextField textField_1;
    private JButton btnNewButton_1;
    private JFileChooser fileChooser = new JFileChooser();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Testando window = new Testando();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public Testando() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 544, 369);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JButton btnNewButton = new JButton("procurar");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                FileNameExtensionFilter filtro = new FileNameExtensionFilter("Apenas .dll ou .exe", "dll", "exe");
                fileChooser.setAcceptAllFileFilterUsed(false);
                fileChooser.addChoosableFileFilter(filtro);
                
                int resposta = fileChooser.showOpenDialog(null);

                if (resposta == fileChooser.APPROVE_OPTION) {
                    File arquivo = fileChooser.getSelectedFile();
                    System.out.println("Arquivo selecionado com sucesso! " + arquivo.getAbsoluteFile());

                } else if (resposta == fileChooser.CANCEL_OPTION) {
                    JOptionPane.showMessageDialog(null, "SELECIONE ALGUMA DLL OU EXECUTÁVEL!");
                }

            }
        });
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnNewButton.setBounds(259, 44, 127, 41);
        frame.getContentPane().add(btnNewButton);

        textField = new JTextField();
        textField.setEditable(false);
        textField.setBounds(87, 46, 162, 41);
        frame.getContentPane().add(textField);
        textField.setColumns(10);
        textField.setText(" Selecione a dll do jogo! ");

        JComboBox<String> comboBox = new JComboBox();
        comboBox.setBounds(259, 126, 127, 22);
        frame.getContentPane().add(comboBox);
        comboBox.addItem("steam_api64"); //0
        comboBox.addItem("steam_api32"); //1
        comboBox.addItem("SteamDRM"); //2
        comboBox.addItem("online64"); //3
        comboBox.addItem("online32"); //4
        
        //inicia a comboBox na opção abaixo
        comboBox.setSelectedItem("steam_api64");

        textField_1 = new JTextField();
        textField_1.setText(" Selecione a opção de crack ");
        textField_1.setEditable(false);
        textField_1.setColumns(10);
        textField_1.setBounds(87, 117, 162, 41);
        frame.getContentPane().add(textField_1);

        btnNewButton_1 = new JButton("CRACKEAR GAME");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                if(comboBox.getSelectedIndex() == 0) {
                    File file = fileChooser.getSelectedFile();
                    String caminho = file.getAbsolutePath();
                    String sla = new File("game_ali213\\files\\steam_api64.dll").getAbsolutePath();
                    File origem1 = new File(sla);
                    File destino = new File(caminho);
                    
                    try {
                        FileUtils.copyFile(origem1, destino);
                       JOptionPane.showMessageDialog(null, "AÇÃO CONCLUÍDA COM SUCESSO!");
                    } catch(IOException aaaaa) {
                        System.out.println("deu ruim " + aaaaa.getMessage());
                        JOptionPane.showMessageDialog(null, "NÃO FOI POSSÍVEL REALIZAR A AÇÃO!");
                    }
                    
                    
                    
                     //FileUtils.copyFile(origem2, destino);
                     //FileUtils.copyFile(origem3, destino);
                }
                
                if(comboBox.getSelectedIndex() == 1) {
                    File file = fileChooser.getSelectedFile();
                    String caminho = file.getAbsolutePath();
                    String sla = new File("game_ali213\\files\\steam_api.dll").getAbsolutePath();
                    File origem2 = new File(sla);
                    File destino = new File(caminho);
                     
                    try {
                        FileUtils.copyFile(origem2, destino);
                       JOptionPane.showMessageDialog(null, "AÇÃO CONCLUÍDA COM SUCESSO!");
                    } catch(IOException bbbb) {
                        System.out.println("deu ruim " + bbbb.getMessage());
                        JOptionPane.showMessageDialog(null, "NÃO FOI POSSÍVEL REALIZAR A AÇÃO!");
                    }
                }
                
                if(comboBox.getSelectedIndex() == 2) {
                    File file = fileChooser.getSelectedFile();
                    String caminho = file.getAbsolutePath();
                    String executavel = new File("Steamless_CLI\\Steamless.CLI.exe").getAbsolutePath();
                    //String teste = "C:\\Users\\MegaOS\\Documents\\teste\\A Game About Digging A Hole\\DiggingGame\\Binaries\\Win64\\DiggingGame.exe";
                    //File file2 = new File(pasta, "Steamless.CLI.exe");
                    
                    String parametros = " --quiet" + " '" + caminho + "'";
                    
                    if(!parametros.isEmpty()) {
                        PowerShellResponse response = PowerShell.executeSingleCommand(executavel + parametros);
                        JOptionPane.showMessageDialog(null, "AÇÃO CONCLUÍDA COM SUCESSO!");
                        System.out.println(response.getCommandOutput());
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "NÃO FOI POSSÍVEL REALIZAR A AÇÃO!");
                    }
                }
                
                if(comboBox.getSelectedIndex() == 3) {
                    File file = fileChooser.getSelectedFile();
                    String caminho = file.getAbsolutePath();
                    String sla = new File("game_goldberg\\files\\steam_api64.dll").getAbsolutePath();
                    File origem2 = new File(sla);
                    File destino = new File(caminho);
                     
                    try {
                        FileUtils.copyFile(origem2, destino);
                       JOptionPane.showMessageDialog(null, "AÇÃO CONCLUÍDA COM SUCESSO!");
                    } catch(IOException cccc) {
                        System.out.println("deu ruim " + cccc.getMessage());
                        JOptionPane.showMessageDialog(null, "NÃO FOI POSSÍVEL REALIZAR A AÇÃO!");
                    }
                }
                
                if(comboBox.getSelectedIndex() == 4) {
                    File file = fileChooser.getSelectedFile();
                    String caminho = file.getAbsolutePath();
                    String sla = new File("game_goldberg\\files\\steam_api.dll").getAbsolutePath();
                    File origem2 = new File(sla);
                    File destino = new File(caminho);
                     
                    try {
                        FileUtils.copyFile(origem2, destino);
                       JOptionPane.showMessageDialog(null, "AÇÃO CONCLUÍDA COM SUCESSO!");
                    } catch(IOException dddd) {
                        System.out.println("deu ruim " + dddd.getMessage());
                        JOptionPane.showMessageDialog(null, "NÃO FOI POSSÍVEL REALIZAR A AÇÃO!");
                    }
                }
            }
        });
        btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnNewButton_1.setBounds(168, 202, 169, 56);
        frame.getContentPane().add(btnNewButton_1);

        //System.out.println("numeração itens " + comboBox.getItemAt(0));
    }
}

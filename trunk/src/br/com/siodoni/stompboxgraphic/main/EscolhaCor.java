package br.com.siodoni.stompboxgraphic.main;

import java.awt.*;
import javax.swing.*;
import javax.swing.colorchooser.*;
import javax.swing.event.*;

public class EscolhaCor extends JApplet implements ChangeListener {

    private Container Panel;
    private LayoutManager Layout;
    private JColorChooser Chooser;
    private ColorSelectionModel Model;

    public EscolhaCor() {

        /* Instantiation */
        Layout = new FlowLayout();
        Chooser = new JColorChooser();
        Model = Chooser.getSelectionModel();
        Panel = getContentPane();

        /* Location */
        Panel.setLayout(Layout);
        Panel.add(Chooser);

        /* Decoration */
        Chooser.setColor(Color.yellow);
        Panel.setBackground(Color.yellow);

        /* Configuration */
        Model.addChangeListener(this);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        Color Choice;
        Choice = Chooser.getColor();
        Panel.setBackground(Choice);
        System.out.println(Choice);
    }
}

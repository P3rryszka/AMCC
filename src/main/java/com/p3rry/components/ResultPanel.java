package com.p3rry.components;

import com.p3rry.components.componentsmanagement.IComponentsAdder;
import com.p3rry.components.componentsmanagement.IComponentsSetter;
import com.p3rry.components.componentsmanagement.ISelfComponentSetter;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;

@Getter
public class ResultPanel implements ISelfComponentSetter, IComponentsAdder, IComponentsSetter {
    public static final int PANEL_X_POSITION = 300;
    public static final int PANEL_Y_POSITION = 530;
    public static final int PANEL_WIDTH = 180;
    public static final int PANEL_HEIGHT = 50;
    private static final String INITIAL_VALUE = "0";

    private JPanel panel;
    private JTextArea resultTextField;

    public ResultPanel() {
        this.panel = new JPanel();
        this.resultTextField = new JTextArea();

        setSelfComponent();
        addComponents();
        setComponents();
    }

    @Override
    public void setSelfComponent() {
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel.setBounds(PANEL_X_POSITION, PANEL_Y_POSITION,
                PANEL_WIDTH, PANEL_HEIGHT);
    }

    @Override
    public void addComponents() {
        panel.add(resultTextField);
    }

    @Override
    public void setComponents() {
        resultTextField.setPreferredSize(new Dimension(160,30));
        resultTextField.setEditable(false);
        resultTextField.setText(INITIAL_VALUE);
        resultTextField.setFocusable(false);
    }
}

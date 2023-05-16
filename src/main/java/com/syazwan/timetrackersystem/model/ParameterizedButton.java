package com.syazwan.timetrackersystem.model;

import javafx.scene.control.Button;

public class ParameterizedButton extends Button {
    private int parameter;

    public ParameterizedButton(String text, int parameter) {
        super(text);
        this.parameter = parameter;
    }

    public int getParameter() {
        return parameter;
    }
}
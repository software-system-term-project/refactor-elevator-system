package com.elevator.system.button;

import java.util.ArrayList;
import java.util.List;

public class ButtonsList {
    private List<Button> buttons = new ArrayList<>();

    public void addButton(Button button) {
        buttons.add(button);
    }

    public void pressButton(int index) {
        buttons.get(index).pressed();
    }
}

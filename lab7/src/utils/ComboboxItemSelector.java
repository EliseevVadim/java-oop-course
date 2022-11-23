package utils;

import javax.swing.*;

public class ComboboxItemSelector {
    public static void selectComboboxItemForSpecificId(JComboBox comboBox, long id) {
        for (int i = 0; i < comboBox.getItemCount(); i++) {
            if (Long.parseLong(comboBox.getItemAt(i).toString().split(" - ")[0]) == id){
                comboBox.setSelectedIndex(i);
                return;
            }
        }
    }
}

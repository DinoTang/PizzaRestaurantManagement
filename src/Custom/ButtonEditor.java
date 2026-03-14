/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Custom;

/**
 *
 * @author GIAHUY2004
 */
import GUI.DlgCongThuc;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;

public class ButtonEditor extends DefaultCellEditor {

    protected JButton button;
    private JTable table;
    private boolean clicked;

    public ButtonEditor(JCheckBox checkBox, JTable table) {
        super(checkBox);

        this.table = table;
        button = new JButton("Xem");

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int row = table.getSelectedRow();

                String maSP = table.getValueAt(row, 0).toString();

                DlgCongThuc dlg = new DlgCongThuc(maSP);
                dlg.setVisible(true);

                fireEditingStopped();
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {

        clicked = true;
        return button;
    }

    @Override
    public Object getCellEditorValue() {

        if (clicked) {
            clicked = false;
        }

        return "Xem";
    }

    @Override
    public boolean stopCellEditing() {
        clicked = false;
        return super.stopCellEditing();
    }
}

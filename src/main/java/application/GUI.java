package application;

import javax.swing.*;
import java.io.Serial;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    
    @Serial
    private static final long serialVersionUID = -6218820567019985015L;
    private final Map<JButton, Position> cells = new HashMap<>();
    private final Logic logic;
    private Runnable onGameOver;

    public GUI(int size, Runnable onGameOver) {
        this(size);
        this.onGameOver = onGameOver;
    }
    
    public GUI(int size) {
        this.onGameOver = () -> System.exit(0);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(70*size, 70*size);
        this.logic = new LogicImpl();

        JPanel panel = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(panel);

        ActionListener al = e -> {
            var jb = (JButton)e.getSource();
            handleClick(jb);
        };

        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
            	final JButton jb = new JButton();
                this.cells.put(jb, new Position(j,i));
                jb.addActionListener(al);
                panel.add(jb);
            }
        }
        this.redraw();
        this.setVisible(true);
    }

    private void redraw() {
        for (var entry: this.cells.entrySet()){
            entry.getKey().setText(
                switch(this.logic.getMark(entry.getValue())){
                    case STATIC -> "*";
                    case DYNAMIC -> "o";
                    case EMPTY -> " ";
                }
            );
        }
    }

    private void handleClick(JButton jb){
        if (this.logic.hit(this.cells.get(jb))){
            this.redraw();
            if (this.logic.isOver()){
                onGameOver.run();
            }
        }
    }

    public Map<JButton, Position> getCells() {
        return cells;
    }

    public Logic getLogic() {
        return logic;
    }

    public boolean doSyncClick(JButton button) {
        try {
            SwingUtilities.invokeAndWait(() -> handleClick(button));
        } catch (InvocationTargetException | InterruptedException e) {
            return false;
        }
        return true;
    }
}

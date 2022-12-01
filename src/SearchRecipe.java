import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchRecipe extends JFrame{
    private JPanel recipeTitlePanel;
    private JLabel tittle;
    private JPanel recipeMainFrame;

    public SearchRecipe()
    {
        super();
        this.setSize(800,400);
        this.setBounds(300, 50, 900, 700);
        this.setContentPane(recipeMainFrame);
        this.setVisible(true);

    };
}

import javax.swing.*;

public class ShoppingList extends JFrame {
    private JPanel shoppingListMainFrame;

    public ShoppingList()
    {
        super();
        this.setSize(800,400);
        this.setBounds(300, 50, 900, 700);
        this.setContentPane(shoppingListMainFrame);
        this.setVisible(true);

    };
}

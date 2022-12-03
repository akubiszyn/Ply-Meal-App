import javax.swing.*;

public class Fridge extends JFrame {
    private JPanel fridgeMainFrame;

    public Fridge()
    {
        super();
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800,400);
        this.setBounds(300, 50, 900, 700);
        this.setContentPane(fridgeMainFrame);
        this.setVisible(true);

    };
}

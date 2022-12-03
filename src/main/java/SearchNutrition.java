import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class SearchNutrition extends JFrame {


    private JPanel nutritionMainFrame;

    public SearchNutrition()
    {
        super();
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800,400);
        this.setBounds(300, 50, 900, 700);
        this.setContentPane(nutritionMainFrame);
        this.setVisible(true);

    };
}

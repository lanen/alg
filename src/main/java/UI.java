import javax.swing.*;
import java.awt.*;

/**
 * @author evan
 * create-date 2018/8/3
 */
public abstract class UI {

    /**
     *
     * @return
     */
    protected JFrame createJFrame(String title){

        JFrame jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setLayout(new BorderLayout());
        jFrame.setSize(600,600);

        //居中对齐
        jFrame.setLocationRelativeTo(null);
        jFrame.setTitle(title );
        return jFrame;
    }

}

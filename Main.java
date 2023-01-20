import javax.swing.*;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
//import java.awt.Insets;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.awt.Color;
//import java.awt.Component;


class Main 
{
/**
 * @param args
 */
public static void main(String[] args) 
{
    //window code  
  JFrame window = new JFrame("Flappy Bird");
  window.setSize(1650, 900);
  window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  
  //Create Graphics Container

  final JPanel container = new JPanel();
  GridBagLayout layout = new GridBagLayout();
  //GridBagConstraints constraints = new GridBagConstraints();


  //Make Window Visible
  final ImageIcon flappyBirdIcon = new ImageIcon("flappybird.png");
  final JLabel imageLabel = new JLabel(flappyBirdIcon);

  //Make Button

  final JButton onOffButton = new JButton("Click To Flap");
  onOffButton.addActionListener(new ActionListener()
  {
    boolean isOn = false;
    @Override
    public void actionPerformed(ActionEvent e)
    {
      if(isOn)
      {
        isOn = false;
        ImageIcon.setIcon(flappyBirdIcon);
      } else 
      {
        isOn = true;
      }
    }
  });


  container.setLayout(layout);
  container.add(imageLabel);
  container.add(onOffButton);
  window.add(container);

  window.setVisible(true);


}
}
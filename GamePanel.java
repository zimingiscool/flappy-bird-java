import java.awt.*;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener{


	static final int SCREEN_WIDTH = 500;
	static final int SCREEN_HEIGHT = 500;
	static final int UNIT_SIZE = 25;
	static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE;
	static final int DELAY = 75;
	final int x[]  = new int[GAME_UNITS];
	final int y[] = new int[GAME_UNITS];
	int bodyParts = 6;
	int applesEaten;
	int appleX;
	int appleY;
	char direction = 'R';
	boolean running = false;
	Timer timer;
	Random random;


    GamePanel(){
        random = new Random();
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setBackground(new Color(46, 52, 64));
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		startGame();
    }
	JButton resetButton;
	boolean gameOver = false;
	public void startGame() 
	{
		newApple();
		running = true;
		timer = new Timer(DELAY, this);
		timer.start();
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		draw(g);
	}
	public void draw(Graphics g)
	{	if(running)
		{	
			/* 
			for(int i = 0;i<SCREEN_HEIGHT/UNIT_SIZE;i++){
				g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_HEIGHT);
				g.drawLine(0, i*UNIT_SIZE, SCREEN_WIDTH, i*UNIT_SIZE);
			}
			*/
			g.setColor(new Color(76, 86, 106));
			g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);
	
			for(int i =0;i<bodyParts;i++)
			{
				if(i ==0)
				{
					g.setColor(new Color(129, 161, 193));
					g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
				}else
				{
					g.setColor(new Color(136, 192, 208));
					g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
				}
			}
			g.setColor(new Color(216, 222, 233));
			g.setFont(new Font("Helvetica", Font.BOLD, 24));
			FontMetrics metrics = getFontMetrics(g.getFont());
			g.drawString("Apples Eaten: " + applesEaten, (SCREEN_WIDTH - metrics.stringWidth("Apples Eaten: "+ applesEaten))/2, g.getFont().getSize());		} else if(!gameOver)
		{
			gameOver(g);
			gameOver = true;
		}
	}
	public void newApple()
	{
		appleX = random.nextInt((int)SCREEN_WIDTH/UNIT_SIZE)*UNIT_SIZE;
		appleY = random.nextInt((int)SCREEN_HEIGHT/UNIT_SIZE)*UNIT_SIZE;
	}
	public void move()
	{
		for(int i = bodyParts; i> 0; i--) 
		{
			x[i] = x[i-1];
			y[i] = y[i-1];
		}
		
		switch(direction) {
		case 'U':
			y[0] = y[0] - UNIT_SIZE;
			break;
		case 'D':
			y[0] = y[0] + UNIT_SIZE;
			break;
		case 'L':
			x[0] = x[0] - UNIT_SIZE;
			break;
		case 'R':
			x[0] = x[0] + UNIT_SIZE;
			break;
		
		}

	}
	public void checkApple() 
	{
		if((x[0] == appleX) && (y[0] == appleY))
		{
			bodyParts++;
			applesEaten++;
			newApple();
			System.out.println("Apples Eaten: " + applesEaten);
			System.out.println("Body parts: " + bodyParts);
		}
	}
	public void checkCollisions() 
		//Check For Collisions With Body
	{	for(int i=bodyParts;i>0;i--)
		{
			if((x[0] == x[i]) && (y[0] == y[i]))
			{
				running = false;
			}
		}
		//Check For Collisions with borders
		if(x[0] < 0)
		{
			running = false;
		}

		if(x[0] > SCREEN_WIDTH)
		{
			running = false;
		}

		if(y[0] < 0)
		{
			running = false;
		}

		if(y[0] > SCREEN_HEIGHT)
		{
			running = false;
		}

		if(!running)
		{
			timer.stop();
		}

	}

	public void gameOver (Graphics g)
	{
		//Game Over Interface
		g.setColor(new Color(216, 222, 233));
		g.setFont(new Font("Helvetica", Font.BOLD, 20));
		FontMetrics metrics = getFontMetrics(g.getFont());
		g.drawString("Game Over" + "\n" + "Your Score: " + applesEaten, (SCREEN_WIDTH - metrics.stringWidth("Game Over" + "\n" + "Your Score: " + applesEaten))/2, SCREEN_HEIGHT/2);
		
	}

	public class MyKeyAdapter extends KeyAdapter
	{
		@Override
		public void keyPressed(KeyEvent e)
		{
			switch(e.getKeyCode())
			{
				case KeyEvent.VK_LEFT:
					if(direction != 'R')
					{
						direction = 'L';
					}
				break;

				case KeyEvent.VK_RIGHT:
					if(direction != 'L')
					{
						direction = 'R';
					}
				break;

				case KeyEvent.VK_UP:
					if(direction != 'D')
					{
						direction = 'U';
					}
				break;

				case KeyEvent.VK_DOWN:
					if(direction != 'U')
					{
						direction = 'D';
					}
				break;
			}
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(running){
			move();
			checkApple();
			checkCollisions();
		}
		
		repaint();
		
	}

}

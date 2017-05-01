import java.applet.Applet;
import java.awt.*;
import javax.swing.*;
import java.util.concurrent.TimeUnit;
import java.lang.Math.*;

/* <applet code="b11" width="600" height="600"> </applet> */

public class b11 extends Applet 
{
	int appletHeight = 0, appletWidth = 0, box_width = 0, box_height = 0, spacing = 0, gap = 0, top_padding = 0, left_padding=0, connectedLineHeight=40, step=1, t_sleep=20;
	int clicked = 0;
	Box[] bb;
	Button b;
	//Graphics g;
	public void init()
	{
		setLayout(null);
		setBackground(Color.WHITE);
		Dimension appletSize = this.getSize();
		appletHeight = appletSize.height;
		appletWidth = appletSize.width;
		
		box_width = appletWidth/12 + 14;
		box_height = appletHeight/12;
		spacing = 5; 

		top_padding =(int)(box_height*0.4 + 10);
		left_padding = (int)(box_width*0.4 + 5);
		//b = new Button("Sort");

		bb = new Box[8];
		bb[0] = new Box(3);
		bb[1] = new Box(5);
		bb[2] = new Box(8);
		bb[3] = new Box(9);
		bb[4] = new Box(7);
		bb[5] = new Box(4);
		bb[6] = new Box(2);
		bb[7] = new Box(1);
		
		gap = spacing + box_width;
		
		//b.setBounds(10,10,50,50);
		//b.addActionListener(this);

		for(int i=0; i<bb.length; i++)
		{
			bb[i].updateBounds(gap*i+20, (appletHeight/2)-(box_height/2));
			//g.drawRect(gap*i+20, (appletHeight/2)-(box_height/2), box_width, box_height);
			//bb[i].setEnabled(false);
			//add(bb[i]);
		}
				
	}
	
	public void paint(Graphics g)
	{
		display(g);
		for(int i=0;i<4;i++)
		{
			swappingLines(bb[i],bb[i+4],g);
			swapUp(bb[i], bb[i+4], g);
			//swapDown(bb[i+4], bb[i], g);
		}
		//swappingLines(bb[2],bb[5],g);
	}

	
	
	public void swappingLines(Box b1, Box b2, Graphics g)
	{
		int x1 = b1.xx;
		int x2 = b2.xx;
		
		int y1 = b1.yy;
		int y2 = b2.yy;

		int mid_b1 = x1 + box_width/2;
		int mid_b2 = x2 + box_width/2;
		
		//for up
		g.setColor(Color.BLACK);
		g.drawLine(mid_b1, y1, mid_b1, y1 - connectedLineHeight);
		g.drawLine(mid_b1, y1 - connectedLineHeight, mid_b2, y1 - connectedLineHeight);
		g.drawLine(mid_b2, y1 - connectedLineHeight, mid_b2, y2);

		//for down
		g.drawLine(mid_b2, y2, mid_b2, y2 + connectedLineHeight + box_height);
		g.drawLine(mid_b2, y2 + connectedLineHeight + box_height, mid_b1, y2 + connectedLineHeight + box_height);
		g.drawLine(mid_b1, y2 + connectedLineHeight + box_height, mid_b1, y1);

		try
		{
			TimeUnit.SECONDS.sleep(2);
		}
		catch(Exception e)
		{
		}
	}
	
	public void swapUp(Box b1, Box b2, Graphics g)
	{
		moveBox(b1, b1.xx, b1.yy-connectedLineHeight-(box_height/2), g);
		moveBox(b1, b2.xx, b2.yy-connectedLineHeight-(box_height/2), g);
		moveBox(b1, b2.xx, b2.yy, g);
	}

	/*public void swapDown(Box b1, Box b2, Graphics g)
	{
		moveBox(b1, b1.xx, b1.yy+connectedLineHeight+(box_height/2), g);
		moveBox(b1, b2.xx, b2.yy+connectedLineHeight+(box_height/2), g);
		moveBox(b1, b2.xx, b2.yy, g);
	}*/

	public void moveBox(Box box, int dest_x, int dest_y, Graphics g)
	{
		int bx = box.xx;
		int by = box.yy;
		
		if(bx == dest_x)
		{
			if(by > dest_y)
			{
				while((by-dest_y)>0)
				{

					g.setColor(Color.WHITE);
					g.drawString(Integer.toString(box.value), bx + left_padding, by + top_padding);
					g.drawRect(bx, by, box_width, box_height);
					

					by-=step;				
					
					g.setColor(Color.BLACK);
					g.drawString(Integer.toString(box.value), bx + left_padding, by + top_padding);
					g.drawRect(bx, by, box_width, box_height);
					
					try
					{Thread.sleep(t_sleep);}
					catch(Exception e)
					{}
				}	
			}
			else if(by < dest_y)
			{
				while((dest_y-by)>0)
				{
					g.setColor(Color.WHITE);
					g.drawString(Integer.toString(box.value), bx + left_padding, by + top_padding);
					g.drawRect(bx, by, box_width, box_height);
					//g.drawString(Integer.toString(box.value), bx + left_padding, by + top_padding);

					by+=step;					
					
					g.setColor(Color.BLACK);
					g.drawString(Integer.toString(box.value), bx + left_padding, by + top_padding);
					g.drawRect(bx, by, box_width, box_height);
					//g.drawString(Integer.toString(box.value), bx + left_padding, by + top_padding);
					
					try
					{Thread.sleep(t_sleep);}
					catch(Exception e)
					{}
				}
			}
		}

		else if(by == dest_y)
		{
			if(bx > dest_x)
			{
				while((bx-dest_x)>0)
				{
					g.setColor(Color.WHITE);
					g.drawString(Integer.toString(box.value), bx + left_padding, by + top_padding);
					g.drawRect(bx, by, box_width, box_height);
					//g.drawString(Integer.toString(box.value), bx + left_padding, by + top_padding);

					bx-=step;					
					
					g.setColor(Color.BLACK);
					g.drawString(Integer.toString(box.value), bx + left_padding, by + top_padding);
					g.drawRect(bx, by, box_width, box_height);
					//g.drawString(Integer.toString(box.value), bx + left_padding, by + top_padding);
					

					try
					{Thread.sleep(t_sleep);}
					catch(Exception e)
					{}
				}	
			}
			else if(bx < dest_x)
			{
				while((dest_x-bx)>0)
				{
					g.setColor(Color.WHITE);
					g.drawString(Integer.toString(box.value), bx + left_padding, by + top_padding);
					g.drawRect(bx, by, box_width, box_height);
					//g.drawString(Integer.toString(box.value), bx + left_padding, by + top_padding);

					bx+=step;					
					
					g.setColor(Color.BLACK);
					g.drawString(Integer.toString(box.value), bx + left_padding, by + top_padding);
					g.drawRect(bx, by, box_width, box_height);
					//g.drawString(Integer.toString(box.value), bx + left_padding, by + top_padding);
					

					try
					{Thread.sleep(t_sleep);}
					catch(Exception e)
					{}
				}
			}
		}
		
	box.xx = bx;
	box.yy = by;
		
	}
	public void display(Graphics g)
	{
		for(int i=0; i<bb.length; i++)
		{
			//String s = bb[i].value.toString();
			//bb[i].updateBounds(gap*i+20, (appletHeight/2)-(box_height/2));
			g.drawRect(bb[i].xx, bb[i].yy, box_width, box_height);
			g.drawString(Integer.toString(bb[i].value), bb[i].xx + left_padding, bb[i].yy + top_padding);
			//bb[i].setEnabled(false);
			//add(bb[i]);
		}
	}
		
}


class Box
{
	public int xx=0;
	public int yy=0;
	//public int width = 50;
	//public int height = 50;
	public int value=0;
	Box(int value)
	{
		this .value = value;
		//this.setBounds(xx, yy, width, height);
		//this.setLayout(null);
	}
	public void updateBounds(int xx, int yy)
	{
		this.xx = xx;
		this.yy = yy;
	}
}



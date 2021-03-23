/**
 * 
 */
package org.bawaweb;
//Program attract.java (c) 1996 by J. C. Sprott <sprott@juno.physics.wisc.edu>
//A Java applet that searches for strange attractors and displays them

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Image;
import java.awt.Font;
import java.awt.FontMetrics;
import java.util.Date;
import java.util.Random;
import java.applet.Applet;

/**
 * @author Navroz
 *	https://www.dynamicmath.xyz/strange-attractors/
 */
public class StrangeAttractorFinder extends Applet implements Runnable {

	private static final long serialVersionUID = 18588L;

	/**
	 * @param args
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	} */

	Thread runner;
    int w, h, nc, bc, dt, flip;
    String s;
    Color colors[] = new Color[256];
    Image offscreenImg1, offscreenImg2;//One displays while other calculates, then flip
    Graphics offscreenG1, offscreenG2;
    Graphics draw2Image[] = new Graphics[2];//Image to draw onto
    Date theDate;
    Random rnd;

    public String getAppletInfo() {
        return "Strange Attractor Search Applet (c) 1996 J. C. Sprott";
    }

    public void init() {
        s=getParameter("width");            //image width in pixels
        if (s==null) w=320; else w=Integer.parseInt(s);
        s=getParameter("height");           //image height in pixels
        if (s==null) h=200; else h=Integer.parseInt(s);
        s=getParameter("numcolors");        //number of colors (0-256)
        if (s==null) nc=256; else nc=Integer.parseInt(s);
        if (nc>256) nc=256;
        s=getParameter("backgroundcolor");  //24-bit decimal, neg=transparent
        if (s==null) bc=-1; else bc=Integer.parseInt(s);
        s=getParameter("time");             //minimum time between cases (ms)
        if (s==null) dt=5000; else dt=Integer.parseInt(s);

        if (nc>0) {
            for (int i=0; i<nc; i++) {
                float c=(float)i/(float)nc;
                colors[i]=Color.getHSBColor(c,1.0f,1.0f);//Spectrum
            }
        }
        rnd=new Random();
    }

    public void start() {
        if (runner==null) {
            runner=new Thread(this);
            runner.start();
        }
    }

    public void stop() {
        if (runner!=null) {
            runner=null;
        }
    }

    public void run() {
		double x = 0, y = 0, z = 0, xmin = 0, xmax = 0, xe = 0, ye = 0, xnew, ynew, xenew;
		double dx = 0, dh = 0, dw = 0, dz = 0, ran = 0;
		double a[] = { 0, 0, 0, 0, 0, 0 };
		int n = 0, xp, yp, zp;
		long t0 = 0;
		boolean le = false;
		offscreenImg1 = createImage(this.getSize().width, this.getSize().height);
		offscreenImg2 = createImage(this.getSize().width, this.getSize().height);
		offscreenG1 = offscreenImg1.getGraphics();
		offscreenG2 = offscreenImg2.getGraphics();
		draw2Image[0] = offscreenG1;
		draw2Image[1] = offscreenG2;
		Font f = new Font("TimesRoman", Font.PLAIN, 24);
		FontMetrics fm = getFontMetrics(f);
		flip = 1;// Use second image as starting point
		draw2Image[flip].setFont(f);
		s = "Searching - Please wait...";
		xp = (this.getSize().width - fm.stringWidth(s)) / 2;
		yp = (this.getSize().height + fm.getHeight()) / 2;
		if (xp > 0)
			draw2Image[flip].drawString(s, xp, yp);
		paint(this.getGraphics());// Draw the searching statement to screen
		while (true) {
			if (n == 0) {
				for (int i = 0; i < 6; i++) {
					ran = rnd.nextFloat();
					a[i] = 6.0 * ran - 3.0;
				}
				x = 0;
				y = 0;
				xe = x + 1e-10;
				ye = y;
				xmin = 1e6;
				xmax = -xmin;
				le = false;
			} else if (n >= 1 && n <= 199) {
				if (x < xmin)
					xmin = x;
				if (x > xmax)
					xmax = x;
				xenew = a[0] + xe * (a[1] + a[2] * xe + a[3] * ye) + ye * (a[4] + a[5] * ye);
				ye = xe;
				xe = xenew;
				if (Math.abs(xe - x) > .001)
					le = true;
			} else if (n == 200) {
				if (!le)
					n = -1;
			} else if (n == 201) {
				dx = .1 * (xmax - xmin);
				if (dx == 0)
					dx = 1;
				xmax = xmax + dx;
				xmin = xmin - dx;
				dw = w / (xmax - xmin);
				dh = h / (xmax - xmin);
				dz = nc / (xmax - xmin);
				flip = (flip + 1) % 2;
				draw2Image[flip].setColor((bc < 0) ? getBackground() : new Color(bc));
				if (nc == 0) {
					draw2Image[flip].setColor(Color.black);
				}
				draw2Image[flip].fillRect(0, 0, this.getSize().width, this.getSize().height);
				theDate = new Date();
				t0 = theDate.getTime();
			} else {
				xp = (int) (dw * (x - xmin));
				yp = (int) (dh * (xmax - y));
				if (nc > 0) {
					zp = (int) (dz * (z - xmin)) % nc;
					if (zp < 0)
						zp = 0;
					draw2Image[flip].setColor(colors[zp]);
				}
				draw2Image[flip].drawLine(xp, yp, xp, yp);
			}
			n = n + 1;
			if (n % 200 == 0) {
				theDate = new Date();
				if (n >= 2000 && theDate.getTime() > t0 + dt) {
					paint(this.getGraphics());
					n = 0;
				}
			}
			xnew = a[0] + x * (a[1] + a[2] * x + a[3] * y) + y * (a[4] + a[5] * y);
			ynew = x;
			z = y;
			y = ynew;
			x = xnew;
			if (Math.abs(x) > 1e4)
				n = 0;
		}
    }

	public void paint(Graphics g) {
		if (flip == 0) {
			g.drawImage(offscreenImg1, 0, 0, this);
		} else {
			g.drawImage(offscreenImg2, 0, 0, this);
		}

	}

}

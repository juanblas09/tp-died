package tp.gui;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.ArrayList;

import javax.swing.JPanel;

import tp.modelos.Graph;
import tp.modelos.Sucursal;

class Punto {
	public int x,y,width,height,id;
	public String text;
	public boolean clicked;
	
	public Punto(int x, int y, int width, int height, String text, int id) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.text = text;
		this.id = id;
	}
	
	public boolean isInside(int x, int y) {
		return x >= this.x && x <= (this.x + width) && y >= this.y && y <= (this.y + height);
	}
}

public class CustomDrawing extends JPanel {
	
	public CustomDrawing() {
		super();
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				int clickX = e.getX();
				int clickY = e.getY();
				
			}
			

			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
			}
		});
	}
	

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		dibujar(g2d);
	}
	
	private void dibujar(Graphics2D g2d) {
		Graph g = new Graph();
		g.construirGrafo();
		
		
		
		List<Punto> puntos = mapPuntos(g.getVertices());
		
		for(Punto p: puntos) {
			g2d.setColor(Color.ORANGE);
			g2d.fillRect(p.x, p.y, p.height, p.width);
			g2d.setColor(Color.BLACK);
			g2d.drawString(p.text, p.x, p.y+p.height/2);
		}
		
	}
	
	private List<Punto> mapPuntos(List<Sucursal> sucursales){
		List<Punto> res = new ArrayList<Punto>();
		int auxX = 20;
		int auxY = 20;
		for(Sucursal s: sucursales) {
			Punto p = new Punto(auxX,auxY,50,50,s.getNombre(),s.getId());
			res.add(p);
			auxX += 100;
			if(auxX==520) {
				auxX = 20;
				auxY += 100;
			}
		}
		return res;
	}
}

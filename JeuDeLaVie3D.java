import java.util.Scanner;
import java.util.Random;
import java.awt.Color;

public class JeuDeLaVie3D {
	static final Scanner input = new Scanner(System.in);
	static final Random rand = new Random();

	enum Cell{DEAD, ALIVE}
	int size ;
	int gen=0 ;
	Cell[][][] ecosysteme ;
	int[][][] voisines ;

	public JeuDeLaVie3D(int n, double p){
		size = n;
		ecosysteme = new Cell[n][n][n];
		voisines = new int[n][n][n];

		for (int z=0; z<ecosysteme.length; z++) {
			for (int y=0; y<ecosysteme[0].length; y++) {
				for (int x=0; x<ecosysteme[0][0].length; x++) {
					if(tireProba(p)) {
						ecosysteme[z][y][x] = Cell.ALIVE;
						voisines[z][y][x] = nbVoisines(x,y,z);
					} else {
						ecosysteme[z][y][x] = Cell.DEAD;
					}
			}
			}
		}
	}

	public static void main(String[] args) {
        // StdDraw.enableDoubleBuffering();
  		final double PROBA = 0.025 ; // pour l'initialisation
  		final int n = 70;

  		StdDraw3D.setScale(0, n); // mise à l'échelle de la fenêtre
		JeuDeLaVie3D jeu = new JeuDeLaVie3D(n,PROBA) ; // création du jeu
		while(!jeu.isAllCellsDead()) {
			StdDraw3D.pause(1000);
			jeu.nextGen();
			jeu.dispEco(); 
		}
	}
	
	public boolean isAllCellsDead() {
		for (int z=0; z<ecosysteme.length; z++) {
			for (int y=0; y<ecosysteme[0].length; y++) {
				for (int x=0; x<ecosysteme[0][0].length; x++) {
					if(ecosysteme[z][y][x] == Cell.ALIVE) {
						return false;
					}
				}
			}
		}
		return true;
	}

	public boolean tireProba(double p){
		if(rand.nextDouble() < p) return true ;
		return false ;
	}

	public void nextGen() {
		for (int z=0; z<ecosysteme.length; z++) {
			for (int y=0; y<ecosysteme[0].length; y++) {
				for (int x=0; x<ecosysteme[0][0].length; x++) {
					if(ecosysteme[z][y][x] == Cell.ALIVE) {
						if(voisines[z][y][x] == 2 || voisines[z][y][x] == 3) {} else {
							ecosysteme[z][y][x] = Cell.DEAD;
						}
					} else {
						if( voisines[z][y][x] == 3) {
							ecosysteme[z][y][x] = Cell.ALIVE;
						}
					}
				}
			}
		}


		for (int z=0; z<ecosysteme.length; z++) {
			for (int y=0; y<ecosysteme[0].length; y++) {
				for (int x=0; x<ecosysteme[0][0].length; x++) {
					voisines[z][y][x] = nbVoisines(x,y,z);
				}
			}
		}
		gen++;
	}

	public int overflow(int n, int max, int min) {
		if(n>max-1) { return min; }
		if(n<min) { return max-1; }
		return n;
	}

	public int nbVoisines(int x, int y,int z) {
		int count = 0;

		// Y+1 Z+1
		if(ecosysteme[ overflow(z+1, ecosysteme.length, 0) ][ overflow(y+1, ecosysteme[0].length, 0)  ][ overflow(x, ecosysteme[0][0].length, 0)  ]== Cell.ALIVE) { count++; }
		if(ecosysteme[ overflow(z+1, ecosysteme.length, 0)][ overflow(y+1, ecosysteme[0].length, 0)  ][ overflow(x+1, ecosysteme[0][0].length, 0)  ] == Cell.ALIVE) { count++; }
		if(ecosysteme[ overflow(z+1, ecosysteme.length, 0)][ overflow(y+1, ecosysteme[0].length, 0)  ][ overflow(x-1, ecosysteme[0][0].length, 0)  ] == Cell.ALIVE) { count++; }

		// Y+1 Z
		if(ecosysteme[z][ overflow(y+1, ecosysteme[0].length, 0)  ][ overflow(x, ecosysteme[0][0].length, 0)  ] == Cell.ALIVE) { count++; }
		if(ecosysteme[z][ overflow(y+1, ecosysteme[0].length, 0)  ][ overflow(x+1, ecosysteme[0][0].length, 0)  ] == Cell.ALIVE) { count++; }
		if(ecosysteme[z][ overflow(y+1, ecosysteme[0].length, 0)  ][ overflow(x-1, ecosysteme[0][0].length, 0)  ] == Cell.ALIVE) { count++; }

		// Y+1 Z-1
		if(ecosysteme[ overflow(z-1, ecosysteme.length, 0) ][ overflow(y+1, ecosysteme[0].length, 0)  ][ overflow(x, ecosysteme[0][0].length, 0)  ] == Cell.ALIVE) { count++; }
		if(ecosysteme[ overflow(z-1, ecosysteme.length, 0) ][ overflow(y+1, ecosysteme[0].length, 0)  ][ overflow(x+1, ecosysteme[0][0].length, 0)  ] == Cell.ALIVE) { count++; }
		if(ecosysteme[ overflow(z-1, ecosysteme.length, 0) ][ overflow(y+1, ecosysteme[0].length, 0)  ][ overflow(x-1, ecosysteme[0][0].length, 0)  ] == Cell.ALIVE) { count++; }


		// Y-1 Z+1
		if(ecosysteme[ overflow(z+1, ecosysteme.length, 0) ][ overflow(y-1, ecosysteme[0].length, 0)  ][ overflow(x, ecosysteme[0][0].length, 0) ] == Cell.ALIVE) { count++; }
		if(ecosysteme[ overflow(z+1, ecosysteme.length, 0) ][ overflow(y-1, ecosysteme[0].length, 0)  ][ overflow(x+1, ecosysteme[0][0].length, 0) ] == Cell.ALIVE) { count++; }
		if(ecosysteme[ overflow(z+1, ecosysteme.length, 0) ][ overflow(y-1, ecosysteme[0].length, 0)  ][ overflow(x-1, ecosysteme[0][0].length, 0) ] == Cell.ALIVE) { count++; }

		// Y-1 Z
		if(ecosysteme[ z ][ overflow(y-1, ecosysteme[0].length, 0)  ][ overflow(x, ecosysteme[0][0].length, 0) ] == Cell.ALIVE) { count++; }
		if(ecosysteme[ z ][ overflow(y-1, ecosysteme[0].length, 0)  ][ overflow(x+1, ecosysteme[0][0].length, 0) ] == Cell.ALIVE) { count++; }
		if(ecosysteme[ z ][ overflow(y-1, ecosysteme[0].length, 0)  ][ overflow(x-1, ecosysteme[0][0].length, 0) ] == Cell.ALIVE) { count++; }

		// Y-1 Z-1
		if(ecosysteme[ overflow(z-1, ecosysteme.length, 0) ][ overflow(y-1, ecosysteme[0].length, 0)  ][ overflow(x, ecosysteme[0][0].length, 0) ] == Cell.ALIVE) { count++; }
		if(ecosysteme[ overflow(z-1, ecosysteme.length, 0) ][ overflow(y-1, ecosysteme[0].length, 0)  ][ overflow(x+1, ecosysteme[0][0].length, 0) ] == Cell.ALIVE) { count++; }
		if(ecosysteme[ overflow(z-1, ecosysteme.length, 0) ][ overflow(y-1, ecosysteme[0].length, 0)  ][ overflow(x-1, ecosysteme[0][0].length, 0) ] == Cell.ALIVE) { count++; }

		// Y Z+1
		if(ecosysteme[ overflow(z+1, ecosysteme.length, 0)][ y ][ overflow(x, ecosysteme[0][0].length, 0) ] == Cell.ALIVE) { count++; }
		if(ecosysteme[ overflow(z+1, ecosysteme.length, 0)][ y ][ overflow(x+1, ecosysteme[0][0].length, 0) ] == Cell.ALIVE) { count++; }
		if(ecosysteme[ overflow(z+1, ecosysteme.length, 0)][ y ][ overflow(x-1, ecosysteme[0][0].length, 0) ] == Cell.ALIVE) { count++; }

		// Y Z
		if(ecosysteme[z][ y ][ overflow(x-1, ecosysteme[0][0].length, 0)  ] == Cell.ALIVE) { count++; }
		if(ecosysteme[z][ y ][ overflow(x+1, ecosysteme[0][0].length, 0)  ] == Cell.ALIVE) { count++; }

		// Y Z-1
		if(ecosysteme[ overflow(z-1, ecosysteme.length, 0)  ][ y ][ overflow(x, ecosysteme[0][0].length, 0) ] == Cell.ALIVE) { count++; }
		if(ecosysteme[ overflow(z-1, ecosysteme.length, 0)  ][ y ][ overflow(x+1, ecosysteme[0][0].length, 0) ] == Cell.ALIVE) { count++; }
		if(ecosysteme[ overflow(z-1, ecosysteme.length, 0)  ][ y ][ overflow(x-1, ecosysteme[0][0].length, 0) ]  == Cell.ALIVE) { count++; }

		return count;
	}

	float map(float x, float in_min, float in_max, float out_min, float out_max) {
	  return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
	}

	public static int HSBtoRGB(float hue, float saturation, float brightness) {
        int r = 0, g = 0, b = 0;
        if (saturation == 0) {
            r = g = b = (int) (brightness * 255.0f + 0.5f);
        } else {
            float h = (hue - (float)Math.floor(hue)) * 6.0f;
            float f = h - (float)java.lang.Math.floor(h);
            float p = brightness * (1.0f - saturation);
            float q = brightness * (1.0f - saturation * f);
            float t = brightness * (1.0f - (saturation * (1.0f - f)));
            switch ((int) h) {
            case 0:
                r = (int) (brightness * 255.0f + 0.5f);
                g = (int) (t * 255.0f + 0.5f);
                b = (int) (p * 255.0f + 0.5f);
                break;
            case 1:
                r = (int) (q * 255.0f + 0.5f);
                g = (int) (brightness * 255.0f + 0.5f);
                b = (int) (p * 255.0f + 0.5f);
                break;
            case 2:
                r = (int) (p * 255.0f + 0.5f);
                g = (int) (brightness * 255.0f + 0.5f);
                b = (int) (t * 255.0f + 0.5f);
                break;
            case 3:
                r = (int) (p * 255.0f + 0.5f);
                g = (int) (q * 255.0f + 0.5f);
                b = (int) (brightness * 255.0f + 0.5f);
                break;
            case 4:
                r = (int) (t * 255.0f + 0.5f);
                g = (int) (p * 255.0f + 0.5f);
                b = (int) (brightness * 255.0f + 0.5f);
                break;
            case 5:
                r = (int) (brightness * 255.0f + 0.5f);
                g = (int) (p * 255.0f + 0.5f);
                b = (int) (q * 255.0f + 0.5f);
                break;
            }
        }
        return 0xff000000 | (r << 16) | (g << 8) | (b << 0);
    }

	public void dispEco() {
		StdDraw3D.clear();
		int alive =0;

		for (int z=0; z<ecosysteme.length; z++) {
			for (int y=0; y<ecosysteme[0].length; y++) {
				for (int x=0; x<ecosysteme[0][0].length; x++) {
					if(ecosysteme[z][y][x] == Cell.ALIVE) {
						int v = voisines[z][y][x];
		        		StdDraw3D.setPenColor(Color.getHSBColor(  map((float)v,0.0f,26.0f,0.0f,1.0f)  , 1.0f, 1.0f));
		        		// StdDraw3D.setPenColor(Color.getHSBColor(  map((float)z,0.0f,(float)ecosysteme.length,0.0f,1.0f)  , 1.0f, 1.0f));
						StdDraw3D.sphere(x+0.5,y+0.5,z+0.5,0.4);

	        			alive++;
					} else {
	        			// StdDraw3D.setPenColor(220,220,220);
						// StdDraw3D.sphere(x+0.5,y+0.5,z+0.5,0.2);
					}
				}
			}
		}

        StdDraw3D.show();
	}

}
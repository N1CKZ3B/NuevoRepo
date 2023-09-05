/**
 * Simulates a Replicate form using Hexagons and manipulating them through the ArrayList created
 * 
 * @author Nicolas Achuri, Sofia Gil 
 * @version 1.0 (2 September 2023)
 */

public class Replicate {

    private static Hexagon[][] replicate;
    /**
     * Constructor will provide a matrix in the desired number, and will start iterating with a random position within the Arraylist
     * @param tamaño in desired quantity of rows and columns
     */    
    public Replicate(int tamaño){
        // Crear un canvas para dibujar el replicate
        Canvas canvas = Canvas.getCanvas();
        canvas.setVisible(true);

        Hexagon[][] replicate = new Hexagon[tamaño][tamaño];

        int hexSize = 17; // Tamaño de cada hexágono
        int canvasWidth = 4000;
        int canvasHeight = 4000;

        canvas.setSize(canvasWidth, canvasHeight);

        //INICIALIZADOR DEL PRIMER HEXAGONO
        replicate[0][0] = new Hexagon();
        replicate[0][0].changeSize(hexSize);
        replicate[0][0].setX(15);
        replicate[0][0].setY(15);
        replicate[0][0].makeVisible();
        
        // Se inicializa la primera fila
        for (int c = 1; c < tamaño; c++) {
            replicate[0][c] = new Hexagon();
            replicate[0][c].changeSize(hexSize);
            replicate[0][c].setY(15);
            replicate[0][c].setX(15);
            replicate[0][c].setX(replicate[0][c - 1].getX()+30);
            replicate[0][c].makeVisible();
        }
        // Inicializar las filas restantes
        for (int f = 1; f < tamaño; f++) {
            for (int c = 0; c < tamaño; c++) {
                replicate[f][c] = new Hexagon();
                replicate[f][c].changeSize(hexSize);
                replicate[f][c].setY(15);
                replicate[f][c].setX(15);
                replicate[f][c].setY(replicate[f - 1][c].getY() + 30);
                replicate[f][c].setX(replicate[f - 1][c].getX());
                replicate[f][c].makeVisible();
            }
        }
        int randomRow = (int)(Math.random()*tamaño);
        int randomColumn = (int)(Math.random()*tamaño);
        changeHexagonColor(replicate, randomRow, randomColumn);
        while (true){
        updateHexagons(replicate);
        }
    }
    /**
     * Constructor that allows the user to execute the Arraylist and start in a given position to iterate the replicate program
     * @param tamaño that allows the user to state the length of the ArrayList
     * @param row given a specific position of the ArrayList will start in this row.
     * @param column given a specific position of the ArrayList will start in this column.
    */
    public Replicate (int tamaño, int row, int column){
        // Creamos un canvas para el replicate
        Canvas canvas = Canvas.getCanvas();
        canvas.setVisible(true);

        Hexagon[][] replicate = new Hexagon[tamaño][tamaño];

        int hexSize = 17; // Tamaño de cada hexágono
        int canvasWidth = 4000;
        int canvasHeight = 4000;

        canvas.setSize(canvasWidth, canvasHeight);

        //INICIALIZADOR DEL PRIMER HEXAGONO
        replicate[0][0] = new Hexagon();
        replicate[0][0].changeSize(hexSize);
        replicate[0][0].setX(15);
        replicate[0][0].setY(15);
        replicate[0][0].makeVisible();
        
        // Se inicializa la primera fila
        for (int c = 1; c < tamaño; c++) {
            replicate[0][c] = new Hexagon();
            replicate[0][c].changeSize(hexSize);
            replicate[0][c].setY(15);
            replicate[0][c].setX(15);
            replicate[0][c].setX(replicate[0][c - 1].getX()+30);
            replicate[0][c].makeVisible();
        }
        // Inicializar las filas restantes
        for (int f = 1; f < tamaño; f++) {
            for (int c = 0; c < tamaño; c++) {
                replicate[f][c] = new Hexagon();
                replicate[f][c].changeSize(hexSize);
                replicate[f][c].setY(15);
                replicate[f][c].setX(15);
                replicate[f][c].setY(replicate[f - 1][c].getY() + 30);
                replicate[f][c].setX(replicate[f - 1][c].getX());
                replicate[f][c].makeVisible();
            }
        }
        changeHexagonColor(replicate, row, column);
        while (true){
        updateHexagons(replicate);
        }
    }
    /**
     * Changes the color of the Hexagon given by the row and column
     * 
     * @param ArrayList of Hexagon objects
     * @param f An int which indicates the row of the Hexagon we are looking for.
     * @param c An int which indicates the column of the Hexagon we are looking for
     */
    public static void changeHexagonColor(Hexagon[][] replicate, int f, int c) {
        replicate[f][c].changeColor("green");
    }
    /**
     * We are able to check the state that allow us to know whether we need to change an Hexagon´s color using the replicate logic.
     * @param ArrayList of Hexagon objects
     */
    public static void updateHexagons(Hexagon[][] replicate) {
        
        int dimensionX = replicate.length;
        int dimensionY = replicate[0].length;
    
        for (int i = 0; i < dimensionX; i++) {
            for (int j = 0; j < dimensionY; j++) {
                // Obtener el número de celdas vecinas amarillas
                int greenAmount = countHexagons(replicate, i, j);
    
                // Determinar el nuevo estado de la celda
                if (greenAmount % 2 == 1) {
                    replicate[i][j].changeColor("green");
                } else {
                    replicate[i][j].changeColor("red");
                }
            }
        }
    }
    /**
     * Using it we are able to know the state of the Hexagons that are around the one we are located in.
     * 
     * @param ArrayList of Hexagon objects
     * @param fila The row of the Hexagon we are located in
     * @param columna The column of the Hexagon we are located in
     * 
     */
    public static int countHexagons(Hexagon[][] replicate, int fila, int columna) {
        int dimensionX = replicate.length;
        int dimensionY = replicate[0].length;
        int countTotal = 0;
        int [][] pos = {{-1,-1},{-1,0},{0,-1},{0,1},{1,0},{1,1}};
    
        for (int i = 0; i < 6; i++) {
            int vecinaX = fila + pos[i][0];
            int vecinaY = columna + pos[i][1];
    
            // Verificar si la celda vecina está dentro de los límites
            if (vecinaX >= 0 && vecinaX < dimensionX && vecinaY >= 0 && vecinaY < dimensionY) {
                if (replicate[vecinaX][vecinaY].getColor().equals("green")) {
                    countTotal++;
                }
            }
        }
    
        return countTotal;
    }

}

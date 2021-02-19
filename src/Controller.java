import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class Controller {
    /** Instantiated in FXML **/
    @FXML private GridPane baseGrid;
    @FXML private TextField numOfCols;
    @FXML private TextField numOfRows;

    /** JavaFX elements instantiated here **/
    private GridPane tableGrid;
    private TextField[][] table;

    /** Local variables **/
    private Model model = new Model();
    private int numRows;
    private int numCols;

    /**
     * Creates a table of TextFields for the user to input data,
     * where the # rows/cols is determined by two labelled & permanent TextFields
     *
     * @param actionEvent on "Generate Table"
     */
    public void generateTable(ActionEvent actionEvent) {

        if(tableGrid == null){
            System.out.println("[LaTeX TableMaker] Generating table for user");
        }
        else {
            System.out.println("[LaTeX TableMaker] Re-generating table for user");
            baseGrid.getChildren().remove(tableGrid);
        }

        // Prepare a GridPane to hold the table
        tableGrid = new GridPane();
        tableGrid.setHgap(7);
        tableGrid.setVgap(7);
        tableGrid.setAlignment(Pos.TOP_CENTER);

        // Construct the table of TextFields
        int rows = numRows = Integer.parseInt(numOfRows.getText());
        int cols = numCols = Integer.parseInt(numOfCols.getText());
        table = new TextField[rows][cols];

        // Note the column-major order!
        for(int i = 0; i < cols; i++){
            for(int j = 0; j < rows; j++){
                table[j][i] = new TextField();
                tableGrid.add(table[j][i], i, j);
            }
        }

        baseGrid.add(tableGrid, 1, 0); // (1, 0) = (col, row) in the baseGrid
    }

    /**
     * TODO Decide upon input method & implement it
     *
     * @param actionEvent on "Load Table"
     */
    public void loadTable(ActionEvent actionEvent) {
        System.out.println("[LaTeX TableMaker] Loading exported table - asking user for input..");
        // Do stuff
        System.out.println("[LaTeX TableMaker] Loaded table! Displaying to user");
    }

    /**
     * TODO Get a filename from the user - perhaps ask where to save the file?
     *
     * Takes the values currently in the table and creates a file
     * holding the corresponding LaTeX code.
     *
     * @param actionEvent on "Export to LaTeX"
     */
    public void exportToLatex(ActionEvent actionEvent) throws IOException {
        System.out.println("[LaTeX TableMaker] Converting table to LaTeX code - asking user for filename...");
        String filepath = "path";
        String filename = "name";
        // Do stuff

        String[][] tableContents = new String[numRows][numCols];

        for(int i = 0; i < numCols; i++){
            for(int j = 0; j < numRows; j++){
                String cellContent = table[j][i].getText().equals("") ? "BLNK" : table[j][i].getText();
                tableContents[j][i] = cellContent;
            }
        }

        String table = model.writeLatex(tableContents, numRows, numCols);
        System.out.println(table);
        model.exportLatex(filepath, filename, table);
        // TODO Prompt the user to close the program or make another table
    }
}

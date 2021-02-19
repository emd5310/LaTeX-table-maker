/*
 * @TODO Need to create a separate Model class to handle the table stuff!
 * MVC pattern should only have the control going on here- not the logic/data bits.
 * Passing panes & such by ref will be fine
 *
 * All of this is TODO!
 * 1. Get a LaTeX file being written to a .txt file in an /output directory
 * 2. Add options for the user to change the alignment, yes/no for lines, etc.
 *  2.5 Output will need to be modified accordingly to accommodate this
 * 3. Decide on a method of inputting an existing table
 *  3.5 Implement the chosen method
 * 4. Style sheets! CSS! Make it look nice.
 *  4.5 JavaScript may be needed to handle any fancier stuff.
 * 5. Create ICO files for the program & add them
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.Node;

public class Controller {
    @FXML public TextField numOfCols;
    @FXML public GridPane baseGrid;
    @FXML private TextField numOfRows;

    private GridPane tableGrid;
    private TextField[][] table;

    public void generateTable(ActionEvent actionEvent) {

        // If this has anything in it, it must be a previous table, which needs to go
        if(tableGrid == null){
            System.out.println("[LaTeX TableMaker] Generating table for user");
        }
        else {
            // @TODO Save values between tables? What if it's a smaller table?
            System.out.println("[LaTeX TableMaker] Re-generating table for user");
            baseGrid.getChildren().remove(tableGrid);
        }

        // Prepare a GridPane to hold the table
        tableGrid = new GridPane();
        tableGrid.setHgap(7);
        tableGrid.setVgap(7);
        tableGrid.setAlignment(Pos.TOP_CENTER);

        // Construct the table of TextFields
        int rows = Integer.parseInt(numOfRows.getText());
        int cols = Integer.parseInt(numOfCols.getText());
        table = new TextField[rows][cols];

        // Note the column-major order!
        for(int i = 0; i < cols; i++){
            for(int j = 0; j < rows; j++){
                table[j][i] = new TextField();
                tableGrid.add(table[j][i], i, j);
            }
        }

        baseGrid.add(tableGrid, 1, 0); // i: column, i1: row of the gridpane being added to

    }

    public void loadTable(ActionEvent actionEvent) {
        System.out.println("[LaTeX TableMaker] Loading exported table - asking user for input..");
        // @TODO Ask the user what file they would like to open. Or should it be a copy-paste entry from new window?
        System.out.println("[LaTeX TableMaker] Loaded table! Displaying to user");
        // @TODO Take the parsed LaTeX file and display it
    }

    public void exportToLatex(ActionEvent actionEvent) {
        System.out.println("[LaTeX TableMaker] Converting table to LaTeX code - asking user for filename...");
        // @TODO Get a filename from the user - perhaps ask where to save the file?

        for(Node child : tableGrid.getChildren()){
            String cellContent = ((TextField) child).getText().equals("") ? "blnk" : ((TextField) child).getText();
            System.out.println(cellContent);
        }

        System.out.println("[LaTeX TableMaker] Table converted and saved!");
        // @TODO Prompt the user to close the program or make another table
    }
}

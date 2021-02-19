import java.io.FileWriter;
import java.io.IOException;

public class Model {

    /**
     * Takes the separate values from the table and creates the LaTeX representing the table, in one string.
     * It then passes the result over to exportLatex.
     *
     * @param tableContents individual strings held in each cell
     * @param totalPath to the directory to save to + the filename.txt
     * @param rows # rows in table
     * @param cols # cols in table
     * @throws IOException if exportLatex(..) has an issue writing the file
     */
    public void writeLatex(String[][] tableContents, String totalPath, int rows, int cols) throws IOException {

        String header = "\\begin{center}\n\\begin{tabular}{ " + "|c".repeat(cols) + "| }\n" + "\\hline\n";

        StringBuilder body = new StringBuilder();
        for(int i = 0; i < cols; i++){
            StringBuilder newLine = new StringBuilder();
            for(int j = 0; j < rows; j++){
                newLine.append(tableContents[i][j]);
                if(j == (rows - 1)){
                    newLine.append(" \\\\\n");
                    newLine.append("\\hline\n");
                }
                else{
                    newLine.append(" & ");
                }
            }
            body.append(newLine);
        }

        String footer = """
                \\end{tabular}
                \\end{center}""";

        String table = header + body.toString() + footer;
        System.out.println("[LaTeX TableMaker] LaTeX written! Exporting...");
        exportLatex(totalPath, table);
    }

    /**
     * Exports the written LaTeX to a file for the user to access
     *
     * @param totalPath to the directory to save to + the filename.txt
     * @param table written latex table
     * @throws IOException if it encounters trouble trying to output the file
     */
    public void exportLatex(String totalPath, String table) throws IOException {
        try {
            FileWriter fileWriter = new FileWriter(totalPath);
            fileWriter.write(table);
            fileWriter.close();
            System.out.println("[LaTeX TableMaker] Exported! File saved to " + totalPath);
        }
        catch(IOException e){
            System.out.println("[LaTeX TableMaker] An error occurred while exporting the table! Closing program.");
        }
    }
}

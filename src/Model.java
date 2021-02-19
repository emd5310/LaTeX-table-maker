import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Model {

    public String writeLatex(String[][] tableContents, int rows, int cols){

        // Line across top of table
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

        // Line across bottom of table
        String footer = """
                \\end{tabular}
                \\end{center}""";

        String table = header + body.toString() + footer;
        System.out.println("[LaTeX TableMaker] LaTeX written");
        return table;
    }

    public void exportLatex(String filepath, String filename, String table) throws IOException {
        String path = "output\\" + filename + ".txt";

        try {
            FileWriter fileWriter = new FileWriter(path);
            fileWriter.write(table);
            fileWriter.close();
            System.out.println("[LaTeX TableMaker] Exported!");
        }
        catch(IOException e){
            System.out.println("[LaTeX TableMaker] An error occurred while exporting the table! Closing...");
        }
    }
}

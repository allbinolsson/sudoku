import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class SudokuViewer extends Application {
	
	private int boardSize = 600;
	private TextField[][] fields = new TextField[9][9];
	private Sudoku sudoku = new Sudoku();
	
	
	/**
	 * Get the number entered into a textfield
	 * 
	 * @param x the row in array
	 * @param y the column in array
	 * @return value of specified field or -1 if it is empty
	 */
	private int getField(int x, int y) {
		if(!fields[x][y].getText().isEmpty()) {
			return Integer.parseInt(fields[x][y].getText());
		} else {
			return -1;
		}
	}
	
	/**
	 * Sets the text in a textfield
	 * 
	 * @param x the row in array
	 * @param y the column in array
	 * @param value which wants to be set
	 */
	private void setField(int x, int y, int value) {
		if(x < 9 && y < 9 && value != 0) {
			fields[x][y].setText(Integer.toString(value));			
		} else {
			fields[x][y].setText("");
		}
	}
	
	/**
	 * Send an update to model
	 */
	private void updateSudoku() {
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				if(getField(i, j) != -1) {
					sudoku.setField(i, j, getField(i, j));					
				} else {
					sudoku.setField(i, j, 0);
				}
			}
		}
	}
	
	/**
	 * Updates array from the model
	 */
	private void updateBoard() {
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
					setField(i, j, sudoku.getField(i, j));					
			}
		}
	}

	
	/**
	 * JavaFX start
	 */
	@Override
	public void start(Stage stage) throws Exception {
		HBox root = new HBox(); // Root contains everything in the window
		TilePane board = new TilePane(); //Board Contains the Sudoku board
			
		
		// Modify the visuals of the window
		board.setMinSize(boardSize, boardSize);
		board.setMaxSize(boardSize, boardSize);
		board.setStyle("-fx-background-color: #000000;");
		board.setPrefColumns(3);
		
		root.setAlignment(Pos.CENTER);
		root.setMinHeight(boardSize);
		root.setStyle("-fx-background-color: #00F0F0;");
		
		// Used for creating the areas and textfields within
		TilePane temp;
		OneLetterTextField tf;
		int r;
		int c;
		// Create the 9 areas
		for(int i = 0; i < 9; i++) {
			temp = new TilePane();
			
			// Size of areas
			temp.setMinSize(boardSize / 3, boardSize / 3);
			temp.setMaxSize(boardSize / 3, boardSize / 3);

			temp.setPrefColumns(3);
			
			// Calculate startingposition of first square in area
			if(i % 3 == 0) {
				c = 0;
			} else if(i % 3 == 1) {
				c = 3;
			} else {
				c = 6;
			}
			
			if(i < 3) {
				r = 0;
			} else if(i >= 3 && i < 6) {
				r = 3;
			} else {
				r = 6;
			}
			
			int oldC = c;
			// Textboxes in every group
			for(int j = 0; j < 9; j++) {
				tf = new OneLetterTextField();
				tf.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
				tf.setPrefWidth(boardSize / 9);
				tf.setMinHeight(boardSize / 9);
				if(i % 2 == 0) {
					tf.setStyle("-fx-background-color: #b7b7b7;");
				} else if (i % 2 == 1) {
					tf.setStyle("-fx-background-color: #FFFFFF;");
				}
				

				// Add the textfield to aray
				fields[r][c] = tf;
				c++;
				if(c % 3 == 0) {
					c = oldC;
					r++;
				}
				// Add textfield to area
				temp.getChildren().add(tf);
			}
			// Add area to board
			board.getChildren().add(temp);
		}
		
		Button bSolve = new Button("Solve");
		Button bClear = new Button("Clear");
		
		// Listner for solve button
		bSolve.setOnAction((event) -> {
			updateSudoku();
			if(sudoku.solveSudoku()) {
				updateBoard();	
			} else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Error");
				alert.setContentText("Board is unsolvable!");
				alert.showAndWait();
			}
		});
		
		// Listener for clear button
		bClear.setOnAction((event) -> {
			sudoku.clear();
			updateBoard();
		});
		
		// Add everything to window and show window
		root.getChildren().addAll(board, bSolve, bClear);
		Scene scene = new Scene(root, 1280, 720);
		stage.setScene(scene);
		stage.resizableProperty();
		stage.setMinHeight(boardSize + 45);
		stage.setMinWidth(boardSize);
		stage.setTitle("Soduku");
		stage.show();
	}
	

	/**
	 * Main function
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Application.launch(args);
	}

}

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class SudokuViewer extends Application {
	
	private int boardSize = 600;
	private TextField[][] fields = new TextField[9][9];
	private Sudoku sudoku = new Sudoku();
	

	private int getField(int x, int y) {
		if(!fields[x][y].getText().isEmpty()) {
			return Integer.parseInt(fields[x][y].getText());
		} else {
			return -1;
		}
	}
	
	private void setField(int x, int y, int value) {
		if(x < 9 && y < 9 && value != 0) {
			fields[x][y].setText(Integer.toString(value));			
		} else {
			fields[x][y].setText("");
		}
	}
	
	private void updateSudoku() {
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				if(getField(i, j) != -1) {
					sudoku.setField(i, j, getField(i, j));					
				}
			}
		}
	}
	
	private void updateBoard() {
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
					setField(i, j, sudoku.getField(i, j));					
			}
		}
	}

	@Override
	public void start(Stage stage) throws Exception {
		HBox root = new HBox(); // eller annan komponent
		TilePane board = new TilePane();
		
		
		
		// Skapa ytterligare komponenter och lägg till dem i root
		board.setMinSize(boardSize, boardSize);
		board.setMaxSize(boardSize, boardSize);
		board.setStyle("-fx-background-color: #000000;");
		board.setPrefColumns(3);
		
		root.setAlignment(Pos.CENTER);
		root.setMinHeight(boardSize);
		root.setStyle("-fx-background-color: #00F0F0;");
		
		int group = 0;
		TilePane temp;
		OneLetterTextField tf;
		int r;
		int c;
		// Every loop is one of 9 groups
		for(int i = 0; i < 9; i++) {
			temp = new TilePane();
			temp.setMinSize(boardSize / 3, boardSize / 3);
			temp.setMaxSize(boardSize / 3, boardSize / 3);

			temp.setPrefColumns(3);
			
			//Starting position of text box in a square
			if(group % 3 == 0) {
				c = 0;
			} else if(group % 3 == 1) {
				c = 3;
			} else {
				c = 6;
			}
			
			if(group < 3) {
				r = 0;
			} else if(group >= 3 && group < 6) {
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
				if(group % 2 == 0) {
					tf.setStyle("-fx-background-color: #b7b7b7;");
				} else if (group % 2 == 1) {
					tf.setStyle("-fx-background-color: #FFFFFF;");
				}
				

				
				fields[r][c] = tf;
				c++;
				if(c % 3 == 0) {
					c = oldC;
					r++;
				}
				
				temp.getChildren().add(tf);
			}
			group++;
			board.getChildren().add(temp);
		}
		
		Button bSolve = new Button("Solve");
		Button bClear = new Button("Clear");
		
		bSolve.setOnAction((event) -> {
			updateSudoku();
			sudoku.solveSudoku();
			updateBoard();
		});
		
		bClear.setOnAction((event) -> {
			sudoku.clear();
			updateBoard();
		});
		
		root.getChildren().addAll(board, bSolve, bClear);
		Scene scene = new Scene(root, 1280, 720);
		stage.setScene(scene);
		stage.resizableProperty();
		stage.setMinHeight(boardSize + 45);
		stage.setMinWidth(boardSize);
		stage.setTitle("Soduku");
		stage.show();
	}
	

	public static void main(String[] args) {
		Application.launch(args);
	}

}

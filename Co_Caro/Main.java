package Co_Caro;

import java.util.Scanner;

// Trước tiên tạo lớp Bàn cờ
// Ở đây sẽ dùng bàn cờ 3x3

/* 
 Minh họa

 X | O | X
-----------
 O | X | O
-----------
 X | X | O

 */

class Board {
    int count = 0;
    String board[] = { " ", " ", " ", " ", " ", " ", " ", " ", " " };
    
    // Phương thức in ra bàn cờ
    void printBoard() {
        System.out.println(" " + board[0] + " | " + board[1] + " | " + board[2]);
        // Dùng 11 dấu - 
        System.out.println("-----------");
        System.out.println(" " + board[3] + " | " + board[4] + " | " + board[5]);
        System.out.println("-----------");
        System.out.println(" " + board[6] + " | " + board[7] + " | " + board[8]);
    }

    // Phương thức cập nhật bàn cờ với nước đi từ người chơi nhập vào
    // Trả về true nếu nước đi hơp lệ, false nếu ngược lại
    boolean updateBoard(int position, String type) {
        // Nếu một người chơi lựa chọn vị trí thứ n, chỉ số n - 1 sẽ được cập nhật
        // Nếu vị trí chưa được điền vào => Điền và cập nhật lại bàn cờ
        if (board[position - 1] == " ") {
            board[position - 1] = type;
            count++;
            return true;
        }
        // Nếu vị trí đã được điền, thông báo người chơi chọn vị trí khác
        else {
            System.out.println("Position already selected. Select another position.");
            return false;
        }
    }
    // Nếu tất cả các ô đã được chọn hết thì hòa => return true;
    boolean checkDraw() {
        if (count == 9) return true;
        else return false;
    }
    // Nếu 3 biểu tượng xuất hiện liên tiếp => return True
    boolean checkWinner(String type) {
        // Hàng
        if ((board[0] == type && board[1] == type && board[2] == type) 
        || (board[3] == type && board[4] == type && board[5] == type)
        || (board[6] == type && board[7] == type && board[8] == type)
        // Cột
        || (board[0] == type && board[3] == type && board[6] == type)
        || (board[1] == type && board[4] == type && board[7] == type)
        || (board[2] == type && board[5] == type && board[8] == type)
        // Đường chéo
        || (board[0] == type && board[4] == type && board[8] == type)
        || (board[2] == type && board[4] == type && board[6] == type)) {
            return true;
        }
        else return false;
    }
}

// Tạo lớp người chơi: trong lớp này sẽ nhập tên người chơi và chọn X hoặc O

class Player {
    String name;
    String type;

    Player(String type) {
        this.type = type;
        Scanner input = new Scanner(System.in);
        if (type == "X") {
            System.out.print("Player selecting X, enter your name: ");
            name = input.nextLine();
        }
        else {
            System.out.print("Player selecting O, enter your name: ");
            name = input.nextLine();
        }
    }
}

class Game {
    Board board = new Board();
    Player player1 = new Player("X");
    Player player2 = new Player("O");
    Player currentPlayer = player1;

    // Phương thức play(): Lặp lại việc lấy nước đi từ người chơi hiện tại => Cập nhật bàn cờ => In ra bàn cờ mới => Check thắng, hòa
    void play() {
        Scanner input = new Scanner(System.in);
        String message = "enter the position (1 - 9): ";
        // Sử dụng vòng lặp vô hạn để chạy trò chơi
        while (true) {
            System.out.print(currentPlayer.type + " - " + currentPlayer.name + " " + message);
            int position = input.nextInt();

            if(board.updateBoard(position, currentPlayer.type)) {
                board.printBoard();
                
                // Kiểm tra người thắng sau khi cập nhật lại bàn cờ
                if (board.checkWinner(currentPlayer.type)) {
                    System.out.println(currentPlayer.name + " wins!");
                    break;
                }
                // Kiểm tra hòa 
                else if (board.checkDraw()) {
                    System.out.println("Game is a draw!");
                    break;
                }

                // Thay đổi người chơi khi cập nhật bàn cờ
                if (currentPlayer == player1) {
                    currentPlayer = player2;
                }
                else {
                    currentPlayer = player1;
                }
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        game.play();
    }
}

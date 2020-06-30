package com.codingblocks.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    var player = true;
    var countTurn = 0
    var boardStatus = Array(3){IntArray(3)}
    lateinit var board: Array<Array<Button>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        board = arrayOf(
            arrayOf(button, button1, button2),
            arrayOf(button3, button4, button5),
            arrayOf(button6, button7, button8)
        )
        for(i in board) {
            for (button in i) {
                button.setOnClickListener(this)
            }
        }
        initialiseBoardStatus()
        button9.setOnClickListener {
            player = true
            countTurn = 0
            updateDisplay("Player X Turn")
            initialiseBoardStatus()
        }
    }

    private fun initialiseBoardStatus() {
        for(i in 0..2){
            for (j in 0..2){
                boardStatus[i][j] = -1
            }
        }
        for(i in board){
            for (button in i){
                button.isEnabled = true
                button.text = ""
            }
        }
    }

    override fun onClick(p0: View) {
        when(p0.id){
            R.id.button -> {
                updateValue(row = 0, col = 0, PLAYER = player)
            }
            R.id.button1 -> {
                updateValue(row = 0, col = 1, PLAYER = player)
            }
            R.id.button2 -> {
                updateValue(row = 0, col = 2, PLAYER = player)

            }
            R.id.button3 -> {
                updateValue(row = 1, col = 0, PLAYER = player)

            }
            R.id.button4 -> {
                updateValue(row = 1, col = 1, PLAYER = player)

            }
            R.id.button5 -> {
                updateValue(row = 1, col = 2, PLAYER = player)

            }
            R.id.button6 -> {
                updateValue(row = 2, col = 0, PLAYER = player)

            }
            R.id.button7 -> {
                updateValue(row = 2, col = 1, PLAYER = player)

            }
            R.id.button8 -> {
                updateValue(row = 2, col = 2, PLAYER = player)

            }
        }
        countTurn++
        player = !player
        if(player){
            updateDisplay("Player X turn")
        }
        if (!player){
            updateDisplay("Player O turn")
        }
        val x = checkWinner()
        if(countTurn==9 && !x){
            updateDisplay("Game Draw")
            Toast.makeText(this, "Game Drawn", Toast.LENGTH_SHORT).show()
        }

    }

    private fun checkWinner() : Boolean {
        for(i in 0..2) {
            if (boardStatus[i][0] == boardStatus[i][1] && boardStatus[i][0] == boardStatus[i][2]) {
                if (boardStatus[i][0] == 1) {
                    updateDisplay("Player X Won")
                    Toast.makeText(this, "Player X Won", Toast.LENGTH_SHORT).show()
                    return true
                } else if (boardStatus[i][0] == 0) {
                    updateDisplay("Player O Won")
                    Toast.makeText(this, "Player O Won", Toast.LENGTH_SHORT).show()
                    return true
                }
            }
        }
        for(i in 0..2) {
            if (boardStatus[0][i] == boardStatus[1][i] && boardStatus[0][i] == boardStatus[2][i]) {
                if (boardStatus[0][i] == 1) {
                    updateDisplay("Player X Won")
                    Toast.makeText(this, "Player X Won", Toast.LENGTH_SHORT).show()
                    break
                } else if (boardStatus[0][i] == 0) {
                    updateDisplay("Player O Won")
                    Toast.makeText(this, "Player O Won", Toast.LENGTH_SHORT).show()
                    break
                }
            }
        }
        if(boardStatus[0][0] == boardStatus[1][1] && boardStatus[0][0]==boardStatus[2][2]){
                if(boardStatus[0][0]==1) {
                    updateDisplay("Player X Won")
                    Toast.makeText(this, "Player X Won", Toast.LENGTH_SHORT).show()
                    return true
                }
                else if(boardStatus[0][0]==0){
                    updateDisplay("Player O Won")
                    Toast.makeText(this, "Player O Won", Toast.LENGTH_SHORT).show()
                    return true
                }
            }
        else if(boardStatus[2][0]==boardStatus[1][1] && boardStatus[2][0]==boardStatus[0][2]){
                if(boardStatus[2][0]==1) {
                    updateDisplay("Player X Won")
                    Toast.makeText(this, "Player X Won", Toast.LENGTH_SHORT).show()
                    return true
                }
                else if(boardStatus[2][0]==0){
                    updateDisplay("Player O Won")
                    Toast.makeText(this, "Player O Won", Toast.LENGTH_SHORT).show()
                    return true
                }
            }
        return false
    }

    private fun updateDisplay(text: String) {
        textView2.text = text
        if(text.contains("Won")){
            disableButtons()
        }
    }
    private fun disableButtons(){
        for(i in board){
            for(button in i){
                button.isEnabled = false
            }
        }
    }

    private fun updateValue(row: Int, col: Int, PLAYER: Boolean) {
        val text = if(PLAYER) "X" else "O"
        val value = if(PLAYER) 1 else 0
        board[row][col].apply {
            isEnabled = false
            setText(text)
        }
        boardStatus[row][col] = value
    }
}

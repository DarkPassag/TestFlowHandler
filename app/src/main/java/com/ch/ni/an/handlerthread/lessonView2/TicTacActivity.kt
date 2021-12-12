package com.ch.ni.an.handlerthread.lessonView2


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ch.ni.an.handlerthread.databinding.TicTacActivityBinding

class TicTacActivity: AppCompatActivity() {

    private lateinit var binding :TicTacActivityBinding

    var isFirstPlayer = true

    override fun onCreate(savedInstanceState :Bundle?) {
        binding = TicTacActivityBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.ticTacView.ticTacToeField = TikTacToeField(6,6)

        binding.ticTacView.actionListener = {row, column, field ->
            val cell = field.getCell(row, column)
            if(cell == Cell.EMPTY){
                if(isFirstPlayer){
                    field.setCell(row, column, Cell.PLAYER_1)
                } else field.setCell(row, column, Cell.PLAYER_2)
                isFirstPlayer = !isFirstPlayer
            }
        }
    }


}
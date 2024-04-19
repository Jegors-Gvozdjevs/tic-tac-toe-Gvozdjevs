package com.example.tictactoe_pd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.GridLayout
import android.widget.TextView
import android.widget.Toast
import com.example.tictactoe_pd.databinding.ActivityGameBinding
import com.example.tictactoe_pd.databinding.ActivityMainBinding

class GameActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var binding: ActivityGameBinding
    private var GameModel : GameModel? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val firstPlayer = intent.getStringExtra("PLAYER1_NAME")
        val secondPlayer = intent.getStringExtra("PLAYER2_NAME")


        binding.btn0.setOnClickListener(this)
        binding.btn1.setOnClickListener(this)
        binding.btn2.setOnClickListener(this)
        binding.btn3.setOnClickListener(this)
        binding.btn4.setOnClickListener(this)
        binding.btn5.setOnClickListener(this)
        binding.btn6.setOnClickListener(this)
        binding.btn7.setOnClickListener(this)
        binding.btn8.setOnClickListener(this)

        binding.startGameBtn.setOnClickListener{
            startGame()
        }


        DataFromGame.gameModel.observe(this){
            GameModel = it
            setUI(firstPlayer,secondPlayer)
        }

    }

    fun setUI(firstPlayer: String?, secondPlayer: String?){


        GameModel?.apply{
            binding.btn0.text = filled[0]
            binding.btn1.text = filled[1]
            binding.btn2.text = filled[2]
            binding.btn3.text = filled[3]
            binding.btn4.text = filled[4]
            binding.btn5.text = filled[5]
            binding.btn6.text = filled[6]
            binding.btn7.text = filled[7]
            binding.btn8.text = filled[8]

            binding.startGameBtn.visibility = View.VISIBLE

            binding.gameStatus.text =
                when(gameStat){
                    GameStat.CREATED ->{
                        binding.startGameBtn.visibility = View.INVISIBLE
                        "Game Id :" + gameId

                    }
                    GameStat.JOINED ->{
                        "Click to start!"
                    }
                    GameStat.INPROGRESS ->{
                        binding.startGameBtn.visibility = View.INVISIBLE
                        if(currentPlayer == "X"){
                            "$firstPlayer turn"
                        }
                        else{
                            "$secondPlayer turn"
                        }
                    }
                    GameStat.FINISHED ->{
                        if(winner == "X") {
                            "$firstPlayer won!"
                        }
                        else if(winner == "O"){
                            "$secondPlayer won!"
                        }
                        else{"Draw!"}
                    }
                }
        }
    }




    fun startGame(){
        GameModel?.apply {
            updateData(
                GameModel(
                    gameId = gameId,
                    gameStat = GameStat.INPROGRESS
                )
            )
        }

    }
    fun updateData(model: GameModel){
        DataFromGame.saveGameModel(model)
    }

    fun checkWin(){
        val winPositions = arrayOf(
            intArrayOf(0,1,2),
            intArrayOf(3,4,5),
            intArrayOf(6,7,8),
            intArrayOf(0,3,6),
            intArrayOf(1,4,7),
            intArrayOf(2,5,8),
            intArrayOf(0,4,8),
            intArrayOf(2,4,6)
        )
        GameModel?.apply {
            for(i in winPositions){
                //012
                if(
                    filled[i[0]]==filled[i[1]] &&
                    filled[i[1]]==filled[i[2]] &&
                    filled[i[0]].isNotEmpty()
                        ){
                    gameStat = GameStat.FINISHED
                    winner = filled[i[0]]
                }
            }
            if(filled.none(){it.isEmpty()}){
                gameStat = GameStat.FINISHED
            }
            updateData(this)
        }
    }

    override fun onClick(v: View?) {
        GameModel?.apply {
            if(gameStat!= GameStat.INPROGRESS){
                Toast.makeText(applicationContext, "Game is not started!",Toast.LENGTH_SHORT).show()
                return
            }
            val clicked=(v?.tag as String).toInt()
            if (filled[clicked].isEmpty()){
                filled[clicked] = currentPlayer
                currentPlayer = if(currentPlayer=="X") "O" else "X"
                checkWin()
                updateData(this)
            }
        }

    }
}
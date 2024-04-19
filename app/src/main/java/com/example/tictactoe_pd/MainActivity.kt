package com.example.tictactoe_pd

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import com.example.tictactoe_pd.databinding.ActivityMainBinding
import kotlin.random.Random
import kotlin.random.nextInt

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.playRobotBtn.setOnClickListener{
            robot()
        }

        binding.playOfflineBtn.setOnClickListener{
            createOfflineGame()
        }
        binding.playOnlineBtn.setOnClickListener{
            createOnlineGame()
        }
        binding.joinOnlineBtn.setOnClickListener{
            joinOnlineGame()
        }
    }

    fun createOnlineGame(){
        DataFromGame.FirstID = "X"
        DataFromGame.saveGameModel(
            GameModel(
                gameStat = GameStat.CREATED,
                gameId = Random.nextInt(1000..9999).toString()
            )
        )
        startGame()
    }
    fun joinOnlineGame(){
        //TODO
    }

    fun createOfflineGame(){
        DataFromGame.saveGameModel(
            GameModel(
                gameStat = GameStat.JOINED
            )
        )
        startGame()
    }
    fun robot(){

    }

    fun startGame(){
        startActivity(Intent(this, PlayerNames::class.java))
    }

}
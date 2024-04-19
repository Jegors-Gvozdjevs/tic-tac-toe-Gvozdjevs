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

        //seit mes saucam visas funkcijas lai spelet speliti
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
        //si funkcija nestrada, seit es meiginaju implementet online speli 
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
        //si funkciju es neimplementeju, seit vajadzetu but funkcijai, kas laus spelet ar cilveu, kurs uztaisija play-room
    }

    fun createOfflineGame(){
        //seit mes uzsakam speli 1 pret 1
        DataFromGame.saveGameModel(
            GameModel(
                gameStat = GameStat.JOINED
            )
        )
        startGame()
    }
    fun robot(){
    //seit es nepaspeju implementet random-algoritmu prieks datora, lai mes varetu ar to spelet
    }

    fun startGame(){
        //seit mes nemam intent vertibas no PlayerNames prieks tam, lai varetu redzet musu vardus
        startActivity(Intent(this, PlayerNames::class.java))
    }

}

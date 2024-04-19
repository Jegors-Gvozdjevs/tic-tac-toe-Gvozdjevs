package com.example.tictactoe_pd

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class PlayerNames : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_names)
        val player1EditText: EditText = findViewById(R.id.player_name)
        val player2EditText: EditText = findViewById(R.id.player2_name)
        val button : Button= findViewById(R.id.start_offline_btn)

        button.setOnClickListener {
            val player1Name = player1EditText.text.toString()
            val player2Name = player2EditText.text.toString()

            // putExtra is used to use user entered names in ThirdActivity and to show their names later
            val intent = Intent(this, GameActivity::class.java)
            intent.putExtra("PLAYER1_NAME", player1Name)
            intent.putExtra("PLAYER2_NAME", player2Name)
            startActivity(intent)
        }
    }
}
package com.example.tictactoe_pd

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

object DataFromGame {
    private var GameModel : MutableLiveData<GameModel> = MutableLiveData()
    var gameModel : LiveData<GameModel> = GameModel
    var FirstID = ""


    fun saveGameModel(model : GameModel){
        GameModel.postValue(model)
        if(model.gameId != "-1"){
            val db = Firebase.firestore
            db.collection("games")
                .document(model.gameId)
                .set(model)
            //sita dala nav implementeta lidz galam, jo es esmu pats vainigs ka saku taisit projektu pedeja mirkli un nepaspeju uzzinat vairak par Firebase
            //es seit meiginaju pielagot ONLINE funkciju, un sutit datus uz Firestore database, lai tos varetu izmantot ar gameId palidzibu
        }
    }

}
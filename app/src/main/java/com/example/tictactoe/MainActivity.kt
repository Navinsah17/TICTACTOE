package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import com.example.tictactoe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    enum class Turn{
        Nought,
        Cross
    }
    private var firstTurn=Turn.Cross
    private var currentTurn=Turn.Cross

    private var crossScore = 0
    private var noughtScore = 0


    private var boardlist= mutableListOf<Button>()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initBoard()
    }

    private fun initBoard() {
        boardlist.add(binding.b1)
        boardlist.add(binding.b2)
        boardlist.add(binding.b3)
        boardlist.add(binding.b4)
        boardlist.add(binding.b5)
        boardlist.add(binding.b6)
        boardlist.add(binding.b7)
        boardlist.add(binding.b8)
        boardlist.add(binding.b9)
    }

    fun boardtapped(view: View) {

        if (view !is Button)
            return
        addtoBoard(view)

        if (checkforWin(Nought)){
            noughtScore++
            result("Nought win !")
        }
        if (checkforWin(Cross)){
            crossScore++
            result("Cross win !")
        }

        if (fullboard()){

            result("Draw")

        }
    }

    private fun checkforWin(s: String): Boolean
    {
        if(match(binding.b1,s) && match(binding.b2,s) && match(binding.b3,s))
            return true
        if(match(binding.b4,s) && match(binding.b5,s) && match(binding.b6,s))
            return true
        if(match(binding.b7,s) && match(binding.b8,s) && match(binding.b9,s))
            return true
        if(match(binding.b1,s) && match(binding.b4,s) && match(binding.b7,s))
            return true
        if(match(binding.b2,s) && match(binding.b5,s) && match(binding.b8,s))
            return true
        if(match(binding.b3,s) && match(binding.b6,s) && match(binding.b9,s))
            return true
        if(match(binding.b1,s) && match(binding.b5,s) && match(binding.b9,s))
            return true
        if(match(binding.b3,s) && match(binding.b5,s) && match(binding.b7,s))
            return true

        return false
    }

    private fun match(button: Button, symbol: String)=button.text==symbol

    private fun result(title: String)
    {
        val message="\nNought $noughtScore\n\nCross $crossScore"
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("Reset")
            { _,_ ->
                resetBoard()
            }
            .setCancelable(false)
            .show()


    }

    private fun resetBoard() {

        for (Button in boardlist)
        {
            Button.text=""
        }
        if (firstTurn == Turn.Nought)
            firstTurn =Turn.Nought

        else if (firstTurn == Turn.Cross)
            firstTurn=Turn.Nought

        currentTurn=firstTurn
        SetTurnlabel()

    }

    private fun fullboard(): Boolean {

        for(Button in boardlist)
        {
            if (Button.text=="")
                return false
        }
        return true

    }

    private fun addtoBoard(button: Button) {
        if (button.text != "")
            return
        if (currentTurn == Turn.Nought){

            button.text= Nought
            currentTurn=Turn.Cross
        }
        else if (currentTurn == Turn.Cross){

            button.text= Cross
            currentTurn=Turn.Nought
        }
        SetTurnlabel()
    }

    private fun SetTurnlabel() {
        var turntext=""
        if (currentTurn==Turn.Cross)
            turntext="Turn $Cross"
        else if (currentTurn==Turn.Nought)
            turntext="Turn $Nought"

        binding.turnmsg.text=turntext
    }

    companion object
    {
        const val Nought="0"
        const val Cross="X"
    }
}
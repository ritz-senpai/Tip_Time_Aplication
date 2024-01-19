package com.example.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //make calculate button to do something
        binding.calulateButton.setOnClickListener { calculateTip() }

    }
    //making calculateTip() method to do all the work
    fun calculateTip(){
        //getting editText input and converting it into decimal number and storing it a variable
        val stringInTextField = binding.costOfService.text.toString() //here upto .text a text is stored but we want a string.
        val cost = stringInTextField.toDouble() //1st input --> Cost in decimal
        //now for the second input i.e percentage from radioGroup and radioButton
        val selectedID = binding.tipOptions.checkedRadioButtonId //It saves the id reference of selected button/option inside the variable.
        //now we want numeric percentage corresponding to checked option. To do this we will use when statement
        val tipPercentage = when(selectedID){   //getting numeric percentage
            R.id.option_twenty_percent ->0.20
            R.id.option_eighteen_percent->0.18
            else->0.15
        }
        //now calculating the tip
        var tip = cost*tipPercentage //Note the use of var instead of val.cuz it can change.

        //Handling roundUp switch. We will check if the user chose to round up the value or not(true/false)
        val roundUp = binding.roundUpSwitch.isChecked   //Stores true/false
        //if true then modifying the tip value and storing the roundup value in tip
        if(roundUp) {
            tip = kotlin.math.ceil(tip) //to round up / find the ceiling. You can use the ceil() function to do that.
        }

        //. But the tip amount is a little different—it represents a currency value. To convert tip into currency use following function
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)

        //Final Task. To Display The Tip.
        binding.tipResult.text = formattedTip.toString() //converting formatted tip into string and storing it in text attribute of textView(tipResult)




    }
}
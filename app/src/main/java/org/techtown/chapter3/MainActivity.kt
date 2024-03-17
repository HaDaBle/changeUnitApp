package org.techtown.chapter3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import androidx.core.widget.addTextChangedListener
import org.techtown.chapter3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var cmToM:Boolean=true
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding=ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        var outputTextView=binding.outputTextView
        var outputUnitView=binding.outputUnitView
        var inputTextView=binding.inputTextView
        var inputUnitView=binding.inputUnitView
        val imageButton=binding.imageButton
        var inputNumber:Int=0

        inputTextView.addTextChangedListener{text->
            inputNumber=if(text.isNullOrEmpty()){
                0
            }
            else{
                text.toString().toInt()
            }

            if(cmToM){
                outputTextView.text=inputNumber.times(0.01).toString()
            }
            else{
                outputTextView.text=inputNumber.times(100).toString()
            }
        }
        imageButton.setOnClickListener{
            cmToM= !cmToM
            if(cmToM){
                inputUnitView.text="cm"
                outputUnitView.text="m"
                outputTextView.text=inputNumber.times(0.01).toString()
            }
            else{
                inputUnitView.text="m"
                outputUnitView.text="cm"
                outputTextView.text=inputNumber.times(0.01).toString()

            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean("cmToM",cmToM)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        cmToM=savedInstanceState.getBoolean("cmToM")
        binding.inputUnitView.text=if(cmToM)"cm" else "m"
        binding.outputUnitView.text=if(cmToM)"m" else "cm"
        super.onRestoreInstanceState(savedInstanceState)
    }
}
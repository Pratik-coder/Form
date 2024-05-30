package com.example.ekacareassignment.activity

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.ekacareassignment.R
import com.example.ekacareassignment.databinding.ActivityHomeBinding
import com.example.ekacareassignment.repository.UserRepository
import com.example.ekacareassignment.viewmodel.UserViewModel
import java.util.Calendar

class HomeActivity : AppCompatActivity() {

    private lateinit var binding:ActivityHomeBinding
    private lateinit var userViewModel: UserViewModel
    private var selectedDate:String=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        userViewModel=ViewModelProvider(this).get(UserViewModel::class.java)
        setUI()
    }

    private fun setUI(){
        binding.imageViewBirthDate.setOnClickListener {
            showDatePickerDialog()
        }
        binding.textViewSubmit.setOnClickListener{

            var strUserName=binding.editTextName.text.toString()
            var strUserAge=binding.editTextAge.text.toString()
            var strUserAddress=binding.editTextAddress.text.toString()


            if (strUserName.isEmpty()){
                Toast.makeText(this,"Please Fill the Name",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (strUserAge.isEmpty()){
                Toast.makeText(this,"Please Fill the Age ",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (strUserAddress.isEmpty()){
                Toast.makeText(this,"Please Fill the Address ",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (binding.textViewDate.text!=selectedDate){
                Toast.makeText(this,"Please Enter Date Of Birth",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val calculatedAge=calculateAge(selectedDate)
            if (strUserAge.toInt()!=calculatedAge){
                Toast.makeText(this,"Age does not match with the Date Of Birth",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            //Functionality implemented from ViewModel
            userViewModel.insertData(this, id = 0,strUserName,strUserAge,selectedDate,strUserAddress)
            Toast.makeText(this,getString(R.string.str_success),Toast.LENGTH_SHORT).show()
            Log.d("TAG",strUserName)
            Log.d("TAG",strUserAge)
            Log.d("TAG",strUserAddress)
            Log.d("TAG",selectedDate)
            clearFields()
        }
    }

    //Displaying the DatePicker Dialog for setting the Date
    private fun showDatePickerDialog(){
        val calendar=Calendar.getInstance()
        val year=calendar.get(Calendar.YEAR)
        val month=calendar.get(Calendar.MONTH)
        val date=calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog=DatePickerDialog(this,{_,selectedYear,selectedMonth,selectedDay->
            selectedDate="$selectedYear/${selectedMonth +1}/$selectedDay"
            binding.textViewDate.text=selectedDate
        },year,month,date)
        datePickerDialog.show()
    }

    //Calculating the Age with the Calendar to avoid invalid dates
    private fun calculateAge(dateOfBirth:String):Int{
        val birthDate=Calendar.getInstance()
        val today=Calendar.getInstance()

        val parts=dateOfBirth.split("/")
        val year=parts[0].toInt()
        val monthYear=parts[1].toInt()-1
        val day=parts[2].toInt()


        birthDate.set(year,monthYear,day)


        var age=today.get(Calendar.YEAR)-birthDate.get(Calendar.YEAR)
        if (today.get(Calendar.DAY_OF_YEAR) <birthDate.get(Calendar.DAY_OF_YEAR))
            age--

        return age
    }

    //For Clearing the data after saving it
    private fun clearFields(){
        binding.editTextName.text?.clear()
        binding.editTextAge.text?.clear()
        binding.editTextAddress.text?.clear()
        binding.textViewDate.text="Enter Date Of Birth"
        selectedDate=""
    }
}
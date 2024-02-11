package com.example.gpa_oliveiraa_calculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.graphics.Color;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.text.Editable;
import android.text.TextWatcher;

public class MainActivity extends AppCompatActivity {
    Button calculate_gpa;
    EditText inputOne, inputTwo, inputThree, inputFour, inputFive;
    TextView totalGpa, errorMsg;
    ConstraintLayout color;
    boolean buttonClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputOne = findViewById(R.id.input1);
        inputTwo = findViewById(R.id.input2);
        inputThree = findViewById(R.id.input3);
        inputFour = findViewById(R.id.input4);
        inputFive = findViewById(R.id.input5);
        totalGpa = findViewById(R.id.gpa);
        color = findViewById(R.id.backgroundColor);
        errorMsg = findViewById(R.id.errorMsg);

        calculate_gpa = findViewById(R.id.button);

        calculate_gpa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Do the gpa calculation if button is clicked once
                if (!buttonClicked) {
                    calculateSum();
                    buttonClicked=true; //set button as true so if clicked again the else clause will be used

                } else if (buttonClicked) {
                    //clear the form if button is clicked twice
                    inputOne.setText("");
                    inputTwo.setText("");
                    inputThree.setText("");
                    inputFour.setText("");
                    inputFive.setText("");
                    totalGpa.setText("");
                    calculate_gpa.setText(R.string.calculate_gpa);
                    color.setBackgroundColor(Color.TRANSPARENT);
                    buttonClicked=false;//set button as false so if clicked again the if clause will be used again


                }


            }
        });


    }

    private void calculateSum() {
        //turn grades into string to use isEmpty()
        String gradeOne = inputOne.getText().toString();
        String gradeTwo = inputTwo.getText().toString();
        String gradeThree = inputThree.getText().toString();
        String gradeFour = inputFour.getText().toString();
        String gradeFive = inputFive.getText().toString();
        //Error messages for empty and numbers that are not in range 0-100
        for (String gradeString : new String[]{gradeOne, gradeTwo, gradeThree, gradeFour, gradeFive}) {
            if (gradeString.isEmpty()) {
                errorMsg.setText(R.string.enter_grades);
                return;
            }
            int grade = Integer.parseInt(gradeString);
            if (grade < 0 || grade > 100) {
                errorMsg.setText(R.string.range_error);
                return;
            }
            errorMsg.setText("");
        }
        //Actual calculation to get the average grade
        int totalClass = 5;
        int totalGrade = Integer.parseInt(gradeOne) + Integer.parseInt(gradeTwo) + Integer.parseInt(gradeThree)
                + Integer.parseInt(gradeFour) + Integer.parseInt(gradeFive);
        int result = totalGrade / totalClass;
        calculate_gpa.setText(R.string.clear_form);
        totalGpa.setText(String.valueOf(result));

        // Set background color based on GPA
        if (result <= 60) {
            color.setBackgroundColor(Color.RED);
        } else if (result < 80) {
            color.setBackgroundColor(Color.YELLOW);
        } else {
            color.setBackgroundColor(Color.GREEN);
        }

    }
}



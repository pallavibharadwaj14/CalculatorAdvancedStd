package com.example.calculatorbasicstandard;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView resultText;
    private Calculator calculator;
    private TextView historyTV;
    private Button buttonAdvance;
    private boolean advancedMode = true;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize calculator class
        calculator = new Calculator();

        // Setting button Click Listeners
        setButtonListeners();
        //Initializing
        resultText = findViewById(R.id.resultText);
        historyTV =findViewById(R.id.historyTV);
        buttonAdvance=findViewById(R.id.buttonAdvance);
        buttonAdvance.setOnClickListener(v ->basicStandardMode() );
        buttonAdvance.setText("Advance: With History");
        setButtonListeners();
    }
//Assigning click listners
    private void setButtonListeners()
    {
        // Buttons for digits 0-9
        for (int i = 0; i < 10; i++) {
            // // Construct the button's ID dynamically based on the digit
            String buttonId = "button" + i;
            //Retrieving resource Id for buttons
            int resID = getResources().getIdentifier(buttonId, "id", getPackageName());
            Button button = findViewById(resID);
            final String value = String.valueOf(i);
            button.setOnClickListener(view -> {
                calculator.push(value);
                updateResult();
                updateHistory();
            });
        }

        // Setting click listner for operator button + - * /
        findViewById(R.id.buttonAdd).setOnClickListener(view -> handleOperator("+"));
        findViewById(R.id.buttonSubtract).setOnClickListener(view -> handleOperator("-"));
        findViewById(R.id.buttonMultiply).setOnClickListener(view -> handleOperator("*"));
        findViewById(R.id.buttonDivide).setOnClickListener(view -> handleOperator("/"));

        // Clear button
        findViewById(R.id.buttonClear).setOnClickListener(view -> {
            calculator.clear();
            resultText.setText("0");
            updateHistory();
        });

        // Click Listnerer for Equal button
        findViewById(R.id.buttonEquals).setOnClickListener(view -> {
            int result = calculator.calculate();
            resultText.setText(String.valueOf(result));
            updateHistory();
        });
    }
    //Handle Input Operator
    private void handleOperator(String operator) {
        calculator.push(operator);
        updateResult();
        updateHistory();
    }

    private void updateResult()
    {
// Display the current expression in the resultText
        resultText.setText(calculator.getExpression());
    }

    //Switching between advanced and basic mode
    private void basicStandardMode()
    {
        advancedMode = !advancedMode;
        calculator.clear();
        if(advancedMode)
        {
            resultText.setText("0");
            buttonAdvance.setText("Basic: No history");
            historyTV.setVisibility(View.VISIBLE);
        }
        else {
            resultText.setText("0");
            buttonAdvance.setText("Advance : With History");
            historyTV.setVisibility((View.GONE));
            calculator.clearHistory();
        }
        //Update the calculator mode to reflect the current mode
        calculator.setAdvancedMode(advancedMode);
    }
    //Update History Text View
    private void updateHistory() {
        if (advancedMode)
        {
            historyTV.setVisibility(View.VISIBLE);
      historyTV.setText(calculator.getHistory());
    }
        else
        {
            historyTV.setVisibility(View.GONE);
        }
    }
}
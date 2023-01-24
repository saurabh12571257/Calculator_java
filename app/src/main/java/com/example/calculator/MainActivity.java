package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView upper,lower;
    Button one,two,three,four,five,six,seven,eight,nine,zero,AC,C,add,sub,div,mul,equals,decimal,rightbrac,leftbrac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        upper = findViewById(R.id.Current);
        lower = findViewById(R.id.answer);
        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        four = findViewById(R.id.four);
        five = findViewById(R.id.five);
        six = findViewById(R.id.six);
        seven = findViewById(R.id.seven);
        eight = findViewById(R.id.eight);
        nine = findViewById(R.id.Nine);
        zero = findViewById(R.id.zero);
        equals = findViewById(R.id.equal);
        rightbrac = findViewById(R.id.Rightbrac);
        leftbrac = findViewById(R.id.leftbrac);
        div = findViewById(R.id.divide);
        mul = findViewById(R.id.multiply);
        sub = findViewById(R.id.subtract);
        decimal = findViewById(R.id.decimal);
        add = findViewById(R.id.add);
        C = findViewById(R.id.C);
        AC = findViewById(R.id.AC);


        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lower.setText(lower.getText() + "1");
            }
        });
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lower.setText(lower.getText() + "2");
            }
        });
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lower.setText(lower.getText() + "3");
            }
        });
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lower.setText(lower.getText() + "4");
            }
        });
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lower.setText(lower.getText() + "5");
            }
        });
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lower.setText(lower.getText() + "6");
            }
        });
        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ;lower.setText(lower.getText() + "7");
            }
        });
        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lower.setText(lower.getText() + "8");
            }
        });
        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lower.setText(lower.getText() + "9");
            }
        });
        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lower.setText(lower.getText() + "0");
            }
        });

            div.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    lower.setText(lower.getText() + "/");

                }
            });

            mul.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    lower.setText(lower.getText() + "*");
                }
            });
            sub.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    lower.setText(lower.getText() + "-");
                }
            });
            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    lower.setText(lower.getText() + "+");

                }
            });
            rightbrac.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    lower.setText(lower.getText() + ")");
                }
            });
            leftbrac.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    lower.setText(lower.getText() + "(");
                }
            });
            decimal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    lower.setText(lower.getText() + ".");
                }
            });
                equals.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String val = lower.getText().toString();
                        if(!lower.getText().toString().equals("")) {
                        double result = eval(val);
                            lower.setText(String.valueOf(result));
                            upper.setText(val);
                        }
                    }
                });
        AC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upper.setText("");
                lower.setText("");
            }
        });
        C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String val = lower.getText().toString();
                if(val.length()!=0) {
                    val = val.substring(0, val.length() - 1);
                }
                lower.setText(val);
            }
        });
    }
    //eval function
    public static double eval(final String str) {
            return new Object() {
                int pos = -1, ch;

                void nextChar() {
                    ch = (++pos < str.length()) ? str.charAt(pos) : -1;
                }

                boolean eat(int charToEat) {
                    while (ch == ' ') nextChar();
                    if (ch == charToEat) {
                        nextChar();
                        return true;
                    }
                    return false;
                }

                double parse() {
                    nextChar();
                    double x = parseExpression();
                    if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char) ch);
                    return x;
                }

                // Grammar:
                // expression = term | expression `+` term | expression `-` term
                // term = factor | term `*` factor | term `/` factor
                // factor = `+` factor | `-` factor | `(` expression `)`
                //        | number | functionName factor | factor `^` factor

                double parseExpression() {
                    double x = parseTerm();
                    for (; ; ) {
                        if (eat('+')) x += parseTerm(); // addition
                        else if (eat('-')) x -= parseTerm(); // subtraction
                        else return x;
                    }
                }

                double parseTerm() {
                    double x = parseFactor();
                    for (; ; ) {
                        if (eat('*')) x *= parseFactor(); // multiplication
                        else if (eat('/')) x /= parseFactor(); // division
                        else return x;
                    }
                }

                double parseFactor() {
                    if (eat('+')) return parseFactor(); // unary plus
                    if (eat('-')) return -parseFactor(); // unary minus

                    double x;
                    int startPos = this.pos;
                    if (eat('(')) { // parentheses
                        x = parseExpression();
                        eat(')');
                    } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                        while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                        x = Double.parseDouble(str.substring(startPos, this.pos));
                    } else {
                        throw new RuntimeException("Unexpected: " + (char) ch);
                    }

                    return x;
                }
            }.parse();

    }
}

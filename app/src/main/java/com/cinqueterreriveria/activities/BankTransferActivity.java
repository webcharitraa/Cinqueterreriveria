package com.cinqueterreriveria.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cinqueterreriveria.R;
import com.cinqueterreriveria.apis.ApiConstents;
import com.cinqueterreriveria.apis.Rest;
import com.cinqueterreriveria.common.PrefStore;
import com.cinqueterreriveria.models.PaymentModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BankTransferActivity extends AppCompatActivity {

    TextView tv_app_bar_title, tv_Sepa, tv_credit;
    EditText et_iBan, et_card_number, et_expiry, et_bank_first_name, et_bank_last_name,
            et_bank_mobile, et_email, et_address, et_address2, et_bank_city, et_bank_state, et_bank_Zip, et_bank_country, et_bank_name, et_bank_email, et_cvv;
    LinearLayout ll_payment_info, ll_card_detail, ll_iBan, ll_note;
    Intent intent;
    Button tv_bank_submit;
    PrefStore prefStore;
    Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_transfer);

        initUis();
    }

    private void initUis() {
        intent = getIntent();
        prefStore = new PrefStore(context);
       // Log.e("TAG DATa", intent.getStringExtra("property_price"));
        tv_app_bar_title = findViewById(R.id.tv_app_bar_title);
        et_iBan = findViewById(R.id.et_iBan);
        et_card_number = findViewById(R.id.et_card_number);
        tv_Sepa = findViewById(R.id.tv_Sepa);
        tv_credit = findViewById(R.id.tv_credit);
        ll_payment_info = findViewById(R.id.ll_payment_info);
        ll_card_detail = findViewById(R.id.ll_card_detail);
        ll_iBan = findViewById(R.id.ll_iBan);
        ll_note = findViewById(R.id.ll_note);
        et_expiry = findViewById(R.id.et_expiry);
        tv_bank_submit = findViewById(R.id.tv_bank_submit);
        et_bank_last_name = findViewById(R.id.et_bank_last_name);
        et_bank_first_name = findViewById(R.id.et_bank_first_name);
        et_bank_mobile = findViewById(R.id.et_bank_mobile);
        et_email = findViewById(R.id.et_email);
        et_address = findViewById(R.id.et_address);
        et_address2 = findViewById(R.id.et_address2);
        et_bank_city = findViewById(R.id.et_bank_city);
        et_bank_state = findViewById(R.id.et_bank_state);
        et_bank_Zip = findViewById(R.id.et_bank_Zip);
        et_bank_country = findViewById(R.id.et_bank_country);
        et_bank_name = findViewById(R.id.et_bank_name);
        et_bank_email = findViewById(R.id.et_bank_email);
        et_card_number = findViewById(R.id.et_card_number);
        et_cvv = findViewById(R.id.et_cvv);

        tv_app_bar_title.setVisibility(View.VISIBLE);
        tv_app_bar_title.setText("Instant Online Booking");

        tv_bank_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* if (!validate(context,et_iBan))
                {
                    et_iBan.setError("Not Valid IBAN");
                }*/

                validationsChcekForCard();
            }
        });


        if (intent.getStringExtra("flag").equals("wire_tranfer")) {
            tv_Sepa.setVisibility(View.VISIBLE);
            tv_credit.setVisibility(View.GONE);
            ll_payment_info.setVisibility(View.VISIBLE);
            ll_card_detail.setVisibility(View.GONE);
            ll_iBan.setVisibility(View.VISIBLE);
            ll_note.setVisibility(View.VISIBLE);
        }
        if (intent.getStringExtra("flag").equals("credit/debit")) {
            tv_Sepa.setVisibility(View.GONE);
            ll_payment_info.setVisibility(View.GONE);
            tv_credit.setVisibility(View.VISIBLE);
            ll_card_detail.setVisibility(View.VISIBLE);
            ll_iBan.setVisibility(View.GONE);
            ll_note.setVisibility(View.GONE);
        }

        et_card_number.addTextChangedListener(new TextWatcher() {

            private static final int TOTAL_SYMBOLS = 19; // size of pattern 0000-0000-0000-0000
            private static final int TOTAL_DIGITS = 16; // max numbers of digits in pattern: 0000 x 4
            private static final int DIVIDER_MODULO = 5; // means divider position is every 5th symbol beginning with 1
            private static final int DIVIDER_POSITION = DIVIDER_MODULO - 1; // means divider position is every 4th symbol beginning with 0
            private static final char DIVIDER = ' ';

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // noop
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // noop
            }

            @Override
            public void afterTextChanged(Editable s) {
               /* if (!isInputCorrect(s, TOTAL_SYMBOLS, DIVIDER_MODULO, DIVIDER)) {
                    s.replace(0, s.length(), buildCorrectString(getDigitArray(s, TOTAL_DIGITS), DIVIDER_POSITION, DIVIDER));
                }*/
            }

            private boolean isInputCorrect(Editable s, int totalSymbols, int dividerModulo, char divider) {
                boolean isCorrect = s.length() <= totalSymbols; // check size of entered string
                for (int i = 0; i < s.length(); i++) { // check that every element is right
                    if (i > 0 && (i + 1) % dividerModulo == 0) {
                        isCorrect &= divider == s.charAt(i);
                    } else {
                        isCorrect &= Character.isDigit(s.charAt(i));
                    }
                }
                return isCorrect;
            }

            private String buildCorrectString(char[] digits, int dividerPosition, char divider) {
                final StringBuilder formatted = new StringBuilder();

                for (int i = 0; i < digits.length; i++) {
                    if (digits[i] != 0) {
                        formatted.append(digits[i]);
                        if ((i > 0) && (i < (digits.length - 1)) && (((i + 1) % dividerPosition) == 0)) {
                            formatted.append(divider);
                        }
                    }
                }

                return formatted.toString();
            }

            private char[] getDigitArray(final Editable s, final int size) {
                char[] digits = new char[size];
                int index = 0;
                for (int i = 0; i < s.length() && index < size; i++) {
                    char current = s.charAt(i);
                    if (Character.isDigit(current)) {
                        digits[index] = current;
                        index++;
                    }
                }
                return digits;
            }
        });
        et_expiry.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int removed, int added) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    formatCardExpiringDate(s);
                } catch (NumberFormatException e) {
                    s.clear();
                    //Toast message here.. Wrong date formate

                }
            }
        });
        et_iBan.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                //validate(context,et_iBan);
            }
        });

    }


    private static boolean validateIsEmpty(String field) {
        return TextUtils.isEmpty(field);
    }

    private static final int IBAN_MIN_SIZE = 15;
    private static final int IBAN_MAX_SIZE = 34;
    private static final long IBAN_MAX = 999999999;
    private static final long IBAN_MODULUS = 97;

    public static boolean validateIban(String iban) {
        String trimmed = iban.trim();
        if (trimmed.length() < IBAN_MIN_SIZE
                || trimmed.length() > IBAN_MAX_SIZE) {
            return false;
        }
        String reformat = trimmed.substring(4) + trimmed.substring(0, 4);
        long total = 0;
        for (int i = 0; i < reformat.length(); i++) {
            int charValue = Character.getNumericValue(reformat.charAt(i));
            if (charValue < 0 || charValue > 35) {
                return false;
            }
            total = (charValue > 9 ? total * 100 : total * 10) + charValue;
            if (total > IBAN_MAX) {
                total = (total % IBAN_MODULUS);
            }
        }
        return (total % IBAN_MODULUS) == 1;
    }

    private static boolean validateBic(String bic) {
        if (bic == null) {
            return false;
        } else {
            return bic.length() == 7 || bic.length() == 11;
        }
    }

    public static boolean validate(Context context,
                                   EditText iban) {
        boolean valid = true;
        //valid = validateName(context, name) && valid;
        valid = validateIban(context, iban) && valid;
        //valid = validateBic(context, bic) && valid;
        return valid;
    }

    public static boolean validateName(Context context, EditText name) {
        name.setError(null);
        if (validateIsEmpty(name.getText().toString())) {
            name.setError("Not Valid name");
            return false;
        }
        return true;
    }

    public static boolean validateIban(Context context, EditText iban) {
        iban.setError(null);
        if (!validateIban(iban.getText().toString().replaceAll("\\s", ""))) {
            iban.setError("Not valid IBAN ");
            return false;
        } else {
            Toast.makeText(context, "VALid", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    public static boolean validateBic(Context context, EditText bic) {
        bic.setError(null);
        if (!validateBic(bic.getText().toString())) {
            bic.setError("Not valid bic");
            return false;
        }
        return true;
    }


    boolean isSlash = false; //class level initialization

    private void formatCardExpiringDate(Editable s) {
        String input = s.toString();
        String mLastInput = "";

        SimpleDateFormat formatter = new SimpleDateFormat("MM/yy", Locale.ENGLISH);
        Calendar expiryDateDate = Calendar.getInstance();

        try {
            expiryDateDate.setTime(formatter.parse(input));
        } catch (java.text.ParseException e) {
            if (s.length() == 2 && !mLastInput.endsWith("/") && isSlash) {
                isSlash = false;
                int month = Integer.parseInt(input);
                if (month <= 12) {
                    et_expiry.setText(et_expiry.getText().toString().substring(0, 1));
                    et_expiry.setSelection(et_expiry.getText().toString().length());
                } else {
                    s.clear();
                    et_expiry.setText("");
                    et_expiry.setSelection(et_expiry.getText().toString().length());
                    Toast.makeText(getApplicationContext(), "Enter a valid month", Toast.LENGTH_LONG).show();
                }
            } else if (s.length() == 2 && !mLastInput.endsWith("/") && !isSlash) {
                isSlash = true;
                int month = Integer.parseInt(input);
                if (month <= 12) {
                    et_expiry.setText(et_expiry.getText().toString() + "/");
                    et_expiry.setSelection(et_expiry.getText().toString().length());
                } else if (month > 12) {
                    et_expiry.setText("");
                    et_expiry.setSelection(et_expiry.getText().toString().length());
                    s.clear();

                }


            } else if (s.length() == 1) {

                int month = Integer.parseInt(input);
                if (month > 1 && month < 12) {
                    isSlash = true;
                    et_expiry.setText("0" + et_expiry.getText().toString() + "/");
                    et_expiry.setSelection(et_expiry.getText().toString().length());
                }
            }

            mLastInput = et_expiry.getText().toString();
            return;
        }
    }

    private void validationsChcekForCard() {
        if (et_bank_first_name.getText().toString().isEmpty()) {
            Toast.makeText(context, "Please Enter First name", Toast.LENGTH_SHORT).show();
        } else if (et_bank_last_name.getText().toString().isEmpty()) {
            Toast.makeText(context, "Please Enter Last name", Toast.LENGTH_SHORT).show();
        } else if (et_bank_mobile.getText().toString().isEmpty()) {
            Toast.makeText(context, "Please Enter Contact no.", Toast.LENGTH_SHORT).show();
        } else if (et_email.getText().toString().isEmpty()) {
            Toast.makeText(context, "Please Enter Email Address", Toast.LENGTH_SHORT).show();
        } else if (et_address.getText().toString().isEmpty()) {
            Toast.makeText(context, "Please Enter Address", Toast.LENGTH_SHORT).show();
        } else if (et_address2.getText().toString().isEmpty()) {
            Toast.makeText(context, "Please Enter Address", Toast.LENGTH_SHORT).show();
        } else if (et_bank_city.getText().toString().isEmpty()) {
            Toast.makeText(context, "Please Enter City", Toast.LENGTH_SHORT).show();
        } else if (et_bank_state.getText().toString().isEmpty()) {
            Toast.makeText(context, "Please Enter State", Toast.LENGTH_SHORT).show();
        } else if (et_bank_Zip.getText().toString().isEmpty()) {
            Toast.makeText(context, "Please Enter Zip", Toast.LENGTH_SHORT).show();
        } else if (et_bank_country.getText().toString().isEmpty()) {
            Toast.makeText(context, "Please Enter Country", Toast.LENGTH_SHORT).show();
        }/*else if (et_bank_name.getText().toString().isEmpty())
        {
            Toast.makeText(context, "Please Enter Name", Toast.LENGTH_SHORT).show();
        }else if (et_bank_email.getText().toString().isEmpty())
        {
            Toast.makeText(context, "Please Enter Email", Toast.LENGTH_SHORT).show();
        }*/ else if (et_card_number.getText().toString().length() < 16) {
            Toast.makeText(context, "Please Enter valid Card Number", Toast.LENGTH_SHORT).show();
        } else if (et_expiry.getText().toString().isEmpty()) {
            Toast.makeText(context, "Please Enter expiry date", Toast.LENGTH_SHORT).show();
        } else if (et_cvv.getText().toString().isEmpty()) {
            Toast.makeText(context, "Please Enter CVV", Toast.LENGTH_SHORT).show();
        } else {
            paymentWithCard();
        }
    }


    private void paymentWithCard() {
        String[] separated = et_expiry.getText().toString().split("/");
        String month = separated[0]; // this will contain "Fruit"
        String year = separated[1]; // this will contain " they taste good"
        Call<PaymentModel> call = Rest.getRetrofit().doPayment(
                ApiConstents.SECRET_KEY,
                prefStore.getString("property_price"),
                prefStore.getString("property_name"),
                et_bank_first_name.getText().toString(),
                et_bank_last_name.getText().toString(),
                et_bank_email.getText().toString(),
                et_bank_mobile.getText().toString(),
                "",
                et_address.getText().toString(),
                et_address2.getText().toString(),
                et_bank_city.getText().toString(),
                et_bank_state.getText().toString(),
                et_bank_Zip.getText().toString(),
                et_bank_country.getText().toString(),
                prefStore.getString("no_of_persons"),
                prefStore.getString("start_date"),
                "",
                prefStore.getString("end_date"),
                prefStore.getString("payment_type"),
                prefStore.getString("booking_price"),
                prefStore.getString("client_price"),
                prefStore.getString("remaining_amount"),
                prefStore.getString("cleaningPrice"),
                prefStore.getString("nights"),
                et_card_number.getText().toString(),
                month,
                year,
                et_cvv.getText().toString());

        call.enqueue(new Callback<PaymentModel>() {
            @Override
            public void onResponse(Call<PaymentModel> call, Response<PaymentModel> response) {
                if (response.isSuccessful())
                {
                    if (response.body().getSuccess().equals("true"))
                    {
                        Toast.makeText(BankTransferActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(BankTransferActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }
            }

            @Override
            public void onFailure(Call<PaymentModel> call, Throwable t) {
                Toast.makeText(BankTransferActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }


}

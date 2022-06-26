package com.lega.practica101.fragment;

import static com.lega.practica101.repository.constans.KEY_NAME;
import static com.lega.practica101.repository.constans.KEY_SURNAME;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;

import com.lega.practica101.HomeActivity;
import com.lega.practica101.R;
import com.lega.practica101.databinding.FragmentLoginBinding;
import com.lega.practica101.repository.User;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;
    private int REQUEST_CODE_OBJECT_ACTIVITY=100;
    private int contador = 0;
    private static String name = "";
    private static String surname = "";
    private static final String KEY_DATA = "data" ;
    private NavController navController;
    private AppBarConfiguration appBarConfiguration;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.ALImage.setImageResource(R.drawable.city);

        if (getArguments() != null) {
            name = getArguments().getString(KEY_NAME);
            surname = getArguments().getString(KEY_SURNAME);
        }

        listener();
    }

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    public static Fragment newInstance(User user){
        LoginFragment fragment = new LoginFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_DATA, user);
        fragment.setArguments(bundle);
        if(user != null){
            name = user.getUser();
            surname = user.getSurName();
        }

        return fragment;
    }

    private void listener() {
        binding.ALUsernameText.setOnFocusChangeListener((view, b) -> {
            if(binding.ALUsernameText.getText().toString() != null && binding.ALUsernameText.getText().toString().length() > 0) {
                contador +=1 ;
            }else{
                contador = 0 ;
            }

            if(contador > 1){
                binding.ALButtonAccess.setEnabled(true);
            }else{
                binding.ALButtonAccess.setEnabled(false);
            }
        });

        binding.ALPassword.setOnFocusChangeListener((view, b) -> {
            if(binding.ALPasswordText.getText().toString() != null && binding.ALPasswordText.getText().toString().length() > 0) {
                contador +=1 ;
            }else{
                contador = 0 ;
            }

            if(contador > 1){
                binding.ALButtonAccess.setEnabled(true);
            }else{
                binding.ALButtonAccess.setEnabled(false);
            }
        });

        binding.ALButtonAccess.setOnClickListener(view -> {
            if(name.toString() ==null && name.toString().length()==0 && surname.toString() ==null && surname.toString().length()==0){
                Toast.makeText(getContext(), "Debes Ingresar Usuario y Password", Toast.LENGTH_SHORT).show();
            }else{
                if(name.toString().equals(binding.ALUsernameText.getText().toString())){
                    if(surname.toString().equals(binding.ALPasswordText.getText().toString())){

                        Intent intent = new Intent(getContext(), HomeActivity.class);
                        intent.putExtra(KEY_NAME, binding.ALUsernameText.getText().toString());
                        intent.putExtra(KEY_SURNAME, binding.ALPasswordText.getText().toString());
                        startActivity(intent);
                    }else{
                        AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                        alert.setTitle("Datos Incorrectos");
                        alert.setMessage("Revise su Password, Dato Incorrecto!!!")
                                .setPositiveButton("Entendido", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Toast.makeText(getContext(), "Password Incorrecto", Toast.LENGTH_SHORT).show();
                                    }
                                }).show();
                    }
                }else{
                    AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                    alert.setTitle("Datos Incorrectos");
                    alert.setMessage("Revise su Usuario, Dato Incorrecto!!!")
                            .setPositiveButton("Entendido", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Toast.makeText(getContext(), "Usuario Incorrecto", Toast.LENGTH_SHORT).show();
                                }
                            }).show();
                }
            }
        });

        binding.ALTxtCreate.setOnClickListener(view -> {
            Navigation.findNavController(view).navigate(R.id.registerFragment);
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }



}
package com.lega.practica101.fragment;

import static com.lega.practica101.repository.constans.KEY_NAME;
import static com.lega.practica101.repository.constans.KEY_SURNAME;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.lega.practica101.R;
import com.lega.practica101.databinding.FragmentRegisterBinding;

public class RegisterFragment extends Fragment {

    private FragmentRegisterBinding binding;
    private int contador =0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentRegisterBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.RAImage.setImageResource(R.drawable.imagen);

        binding.toolbar.setNavigationOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.loginFragment);
        });

        binding.RAImage.setImageResource(R.drawable.imagen);
        binding.RAImageCamara.setImageResource(R.drawable.ic_camara_foreground);

        dropdownBinding();

        listener();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    private void listener() {

        binding.RANameText.setOnFocusChangeListener((view, b) -> {
            if (binding.RANameText.getText().toString() != null && binding.RANameText.getText().toString().length() > 0){
                if(binding.RANameText.getText().toString().contains("@") || binding.RANameText.getText().toString().contains("!")) {
                    binding.RANameText.setError("Ups, no creo que sea correcto, resviselo");
                    contador = 0;
                }
                contador +=1 ;
                if(contador > 1){
                    binding.RABtnAction.setEnabled(true);
                }
            }else{
                binding.RABtnAction.setEnabled(false);
            }
        });

        binding.RASurnameText.setOnFocusChangeListener((view, b) -> {
            if(binding.RASurnameText.getText().toString() != null && binding.RASurnameText.getText().toString().length() > 0) {
                if(binding.RASurnameText.getText().toString().contains("@") || binding.RASurnameText.getText().toString().contains("!")) {
                    binding.RASurnameText.setError("Ups, no creo que sea correcto, resviselo");
                    contador = 0;
                }
                contador +=1 ;
                if(contador > 1){
                    binding.RABtnAction.setEnabled(true);
                }else{
                    binding.RABtnAction.setEnabled(false);
                }
            }
        });

        binding.RAAutocomplete.setOnItemClickListener((adapterView, view, i, l) -> {
            String optionSelected = "";
            if (String.valueOf(adapterView.getItemAtPosition(i)).equals("0-5")) {
                Log.e("lega", "1");
                binding.RADrop.setError("Esto no es para ti");
            }else  if (String.valueOf(adapterView.getItemAtPosition(i)).equals("6-11")) {
                Log.e("lega", "2");
                binding.RADrop.setError("Esto no es para ti");
            }else if (String.valueOf(adapterView.getItemAtPosition(i)).equals("12-17")) {
                Log.e("lega", "3");
                binding.RADrop.setError("Esto no es para ti");
            }else{
                binding.RADrop.setError(null);
            }
        });

        binding.RABtnAction.setOnClickListener(view -> {
            if(binding.RANameText.getText().length() > 0 && binding.RANameText.getText() != null && binding.RASurnameText.getText().length() > 0 && binding.RASurnameText.getText() != null){

                Navigation.findNavController(view).navigate(R.id.to_loginFragment, getUser());

            }else{
                Toast.makeText(getContext(), "Debes Llenar todos los datos solicitados.", Toast.LENGTH_SHORT).show();
            }
        });


        binding.RATxtCondiciones.setOnClickListener(view -> openWeb("https://developers.google.com/ml-kit/terms"));

        binding.RAImageCamara.setOnClickListener(view -> capturePhoto());
    }

    private Bundle getUser() {
        String name = binding.RANameText.getText().toString();
        String surname = binding.RASurnameText.getText().toString();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_NAME, name);
        bundle.putString(KEY_SURNAME, surname);
        return bundle;
    }

    private void dropdownBinding() {
        String []  listFruits = getResources().getStringArray(R.array.ListOption);
        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, listFruits);
        binding.RAAutocomplete.setAdapter(adapter);
    }

    public void openWeb(String url){
        Uri web= Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW,web);
        startActivity(intent);
    }

    public void capturePhoto() {
        Intent intent = new Intent(MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA);
        startActivityForResult(intent, 12);
    }
}
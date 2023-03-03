package com.example.loginregisterthreading;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class UserAccount {
    final String TAG = "FIRESTORE";
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public void addUser(String FName, String LName, String UName, String Pass, String CPass){
        Map<String,Object> user = new HashMap<String,Object>(){{
            put("FirstName", FName);
            put("LastName", LName);
            put("Username", UName);
            put("Password", Pass);
            put("ConfirmPass", CPass);
        }};
        db.collection("Accounts").document(UName)
                .set(user)
                .addOnSuccessListener(unused -> Log.d(TAG,"DocumentSnapshot added with ID: " + UName))
                .addOnFailureListener(e -> Log.d(TAG, "Error adding document", e));
    }
    public void searchUser(String user, String pass, Context context)
    {
        db.collection("Accounts")
                .document(user)
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    String queriedPassword = documentSnapshot.getString("Password");
                    Log.d(TAG, "Data read from Firestore: " + documentSnapshot.getData());
                    if(queriedPassword == null) {
                        Toast.makeText(context,"User does not exist", Toast.LENGTH_SHORT).show();
                    }else if(!queriedPassword.equals(pass)) {
                        Toast.makeText(context,"Incorrect password", Toast.LENGTH_SHORT).show();
                    }else {
                        Intent intent = new Intent(context,Threading.class);
                        context.startActivity(intent);
                    }
                })
                .addOnFailureListener(e -> Log.d(TAG, "Error reading data from Firestore: " + e.getMessage()));
    }
}

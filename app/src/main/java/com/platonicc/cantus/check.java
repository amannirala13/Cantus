package com.platonicc.cantus;

import com.google.firebase.auth.FirebaseAuth;

public class check {

    public boolean isValidUser(){
        return FirebaseAuth.getInstance().getCurrentUser() != null ;
    }
}

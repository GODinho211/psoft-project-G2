package com.example.projetopsoft2024.models;

public enum RoleUser {
    LIBRARIAN("librarian"),
    READER("reader");

    private String role;

     RoleUser(String role){
        this.role=role;
    }

    public String getRole(){
         return role;
    }

}

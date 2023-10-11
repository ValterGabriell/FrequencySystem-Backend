package io.github.ValterGabriell.FrequenciaAlunos.mapper.admin;

import io.github.ValterGabriell.FrequenciaAlunos.domain.admins.Admin;

import java.util.UUID;

public class CreateNewAdmin {

    private String username;
    private String password;
    private String email;
    private String cnpj;


    public CreateNewAdmin(String username, String password, String email, String cnpj) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.cnpj = cnpj;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Admin toAdmin() {
        return new Admin(
                UUID.randomUUID().toString(),
                this.username,
                this.password,
                this.email,
                this.cnpj
        );
    }
}
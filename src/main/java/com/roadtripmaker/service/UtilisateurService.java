package com.roadtripmaker.service;

import com.roadtripmaker.domain.Role;
import com.roadtripmaker.domain.Utilisateur;

import java.util.List;

public interface UtilisateurService {
    Utilisateur signUp(Utilisateur utilisateur);

    Utilisateur getUtilisateur(String mail);

    List<Utilisateur> getUtilisateurs();

    Role addRole(Role role);

    void assignRoleToAnUser(String mail, String nomRole);
}
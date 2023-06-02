package com.ustb.evaluation.mod02infrastructure.domain;

import org.springframework.security.core.GrantedAuthority;

public class Permit implements GrantedAuthority {
    String permit;
    public Permit(String permit){
        this.permit=permit;
    }
    @Override
    public String getAuthority() {
        return permit;
    }
}

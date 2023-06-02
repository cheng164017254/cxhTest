package com.ustb.evaluation.mod01common.domain;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

/**
 * 权限封装
 */
@Data
@AllArgsConstructor
public class GrantedAuthorityImpl implements GrantedAuthority {
	private String authority;

    @Override
    public String getAuthority() {
        return this.authority;
    }
}
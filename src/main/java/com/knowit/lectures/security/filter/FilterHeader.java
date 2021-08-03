package com.knowit.lectures.security.filter;

import com.knowit.lectures.constants.HeaderConstants;
import com.knowit.lectures.domain.entities.ERole;
import com.knowit.lectures.domain.entities.Role;
import com.knowit.lectures.domain.entities.User;
import com.knowit.lectures.exceptions.UserDoesNotExist;
import com.knowit.lectures.repository.RoleRepository;
import com.knowit.lectures.services.users.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class FilterHeader extends OncePerRequestFilter {

    private final UserService userService;
    private final RoleRepository roleRepository;

    public FilterHeader(
            UserService userService,
            RoleRepository roleRepository
    ) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String userId = request.getHeader(HeaderConstants.X_USER_ID);
        String [] userR = request.getHeader(HeaderConstants.X_USER_SCOPES).split("([\"\"]*\\W+)"); //TODO
        String [] userRoles = new String[userR.length - 1];

        for (int i = 0; i < userR.length - 1; i++) {
            userRoles[i] = userR[i + 1];
        }

        if (userId == null || userRoles == null) {
            response.setStatus(403);
            throw new ServletException("User id or roles is null!");
        }

        try {
            Set<Role> roleSet = new HashSet<>();

            for (int i = 0; i < userRoles.length; i++) {
                Role role = new Role();
                if (ERole.EDITOR.getAuthority().equals(userRoles[i])){
                    role = roleRepository.findByName(ERole.EDITOR);
                } else if (ERole.ADMIN.getAuthority().equals(userRoles[i])) {
                    role = roleRepository.findByName(ERole.ADMIN);
                } else {
                    role = roleRepository.findByName(ERole.USER);
                }
                roleSet.add(role);
            }

            User user = this.userService.fetchByUserId(userId);
            user.setRoles(roleSet);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    user,
                    null,
                    user.getRoles().stream().map(r -> new SimpleGrantedAuthority(r.getName().getAuthority()))
                            .collect(Collectors.toSet())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

        } catch (UserDoesNotExist userDoesNotExist) {
            logger.warn(userDoesNotExist.getStackTrace());
        }

        filterChain.doFilter(request, response);
    }
}


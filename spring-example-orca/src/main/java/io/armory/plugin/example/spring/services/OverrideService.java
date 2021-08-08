package io.armory.plugin.example.spring.services;
import com.netflix.spinnaker.fiat.model.resources.Role;
import com.netflix.spinnaker.fiat.roles.file.FileBasedUserRolesProvider;
import com.netflix.spinnaker.fiat.permissions.ExternalUser;

import java.util.*;

public class OverrideService extends FileBasedUserRolesProvider {

    @Override
    public List<Role> loadRoles(ExternalUser user) {
        List<Role> roles = super.loadRoles(user);
        return roles.isEmpty()? Collections.singletonList(new Role("public")) : roles;
    }


}

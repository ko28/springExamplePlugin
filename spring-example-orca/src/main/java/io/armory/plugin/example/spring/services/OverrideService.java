package io.armory.plugin.example.spring.services;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.spinnaker.fiat.model.resources.Role;
import com.netflix.spinnaker.fiat.roles.file.FileBasedUserRolesProvider;
import com.netflix.spinnaker.fiat.permissions.ExternalUser;
import com.netflix.spinnaker.fiat.roles.file.FileBasedUserRolesProvider;

import java.io.*;
import java.util.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

public class OverrideService extends FileBasedUserRolesProvider {

    @Override
    public List<Role> loadRoles(ExternalUser user) {
        try {
            return Optional.ofNullable(parse2().get(user.getId()))
                    .orElse(Collections.singletonList(new Role("public")));
        } catch (IOException io) {
            log.error("Couldn't load roles for user " + user.getId() + " from file", io);
        }
        return Collections.singletonList(new Role("public"));
    }
    private Map<String, List<Role>> parse2() throws IOException {
        return parse2(new BufferedReader(new FileReader(new File(configProps.getPath()))));
    }

    private Map<String, List<Role>> parse2(Reader source) throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        return mapper.readValue(source, UserRolesMapping.class).toMap();
    }

}

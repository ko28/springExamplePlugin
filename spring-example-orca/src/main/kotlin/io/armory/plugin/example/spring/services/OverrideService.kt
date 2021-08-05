package io.armory.plugin.example.spring.services

import com.netflix.spinnaker.fiat.model.resources.Role
import com.netflix.spinnaker.fiat.roles.file.FileBasedUserRolesProvider
import com.netflix.spinnaker.fiat.permissions.ExternalUser

//import org.pf4j.PluginManager
//import java.io.IOException


class OverrideService() { // : FileBasedUserRolesProvider()
    override fun loadRoles(user: ExternalUser): List<Role> {
        try {
            return Optional.ofNullable(parse().get(user.getId()))
                    .orElse(listOf(Role("public")))
        } catch (io: IOException) {
            log.error("Couldn't load roles for user " + user.getId().toString() + " from file", io)
        }
        return listOf<Role>(Role("public"))
    }
}
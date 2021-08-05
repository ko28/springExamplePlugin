package io.armory.plugin.example.spring.config

import com.netflix.spinnaker.kork.plugins.api.spring.ExposeToApp
import io.armory.plugin.example.spring.services.OverrideService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@Configuration
class NewConfiguration {

    @Bean
    @Primary
    @ExposeToApp
    // use bean name of bean you are overriding from service as method name
    fun fileBasedUserRolesProvider() : OverrideService {
        return OverrideService()
    }

}

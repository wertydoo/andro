package com.thomasdriscoll.andro.configuration

import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EnableJpaRepositories("com.thomasdriscoll.andro.lib.dao")
open class AndroConfig {

}
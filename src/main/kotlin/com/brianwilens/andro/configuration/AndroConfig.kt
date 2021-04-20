package com.brianwilens.andro.configuration

import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EnableJpaRepositories("com.brianwilens.andro.lib.dao")
open class AndroConfig {

}
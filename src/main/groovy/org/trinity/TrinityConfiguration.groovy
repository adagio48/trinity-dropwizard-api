package org.trinity

import com.fasterxml.jackson.annotation.JsonProperty
import com.yammer.dropwizard.config.Configuration

import javax.validation.Valid
import javax.validation.constraints.NotNull

class TrinityConfiguration extends Configuration {
    @Valid
    @NotNull
    @JsonProperty
    MongoConfiguration mongoConfiguration = new MongoConfiguration()
}

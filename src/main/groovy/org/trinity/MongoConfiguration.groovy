package org.trinity

import javax.validation.constraints.NotNull

class MongoConfiguration {
    @NotNull
    String host
    @NotNull
    int port
    @NotNull
    String db
    @NotNull
    String user
    @NotNull
    String password
}

package org.acme;

import jakarta.inject.Inject;
import jakarta.json.JsonObject;
import jakarta.json.Json;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.Set;
import java.util.stream.Collectors;

@Path("/extensions")
public class ExtensionInfoApi {

    @Inject
    ExtensionInfo extensionInfo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Set<JsonObject> getExtensions() {
        Set<JsonObject> collect = extensionInfo.doSomething().stream().map(extension -> {
            JsonObject json = Json.createObjectBuilder()
                    .add("id", extension.id)
                    .add("name", extension.name)
                    .add("shortName", extension.shortName)
                    .add("keywords", Json.createArrayBuilder(extension.keywords))
                    .build();
            return json;
        }).collect(Collectors.toSet());
        return collect;
    }

}

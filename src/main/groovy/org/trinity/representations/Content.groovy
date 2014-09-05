package org.trinity.representations

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.hibernate.validator.constraints.NotBlank
import org.mongojack.Id
import org.mongojack.ObjectId
import javax.validation.constraints.NotNull

@JsonIgnoreProperties(ignoreUnknown = true)
class Content {
    @Id
    @ObjectId
    String id
    @NotBlank
    String type
    @NotNull
    PublishedState publishedState
    Map<String, Object> fields
}

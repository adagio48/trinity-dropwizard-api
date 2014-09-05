package org.trinity.resources

import org.mongojack.DBCursor
import org.mongojack.JacksonDBCollection
import org.trinity.representations.Content

import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("/content")
@Produces(MediaType.APPLICATION_JSON)
class ContentResource {

    private JacksonDBCollection<Content, String> contents

    public ContentResource(JacksonDBCollection<Content, String> contents) {
        this.contents = contents;
    }

    @GET
    @Path("/list")
    public List<Content> findAll() {
        DBCursor<Content> dbCursor = contents.find()
        convertToList(dbCursor)
    }

    @GET
    @Path("/list/{contentType}")
    public List<Content> findByContentType(@PathParam("contentType") String contentType) {
        DBCursor<Content> dbCursor = contents.find().is("type", contentType)
        convertToList(dbCursor)
    }

    private List<Content> convertToList(DBCursor<Content> dbCursor) {
        List<Content> contentList = []

        while (dbCursor.hasNext()) {
            Content content = dbCursor.next()
            contentList.add(content)
        }
        contentList
    }
}

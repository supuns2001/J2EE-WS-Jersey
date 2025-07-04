package lk.jiat.app.jersey.controller;

import jakarta.inject.Inject;
import jakarta.servlet.ServletContext;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lk.jiat.app.jersey.model.User;
import org.apache.commons.io.FilenameUtils;
import org.glassfish.jersey.media.multipart.ContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.glassfish.jersey.message.internal.InputStreamProvider;
import org.glassfish.jersey.server.mvc.Viewable;
import org.glassfish.jersey.servlet.ServletContainer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@Path("/")
public class HomeController {

    @Inject
    User user;
    
    
    @Context
    ServletContext context;
    @Inject
    private InputStreamProvider inputStreamProvider;


    @GET
    public Viewable index(){

        System.out.println(user);

        Map<String,Object> model = new HashMap<>();
        model.put("title","J2EE Home");


        return new Viewable("/index", model);
    }

    @POST
    @Path("file_upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA) // multipart request ekk thamai ganne kiynne methanin
    public Response fileUpload(@FormDataParam("file")FormDataBodyPart body){

        //multiple data save karanawanam:
        body.getParent().getBodyParts().forEach(part -> {
            InputStream entityAs = part.getEntityAs(InputStream.class);
            // patha process ekama add karanna thiyenne
        });

        
        
        InputStream content = body.getContent();
        ContentDisposition contentDisposition = body.getContentDisposition();
        String fileName = contentDisposition.getFileName();

        String extension = FilenameUtils.getExtension(fileName);

        try {
            int read = 0;
            byte[] buffer = new byte[1024];

            String realPath = context.getRealPath("/");
            System.out.println("Real path is :"+realPath);
            java.nio.file.Path uploadPath = Paths.get(realPath+"/upload");

            if (!Files.exists(uploadPath)) {
                System.out.println("Not folder");

                try{
                    Files.createDirectory(uploadPath);
                    System.out.println("create folder");
                }catch (IOException e){
                    e.printStackTrace();
                }

            }

            FileOutputStream out =
                    new FileOutputStream(new File(uploadPath+"/" + System.currentTimeMillis() + "." + extension));

            while ((read = content.read(buffer)) != -1){
                out.write(buffer,0,read);
            }
            Thread.sleep(100);

            out.flush();
            out.close();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("File saved successfully.");


        return Response.ok().build();
    }

}


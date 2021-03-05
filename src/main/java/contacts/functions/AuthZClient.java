package contacts.functions;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;

import java.net.URI;

public class AuthZClient {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    public static final String AUTHZ_HOST = System.getenv("AUTHZ_HOST");

    @SneakyThrows
    public static void create(Acl acl) {
        try {
            if (AUTHZ_HOST == null || AUTHZ_HOST.isEmpty()) {
                System.out.printf("Environment variable AUTHZ_HOST is not set. Suppressing call to /acl/create.%n");
                return;
            }

            URI uri = new URI("http://" + AUTHZ_HOST + "/acl/create");
            System.out.println(uri);

            HttpResponse httpResponse = Request.Post(uri)
                .addHeader("Authorization", "Bearer acl_admin")
                .addHeader("Accept", "application/json")
                .connectTimeout(100)
                .socketTimeout(100)
                .bodyByteArray(MAPPER.writeValueAsBytes(acl), ContentType.APPLICATION_JSON)
                .execute()
                .returnResponse();

            System.out.println(httpResponse.getStatusLine());

        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace(System.err);
        }
    }

    @SneakyThrows
    public static void delete(Acl acl) {
        if (AUTHZ_HOST == null || AUTHZ_HOST.isEmpty()) {
            System.out.printf("Environment variable AUTHZ_HOST is not set. Suppressing call to /acl/delete.%n");
            return;
        }

        System.out.printf("TODO: endpoint %s is not implemented yet%n", AUTHZ_HOST + "/acl/delete");

//        byte[] responseBody = Request.Post(AUTHZ_HOST + "/acl/delete")
//            .addHeader("Authorization", "Bearer acl_admin")
//            .addHeader("Accept", "application/json")
//            .connectTimeout(100)
//            .socketTimeout(100)
//            .bodyByteArray(MAPPER.writeValueAsBytes(acl), ContentType.APPLICATION_JSON)
//            .execute()
//            .returnContent().asBytes();
//
//        if (responseBody == null)
//            throw new IOException("Response body is null");
    }
}

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Solution {
    public static void getEmployees() throws Exception {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder(URI.create("http://localhost:8080/employees"))
            .header("accept", "application/json")
            .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.statusCode());
        System.out.println(response.body());
    }

    public static void main(String[] args) {
        try {
            getEmployees();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}

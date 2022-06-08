package Lufthansa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;


public class Test {

    private String token = "6b4bwyavmghqft6f96xuzz3g";
    private int restTime;
    private LocalDateTime askedforToken;
    private LocalDateTime askfornewToken;


    public Test() {
    }
    public void getToken() throws IOException, InterruptedException {

        String[] commands = new String[]{"curl", "https://api.lufthansa.com/v1/oauth/token", "-X", "POST", "-d",
                "client_id=vzr85vbughdj7tbvkqhbabj7", "-d", "client_secret=aGtfzGbDhN54TQPBjFQ6", "-d", "grant_type=client_credentials"};
        Process process = Runtime.getRuntime().exec(commands);
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        String response = "";
        while ((line = reader.readLine()) != null) {
            response = response + line;
        }
        String token = "";
        String restTime = "";
        token = response.substring(17, 42);
        restTime = response.substring(78, 84);

        this.token = token;
        this.restTime = Integer.parseInt(restTime);
        this.askedforToken = LocalDateTime.now();
        this.askfornewToken = askedforToken.plusSeconds(Long.parseLong(restTime));

        System.out.println(token);
        System.out.println(response);
        System.out.println(restTime);

    }

    public void searchFlight(String from, String to, String date, int directFlight) throws IOException, InterruptedException {
        String[] commands = new String[]{"curl", "-H", "Authorization: Bearer "+ token, "-H", "Accept: application/json",
               "https://api.lufthansa.com/v1/operations/schedules/"+from+"/"+to+"/"+date +"?directFlights="+directFlight};

        Process process = Runtime.getRuntime().exec(commands);
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        String response = "";
        while ((line = reader.readLine()) != null) {
            response = response + line;
        }
        String[] array = response.split("TotalJourney");

        for (int i = 1; i < array.length; i++) {
            String[] details = array[i].split("}");
            System.out.println("ende");
        }
        System.out.println(response);
    }

    public int getRestTime() {
        return restTime;
    }

    public LocalDateTime getAskedforToken() {
        return askedforToken;
    }

    public LocalDateTime getAskfornewToken() {
        return askfornewToken;
    }




    public static void main(String[] args) throws IOException, InterruptedException {
/*
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.lufthansa.com/v1/operations/schedules/FRA/JFK/2022-06-06?directFlights=0"))
                .header("X-RapidAPI-Host", "lihcode-lufthansa-open-new-v1.p.rapidapi.com")
                .header("X-RapidAPI-Key", "SIGN-UP-FOR-KEY")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());

 */
        Test versuch = new Test();
        versuch.searchFlight("MUC","JFK", "2022-06-06",1);

    }


}

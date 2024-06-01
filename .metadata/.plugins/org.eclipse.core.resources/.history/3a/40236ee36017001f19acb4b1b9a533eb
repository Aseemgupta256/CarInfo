package Carinfo;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public class MainCN {
    private static final String API_URL = "https://api.cuvora.com/car/partner/cricket-data";
    private static final String API_KEY = "test-creds@2320";

    public static void main(String[] args) {
        try {
            String response = getApiResponseCN(API_URL);
            System.out.println("API Response: " + response); // Print the response for debugging
            
            List<MtchCN> mtchesCN = parseMtchesCN(response);

            MtchCN hghestScoreMtchCN = getHghestScoreMtchCN(mtchesCN);
            int hghScoringMtchesCountCN = getHghScoringMtchesCountCN(mtchesCN);

            System.out.println("Highest Score and Team Name is : " + hghestScoreMtchCN.getHghestScoringTeamCN() + " with score " + hghestScoreMtchCN.getHghestScoreCN());
            System.out.println("Number Of Matches with total 300 Plus Score : " + hghScoringMtchesCountCN);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getApiResponseCN(String apiUrl) throws Exception {
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("apiKey", API_KEY);

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }

        in.close();
        connection.disconnect();

        return content.toString();
    }

    private static List<MtchCN> parseMtchesCN(String jsonResponse) throws Exception {
        List<MtchCN> mtchesCN = new ArrayList<>();

        // Determine if the response is an array or object
        if (jsonResponse.trim().startsWith("[")) {
            // If it's a JSON array
            JSONArray matchesArray = new JSONArray(jsonResponse);
            for (int i = 0; i < matchesArray.length(); i++) {
                JSONObject matchObject = matchesArray.getJSONObject(i);
                MtchCN mtchCN = new MtchCN(
                        matchObject.getString("id"),
                        matchObject.getString("dateTimeGMT"),
                        matchObject.getString("matchType"),
                        matchObject.getString("status"),
                        matchObject.getString("ms"),
                        matchObject.getString("t1"),
                        matchObject.getString("t2"),
                        matchObject.optString("t1s", "0/0"),
                        matchObject.optString("t2s", "0/0"),
                        matchObject.getString("t1img"),
                        matchObject.getString("t2img")
                );
                mtchesCN.add(mtchCN);
            }
        } else {
            // If it's a JSON object
            JSONObject jsonObject = new JSONObject(jsonResponse);
            JSONArray matchesArray = jsonObject.getJSONArray("matches"); // Adjust based on the actual structure
            for (int i = 0; i < matchesArray.length(); i++) {
                JSONObject matchObject = matchesArray.getJSONObject(i);
                MtchCN mtchCN = new MtchCN(
                        matchObject.getString("id"),
                        matchObject.getString("dateTimeGMT"),
                        matchObject.getString("matchType"),
                        matchObject.getString("status"),
                        matchObject.getString("ms"),
                        matchObject.getString("t1"),
                        matchObject.getString("t2"),
                        matchObject.optString("t1s", "0/0"),
                        matchObject.optString("t2s", "0/0"),
                        matchObject.getString("t1img"),
                        matchObject.getString("t2img")
                );
                mtchesCN.add(mtchCN);
            }
        }

        return mtchesCN;
    }

    private static MtchCN getHghestScoreMtchCN(List<MtchCN> mtchesCN) {
        MtchCN hghestScoreMtchCN = null;
        int hghestScoreCN = 0;

        for (MtchCN mtchCN : mtchesCN) {
            int t1ScoreCN = mtchCN.getTeam1ScoreCN();
            int t2ScoreCN = mtchCN.getTeam2ScoreCN();

            if (t1ScoreCN > hghestScoreCN) {
                hghestScoreCN = t1ScoreCN;
                hghestScoreMtchCN = mtchCN;
            }

            if (t2ScoreCN > hghestScoreCN) {
                hghestScoreCN = t2ScoreCN;
                hghestScoreMtchCN = mtchCN;
            }
        }

        return hghestScoreMtchCN;
    }

    private static int getHghScoringMtchesCountCN(List<MtchCN> mtchesCN) {
        int count = 0;

        for (MtchCN mtchCN : mtchesCN) {
            int totalScoreCN = mtchCN.getTeam1ScoreCN() + mtchCN.getTeam2ScoreCN();
            if (totalScoreCN > 300) {
                count++;
            }
        }

        return count;
    }
}

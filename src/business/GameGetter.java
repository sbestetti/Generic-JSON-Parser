package business;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.TimerTask;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class GameGetter extends TimerTask {
	
	private static Long nextId;
	
	public GameGetter(Long startFrom) {
		GameGetter.nextId = startFrom;
	}
	
	@Override
	public void run() {
		try {
			URL address = new URL("http://www.giantbomb.com/api/game/" + 
			nextId + "/?api_key=422ffef0c4bc57bf2edbf582bf00069cb51c6ff3&format=json");
			HttpURLConnection connection = (HttpURLConnection) address.openConnection();
			connection.setRequestProperty("User-Agent", "Mozilla/5.0");
			InputStream inputStream = connection.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			StringBuilder sb = new StringBuilder();
			int bufferControler;
			while ((bufferControler = bufferedReader.read()) != -1) {
				sb.append((char)bufferControler);
			}
			String jsonText = sb.toString();
			JSONObject json = (JSONObject) new JSONParser().parse(jsonText);
			JSONObject results = (JSONObject) json.get("results");
			if (results.get("aliases") == null) {
				System.out.println("Parsed object " + nextId + ": " + results.get("name") + " / Waiting timeout...");
			} else {
				System.out.println("Parsed object " + nextId + ": " + results.get("aliases") + " / Waiting timeout...");
			}
			nextId++;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}

}

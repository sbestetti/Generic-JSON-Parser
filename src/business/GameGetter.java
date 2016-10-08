package business;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import models.Game;
import util.DateParser;

public class GameGetter extends TimerTask {
	
	private static Long nextId;	
	
	public GameGetter(Long startFrom) {
		GameGetter.nextId = startFrom;
	}	
	
	@Override
	public void run() {
		Game game = new Game();
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
			ObjectMapper mapper = new ObjectMapper();
			JsonNode root = mapper.readValue(jsonText, JsonNode.class);
			game.setId(root.path("results").path("id").asLong());
			String alias = root.path("results").path("aliases").asText();
			String name = root.path("results").path("name").asText();
			if (alias.equals("null")) {
				game.setTitle(name);
			} else {
				game.setTitle(alias);
			}
			JsonNode jsonPlatforms = root.path("results").get("platforms");
			List<String> platformsList = new ArrayList<String>();
			for (int i = 0; i < jsonPlatforms.size(); i++) {
				platformsList.add(jsonPlatforms.get(i).get("name").asText());
			}
			game.setPlataforms(platformsList);
			JsonNode jsonGenres = root.path("results").get("genres");
			List<String> genresList = new ArrayList<String>();
			for (int i = 0; i < jsonGenres.size(); i++) {
				genresList.add(jsonGenres.get(i).get("name").asText());
			}
			game.setGenres(genresList);
			game.setDescription(root.path("results").path("deck").asText());
			game.setReleaseDate(DateParser.parse(root.path("results").path("original_release_date").asText()));
			System.out.println("ID: " + game.getId());
			System.out.println("Title: " + game.getTitle());
			System.out.println("Description: " + game.getDescription());
			System.out.println("Release date: " + new SimpleDateFormat("MMMM/YYYY").format(game.getReleaseDate()));
			System.out.println("Platforms " + game.getPlataforms().toString());
			System.out.println("Genres: " + game.getGenres().toString());
			System.out.println("Waiting timeout interval\n");
			nextId++;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
